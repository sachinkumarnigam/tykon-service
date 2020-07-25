package com.tykon.entity.request.base;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListEntityRequest extends BaseRequest{

	private Set<String> entityIds;
	
	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("is_active")
	private boolean isActive = true;

	@JsonIgnore
	private SearchFilterType searchFilterType;

	@JsonIgnore
	private SearchType searchType;

	private int pageNumber;

	private int pageSize;

	@JsonIgnore
	private boolean refreshCache;

	public final Set<String> getEntityIds() {
		return entityIds;
	}

	public final void setEntityIds(Set<String> entityIds) {
		this.entityIds = entityIds;
	}

	public final int getPageNumber() {
		return pageNumber;
	}

	public final void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public final int getPageSize() {
		return pageSize;
	}

	public final void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public final boolean isRefreshCache() {
		return refreshCache;
	}

	public final void setRefreshCache(boolean refreshCache) {
		this.refreshCache = refreshCache;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}
