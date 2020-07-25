package com.tykon.api.entity.response;

import com.tykon.entity.response.base.BaseResponse;

public class GenericResponse extends BaseResponse {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	} 

}
