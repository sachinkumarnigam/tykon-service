package com.tykon.entity.response.base;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tykon.api.entity.view.model.base.BaseViewModel;

@JsonInclude(Include.NON_NULL)
public class ListEntityResponse<T extends BaseViewModel> extends BaseResponse {

	private List<T> data;

	private long totalNumberOfRecords = 0;

	private int totalPages = 0;

	public final List<T> getData() {
		return data;
	}

	public final void setData(List<T> data) {
		this.data = data;
	}

	public final long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public final void setTotalNumberOfRecords(long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	public final int getTotalPages() {
		return totalPages;
	}

	public final void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


}
