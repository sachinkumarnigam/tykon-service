package com.tykon.notification.entity.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tykon.entity.request.base.BaseRequest;

public class UpdateNotificationEventRequest extends BaseRequest {

	@JsonProperty(value = "notification_id")
	@NotNull
	private String notificationId;

	@JsonProperty(value = "is_update")
	boolean isUpdate;

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

}
