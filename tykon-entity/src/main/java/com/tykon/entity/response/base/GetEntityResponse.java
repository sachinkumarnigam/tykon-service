package com.tykon.entity.response.base;

import com.tykon.api.entity.view.model.base.BaseViewModel;

public class GetEntityResponse<T extends BaseViewModel> extends BaseResponse {

	T data;

	public final T getData() {
		return data;
	}

	public final void setData(T data) {
		this.data = data;
	}


}
