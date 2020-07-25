package com.tykon.api.framework.service.core.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgUrlRequest {
	@JsonProperty("image_url")
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public ImgUrlRequest(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
