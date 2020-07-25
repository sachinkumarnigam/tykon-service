package com.tykon.entity.response.base;


public class BaseHostDetailsResponse extends BaseResponse{

	private String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
