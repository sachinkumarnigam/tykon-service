package com.tykon.api.framework.service.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.bson.Document;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tykon.api.entity.view.model.base.BaseViewModel;
import com.tykon.api.framework.service.core.annotation.Timed;
import com.tykon.api.framework.service.core.exception.AppCommonException;
import com.tykon.api.framework.service.core.util.CommonUtil;
import com.tykon.api.framework.service.core.util.DateUtil;
import com.tykon.entity.request.base.BaseRequest;
import com.tykon.entity.request.base.GetEntityRequest;
import com.tykon.entity.request.base.ListEntityRequest;
import com.tykon.entity.request.base.SearchFilterType;
import com.tykon.entity.request.base.SearchType;
import com.tykon.entity.response.base.BaseResponse;
import com.tykon.entity.response.base.GetEntityResponse;
import com.tykon.entity.response.base.ListEntityResponse;
import com.tykon.entity.response.base.ResponseStatus;
import com.tykon.persistence.jpa.mongo.model.base.AuditEntity;
import com.tykon.persistence.jpa.mongo.repository.base.BaseMongoRepository;

public abstract class CrudMasterBaseService<CREATE_REQ extends BaseRequest, EDIT_REQ extends CREATE_REQ, RES extends BaseResponse, DOC extends AuditEntity, REPO extends BaseMongoRepository<DOC, String>, VIEW_MODEL extends BaseViewModel, LIST_RESP extends ListEntityResponse<VIEW_MODEL>, GET_RESP extends GetEntityResponse<VIEW_MODEL>>
		extends BaseService {

	@Autowired
	UserDetailsServiceImpl userDetailService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	RedissonClient redissonClient;

	// @Transactional
	public RES create(CREATE_REQ request) {
		try {
			preCreate(request);
			validate(request);
			DOC document = getNewCrudDocument();
			document = copyFromEntityToDocument(request, document);

			preCreateSave(request, document);
			document = getCrudMasterRepository().save(document);
			postCreateSave(request, document);
			return getSuccessResponse(document);
		} catch (Exception e) {
			logger.error("error creating entity.", e);
			return getFailedResponse(request, e.getMessage());
		}
	}

	protected DOC copyFromEntityToDocument(CREATE_REQ request, DOC document) throws Exception {
		BeanUtils.copyProperties(request, document);
		return document;
	}

	protected void postCreateSave(CREATE_REQ request, DOC document) throws Exception {
		doAudit(request, document);
	}

	private void doAudit(CREATE_REQ request, DOC documentSaved) {
		saveInAudit(request, null, documentSaved, "I");
	}

	private void doAudit(EDIT_REQ request, DOC oldDocument, DOC documentSaved) {
		saveInAudit(request, oldDocument, documentSaved, "U");
	}

	protected void saveInAudit(CREATE_REQ request, DOC oldDocument, DOC documentSaved, String operationType) {
		Document document = new Document();
		document.append("crdt", new Date());
		document.append("request", request);
		document.append("old_document", oldDocument);
		document.append("operation_type", operationType);
		document.append("new_document", documentSaved);
		mongoTemplate.save(document, "hp_audit");
	}

	protected void preCreateSave(CREATE_REQ request, DOC document) {
		Date curDate = new Date();
		document.setCreatedAt(curDate);
		document.setUpdatedAt(curDate);
	}

	protected void preCreate(CREATE_REQ request) throws AppCommonException {

	}

	private void validate(CREATE_REQ request) throws AppCommonException {
		switch (request.getUserRequestIdentity().getUserTypeId().intValue()) {
		case 2:
			validateRequestFromAdmin(request);
			break;
		case 3:
			validateRequestFromSuperAdmin(request);
			break;
		case 4:
			validateRequestFromSchool(request);
			break;
		case 99:
			validateRequestFromParent(request);
			break;
		default:
			throw new AppCommonException("Invalid user type");
		}
	}

	protected boolean validateRequestFromAdmin(CREATE_REQ request) throws AppCommonException {
		return true;
	}

	protected boolean validateRequestFromSuperAdmin(CREATE_REQ request) throws AppCommonException {
		return true;
	}

	protected boolean validateRequestFromSchool(CREATE_REQ request) throws AppCommonException {
		return true;
	}

	protected boolean validateRequestFromParent(CREATE_REQ request) throws AppCommonException {
		return true;
	}

	// @Transactional
	public RES edit(EDIT_REQ request) {
		try {
			preEdit(request);
			validate(request);
			DOC oldDocumentToAudit = null;
			DOC document = null;
			oldDocumentToAudit = getCrudMasterRepository().findById(request.getId()).get();
			document = getCrudMasterRepository().findById(request.getId()).get();
			if (document == null) {
				return getFailedResponse(request, "Invalid Entity Update Request.");
			}
			preEditCopy(request, document);
			document = copyFromEntityToDocument(request, document);
			postEditCopy(request, document, oldDocumentToAudit);
			document = getCrudMasterRepository().save(document);
			postEditSave(request, oldDocumentToAudit, document);
			return getSuccessResponse(document);
		} catch (Exception e) {
			logger.error("error updating entity.", e);
			return getFailedResponse(request, e.getMessage());
		}
	}

	protected void preEdit(EDIT_REQ request) throws AppCommonException {

	}

	protected void preEditCopy(EDIT_REQ request, DOC document) {

	}

	protected void postEditCopy(EDIT_REQ request, DOC document, DOC oldDocumentToAudit) {
		Date curDate = new Date();
		document.setUpdatedAt(curDate);
		document.setCreatedBy(oldDocumentToAudit.getCreatedBy());
		document.setCreatedAt(oldDocumentToAudit.getCreatedAt());
	}

	protected void postEditSave(EDIT_REQ request, DOC oldDocumentToAudit, DOC documentSaved) {
		doAudit(request, oldDocumentToAudit, documentSaved);
	}

	@Timed
	public Set<String> findEntityIds(ListEntityRequest request) {
		Set<String> ids = new HashSet<>();
		long dbTime = 0l;
		long saveCacheTime = 0l;
		long cacheTime = 0l;
		try {
			if (CommonUtil.isEmpty(request.getSessionId())) {
				long curTime = new Date().getTime();
				Set<String> filterEntityIdsNotInCache = findEntityIdsFromCache(request, ids);
				cacheTime = new Date().getTime() - curTime;

				if (!filterEntityIdsNotInCache.isEmpty() || ids.isEmpty()) {
					logger.info("some or all ids not in cache getting ids from db ");
					curTime = new Date().getTime();
					Map<String, Set<String>> idsFromSource = findEntityIdsFromSource(request,
							filterEntityIdsNotInCache);
					dbTime = new Date().getTime() - curTime;
					if (idsFromSource != null && !idsFromSource.isEmpty()) {
						logger.info("found entity id from source ");
						curTime = new Date().getTime();
						Set<String> aggregatedEntityIds = saveEntityIdIntoCache(request, idsFromSource);
						saveCacheTime = new Date().getTime() - curTime;
						ids.addAll(aggregatedEntityIds);
					} else {
						logger.info("not found ids from db");
					}
				} else {
					logger.info("got all ids from cache ");
				}

			} else {
				Map<String, Set<String>> idsFromSource = findEntityIdsFromSource(request, Collections.emptySet());
				if (idsFromSource != null && !idsFromSource.isEmpty()) {
					ids.addAll(idsFromSource.values().stream().collect(Collectors.toSet()).stream().flatMap(Set::stream)
							.collect(Collectors.toSet()));
					logger.info("got all ids from db for inactive session ");
				}
			}
		} catch (Exception e) {
			logger.error("error Searching entity ids.", e);
		}

		// TODO move below object to json conversion in excetion putting it just for
		// debugging latency issue on prod
		try {
			logger.error("search request is " + objectMapper.writeValueAsString(request));
		} catch (JsonProcessingException e1) {
		}

		logger.info("time data of findEntityIds cacheTime = " + cacheTime + " dbTime = " + dbTime + " saveCacheTime = "
				+ saveCacheTime);
		return ids;
	}

	@Timed
	private Set<String> saveEntityIdIntoCache(ListEntityRequest request, Map<String, Set<String>> idsFromSource) {

		Set<String> aggregatedIds = new HashSet<>();
		if (this.getFilterCacheKey(request) == null) {// no need to cache model
			return aggregatedIds;
		}
		String mapKey = this.getFilterCacheKey(request) + "_" + request.getSelectedSchoolId();
		if (request.getSearchFilterType().value == SearchFilterType.SCHOOLID.value) {
			mapKey = this.getFilterCacheKey(request);
		}
		RMapCache<String, Set<String>> rMap = redissonClient.getMapCache(mapKey);

		for (String key : idsFromSource.keySet()) {
			if (idsFromSource.get(key) != null) {
				aggregatedIds.addAll(idsFromSource.get(key));
				rMap.put(key, idsFromSource.get(key), this.getCacheTTL(request), TimeUnit.HOURS);
			}
		}
		// rMap.expireAsync(this.getCacheTTL(request)*4, TimeUnit.HOURS);
		return aggregatedIds;
	}

	@Timed
	private Set<String> findEntityIdsFromCache(ListEntityRequest request, Set<String> ids) {
		logger.info("Getting ids from cache");
		if (this.getFilterCacheKey(request) == null) {// if model is not getting cached
			return request.getEntityIds();
		}
		Set<String> pendingId = null;
		if (request.getSearchFilterType().value == SearchFilterType.SCHOOLID.value) {
			pendingId = getEntityIdsBySchoolId(request, ids);
		} else {
			pendingId = getEntityIdsBySubEntityIds(request, ids);
		}
		return pendingId;
	}

	@Timed
	private Set<String> getEntityIdsBySubEntityIds(ListEntityRequest request, Set<String> ids) {
		Set<String> pendingId = new HashSet<String>();

		RMapCache<String, Set<String>> rMap = redissonClient
				.getMapCache(this.getFilterCacheKey(request) + "_" + request.getSelectedSchoolId());
		Map<String, Set<String>> cachedData = null;
		if (rMap != null && !rMap.isEmpty()) {
			cachedData = rMap.getAll(request.getEntityIds().stream().collect(Collectors.toSet()));
		}
		if (cachedData != null && !cachedData.isEmpty()) {
			for (String id : request.getEntityIds()) {
				if (cachedData.get(id) != null) {
					ids.addAll(cachedData.get(id));
				} else {
					pendingId.add(id);
				}
			}
		} else {
			pendingId.addAll(request.getEntityIds());
		}
		return pendingId;
	}

	@Timed
	private Set<String> getEntityIdsBySchoolId(ListEntityRequest request, Set<String> ids) {

		Set<String> pendingId = new HashSet<String>();

		RMapCache<String, Set<String>> rMap = redissonClient.getMapCache(this.getFilterCacheKey(request));
		if (rMap != null && !rMap.isEmpty()) {
			Set<String> idList = rMap.get(request.getSelectedSchoolId());
			if (idList != null && !idList.isEmpty()) {
				ids.addAll(idList);
			}
		}

		return pendingId;
	}

	@Timed
	public LIST_RESP findEntities(ListEntityRequest request) {
		LIST_RESP response = getNewListEntityResponse();
		response.setRequest(request);
		long dbTime = 0l;
		long cacheTime = 0l;
		long findEntityIdsTime = 0l;
		long totalTime = 0l;
		long startTime = new Date().getTime();
		try {
			Set<String> originalEntityIds = null;
			SearchFilterType originalSearchFilterType = null;
			if (request.getSearchType().value == SearchType.FILTER.value) {
				long lastTime = new Date().getTime();
				Set<String> entityIds = this.findEntityIds(request);
				findEntityIdsTime = new Date().getTime() - lastTime;
				originalEntityIds = request.getEntityIds();// keeping original values if required in future
				originalSearchFilterType = request.getSearchFilterType();

				request.setEntityIds(entityIds);
				request.setSearchFilterType(SearchFilterType.ENTITY_ID);
			}
			List<DOC> models = new ArrayList<>();
			List<VIEW_MODEL> entities = new ArrayList<>();
			long lastTime = new Date().getTime();
			Set<String> entityIdsNotInCache = new HashSet<>();
			entityIdsNotInCache = findFromCache(request, models);// will fetch from cache and add into models
			cacheTime = new Date().getTime() - lastTime;
			boolean isSoftTtlExpired = softTtlExpired(models);
			if (!CommonUtil.isEmpty(request.getSessionId())) {
				lastTime = new Date().getTime();
				models = fetchFromDb(request, models, entityIdsNotInCache);
				dbTime = new Date().getTime() - lastTime;
				logger.info("got for inactive session from db");
			} else if (CommonUtil.isEmpty(request.getSessionId())
					&& (!entityIdsNotInCache.isEmpty() || models.isEmpty())) {
				lastTime = new Date().getTime();
				models = fetchFromDb(request, models, entityIdsNotInCache);
				dbTime = new Date().getTime() - lastTime;
			} else {
				logger.info("got from cache all");
			}
			entities = copyFromModelToVM(models, entities);
			entities = doPostCopyModel(entities, request);
			response.setData(entities);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setTotalNumberOfRecords(entities.size());
			if (originalEntityIds != null) {
				request.setEntityIds(originalEntityIds);
				request.setSearchFilterType(originalSearchFilterType);
			}

		} catch (Exception e) {
			logger.error("error Searching entity.", e);
			try {
				logger.error("search request is " + objectMapper.writeValueAsString(request));
			} catch (JsonProcessingException e1) {
			}
			response.setStatus(ResponseStatus.FAILED);
			response.addErrorMessage("ERROR", e.getMessage());
		}

		totalTime = new Date().getTime() - startTime;

		logger.info("time data of findEntities totalTime = " + totalTime + " cacheTime = " + cacheTime + " dbTime = "
				+ dbTime + " findEntityIdsTime = " + findEntityIdsTime);
		return response;
	}

	private boolean softTtlExpired(List<DOC> models) {
		Date date = new Date();
		boolean isExpired = false;
		for (DOC doc : models) {
			if (doc.getCacheExpiryTime().before(date) && doc.isBeingRefreshed() == false) {
				isExpired = true;// need logic to update in cache that the id is being refreshed so no other
									// thread start refreshing it
			}
		}
		return isExpired;
	}

	private List<VIEW_MODEL> copyFromModelToVM(List<DOC> models, List<VIEW_MODEL> entities) {
		for (DOC document : models) {
			VIEW_MODEL model = getNewViewModel();
			model = copyFromDocumentToViewModel(document, model);
			entities.add(model);
		}
		return entities;
	}

	@Timed
	private List<DOC> fetchFromDb(ListEntityRequest request, List<DOC> models, Set<String> entityIdsNotInCache) {
		logger.info("getting from db");
		List<DOC> modelsFromDB = findFromDB(request, entityIdsNotInCache);
		if (modelsFromDB != null && !modelsFromDB.isEmpty()) {
			logger.info("found data from db");
			if (CommonUtil.isEmpty(request.getSessionId())) {
				saveIntoCache(request, modelsFromDB);
				logger.info("saved entities into cache");
			}
			models.addAll(modelsFromDB);
		} else {
			logger.info("no found data from db");
		}
		return models;
	}

	protected List<VIEW_MODEL> doPostCopyModel(List<VIEW_MODEL> entities, ListEntityRequest request) {
		return entities;
	}

	@Timed
	public LIST_RESP findEntities(GetEntityRequest request) {
		ListEntityRequest listRequest = new ListEntityRequest();
		listRequest.setEntityIds(new HashSet<>());
		listRequest.getEntityIds().add(request.getId());
		listRequest.setSelectedSchoolId(request.getSelectedSchoolId());
		listRequest.setSearchFilterType(request.getSearchFilterType());
		listRequest.setSearchType(request.getSearchType());
		if (!CommonUtil.isEmpty(request.getSessionId()))
			listRequest.setSessionId(request.getSessionId());
		LIST_RESP listResponse = this.findEntities(listRequest);
		return listResponse;

	}

	@Timed
	public GET_RESP findEntity(GetEntityRequest request) {
		ListEntityRequest listRequest = new ListEntityRequest();
		listRequest.setSelectedSchoolId(request.getSelectedSchoolId());
		listRequest.setEntityIds(new HashSet<>());
		listRequest.getEntityIds().add(request.getId());
		listRequest.setSearchType(request.getSearchType());
		listRequest.setSearchFilterType(request.getSearchFilterType());
		if (!CommonUtil.isEmpty(request.getSessionId()))
			listRequest.setSessionId(request.getSessionId());
		LIST_RESP listResponse = this.findEntities(listRequest);
		GET_RESP response = this.getNewGetEntityResponse();
		response.setData(
				listResponse.getData() != null && listResponse.getData().size() > 0 ? listResponse.getData().get(0)
						: null);
		response.setStatus(listResponse.getStatus());
		response.setFieldErrorMessageMap(listResponse.getFieldErrorMessageMap());
		return response;
	}

	private Set<String> findFromCache(ListEntityRequest request, List<DOC> models) {

		logger.info("Getting from cache");
		if (this.getCacheKey() == null) {// if model is not getting cached
			return request.getEntityIds();
		}
		Set<String> pendingId = new HashSet<>();

		String mapKey = this.getCacheKey();
		if (mapKey.equals("SCHOOL_META_BUCKET")) {

		} else {
			mapKey = this.getCacheKey() + "_" + request.getSelectedSchoolId();
		}
		RMapCache<String, DOC> rMap = redissonClient.getMapCache(mapKey);

		Map<String, DOC> cachedData = null;
		if (rMap != null && !rMap.isEmpty() && request.getEntityIds() != null && !request.getEntityIds().isEmpty()) {
			cachedData = rMap.getAll(request.getEntityIds().stream().collect(Collectors.toSet()));
		} else {
			if (request.getEntityIds() != null) {
				pendingId.addAll(request.getEntityIds());
			}
			return pendingId;
		}
		if (cachedData != null && !cachedData.isEmpty()) {
			for (String id : request.getEntityIds()) {
				if (cachedData.get(id) != null) {
					models.add(cachedData.get(id));
				} else {
					pendingId.add(id);
				}
			}
		} else {
			pendingId.addAll(request.getEntityIds());
		}
		return pendingId;
	}

	@Timed
	protected void saveIntoCache(ListEntityRequest request, List<DOC> models) {
		if (this.getCacheKey() == null || models == null || models.isEmpty()) {// no need to cache model
			return;
		}
		String mapKey = this.getCacheKey();
		if (!request.getSelectedSchoolId().equals(models.get(0).getId())) {
			mapKey = this.getCacheKey() + "_" + request.getSelectedSchoolId();
		}
		RMapCache<String, DOC> rMap = redissonClient.getMapCache(mapKey);

		for (DOC model : models) {
			model = setCacheProperties(model);
			rMap.put(model.getId(), model, this.getCacheTTL(), TimeUnit.HOURS);
		}
		// System.out.println("TTL "+this.getCacheTTL());
		// rMap.expire(this.getCacheTTL()*4, TimeUnit.SECONDS);
	}

	private DOC setCacheProperties(DOC model) {
		model.setCachePutTime(new Date());
		model.setCacheExpiryTime(DateUtil.calculateExpiryDate(model.getCachePutTime(), (int) this.getCacheTTL() * 60));
		model.setBeingRefreshed(false);
		return model;
	}

	protected long getCacheTTL() {
		return DEFAULT_TTL;
	}

	protected EDIT_REQ copyFromDocumentToEntity(DOC document, EDIT_REQ editRequest) throws Exception {
		BeanUtils.copyProperties(document, editRequest);
		return editRequest;
	}

	public GetEntityResponse<VIEW_MODEL> getEntity(GetEntityRequest request) {
		GetEntityResponse<VIEW_MODEL> response = new GetEntityResponse<>();
		try {
			DOC document = getCrudMasterRepository().findById(request.getId()).get();
			VIEW_MODEL viewModel = getNewViewModel();
			copyFromDocumentToViewModel(document, viewModel);
			response.setData(viewModel);
			response.setStatus(ResponseStatus.SUCCESS);
		} catch (Exception e) {
			logger.error("error Getting entity.", e);
			response.setStatus(ResponseStatus.FAILED);
			response.addErrorMessage("ERROR", e.getMessage());
		}
		return response;
	}

	protected VIEW_MODEL copyFromDocumentToViewModel(DOC document, VIEW_MODEL model) {
		BeanUtils.copyProperties(document, model);
		model.setCachePutTime(new Date());
		// String json = objectMapper.writeValueAsString(document);
		// model = (VIEW_MODEL) objectMapper.convertValue(document, model.getClass());
		return model;
	}

	public RES getSuccessResponse(DOC document) throws Exception {
		RES response = getNewEditResponse();
		EDIT_REQ editRequest = getNewEditRequest();
		copyFromDocumentToEntity(document, editRequest);
		response.setRequest(editRequest);
		response.setStatus(ResponseStatus.SUCCESS);
		return response;
	}

	public RES getFailedResponse(BaseRequest request, String message) {
		RES response = getNewEditResponse();
		response.setRequest(request);
		response.addErrorMessage("ERROR", message);
		response.setStatus(ResponseStatus.FAILED);
		return response;
	}

	protected abstract String getCacheKey();

	protected abstract REPO getCrudMasterRepository();

	protected abstract DOC getNewCrudDocument();

	protected abstract EDIT_REQ getNewEditRequest();

	protected abstract RES getNewEditResponse();

	protected abstract VIEW_MODEL getNewViewModel();

	protected abstract List<DOC> findFromDB(ListEntityRequest request, Set<String> entityIdsNotInCache);

	protected abstract GET_RESP getNewGetEntityResponse();

	protected abstract LIST_RESP getNewListEntityResponse();

	protected abstract String getFilterCacheKey(ListEntityRequest request);

	protected abstract Map<String, Set<String>> findEntityIdsFromSource(ListEntityRequest request,
			Set<String> filterEntityIdsNotInCache);

	protected abstract long getCacheTTL(ListEntityRequest request);

}
