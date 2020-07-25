package com.tykon.api.entity.request;

import com.tykon.entity.bo.model.UserActivityModel;
import com.tykon.entity.request.base.BaseRequest;

public class UserActivityRequest extends BaseRequest {

	UserActivityModel model;

	public UserActivityModel getModel() {
		return model;
	}

	public void setModel(UserActivityModel model) {
		this.model = model;
	}
}
