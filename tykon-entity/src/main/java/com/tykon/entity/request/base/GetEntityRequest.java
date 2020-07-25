package com.tykon.entity.request.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEntityRequest extends BaseRequest {

	private String id;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonIgnore
	private SearchFilterType searchFilterType;

	@JsonIgnore
	private SearchType searchType;

	public GetEntityRequest() {
		super();
	}

	public GetEntityRequest(String schoolId, String id, SearchFilterType searchFilterType, SearchType searchType,
			String sessionId) {
		super();
		this.setSelectedSchoolId(schoolId);
		this.id = id;
		this.searchFilterType = searchFilterType;
		this.searchType = searchType;
		this.sessionId = sessionId;
	}

	public GetEntityRequest(String schoolId, String id, SearchFilterType searchFilterType, SearchType searchType) {
		super();
		this.setSelectedSchoolId(schoolId);
		this.id = id;
		this.searchFilterType = searchFilterType;
		this.searchType = searchType;
	}

	public GetEntityRequest(String id, SearchFilterType searchFilterType, SearchType searchType) {
		super();
		this.id = id;
		this.searchFilterType = searchFilterType;
		this.searchType = searchType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public final SearchFilterType getSearchFilterType() {
		return searchFilterType;
	}

	public final void setSearchFilterType(SearchFilterType searchFilterType) {
		this.searchFilterType = searchFilterType;
	}

	public final SearchType getSearchType() {
		return searchType;
	}

	public final void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
