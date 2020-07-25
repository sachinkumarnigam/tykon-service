/**
 *
 */
package com.tykon.entity.response.base;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tykon.entity.request.base.BaseRequest;

/**
 * @author amleshsinha
 *
 */
@JsonInclude(Include.NON_NULL)
public class BaseResponse {

	private ResponseStatus status = ResponseStatus.FAILED;

	@JsonProperty("field_error_message_map")
	private Map<String, String> fieldErrorMessageMap = new HashMap<>();

	@JsonProperty(value = "screen_name")
	private String screenName;

	private BaseRequest request;

	@JsonProperty("token")
	private String token;

	@JsonProperty(value = "os_version")
	private String osVersion;

	@JsonProperty(value = "app_version")
	private String appVersion;

	@JsonProperty(value = "os_build_version")
	private String osBuildVersion;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the status
	 */
	public ResponseStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	/**
	 * @return the fieldErrorMessageMap
	 */
	public Map<String, String> getFieldErrorMessageMap() {
		return fieldErrorMessageMap;
	}

	/**
	 * @param fieldErrorMessageMap the fieldErrorMessageMap to set
	 */
	public void setFieldErrorMessageMap(Map<String, String> fieldErrorMessageMap) {
		this.fieldErrorMessageMap = fieldErrorMessageMap;
	}

	public void addErrorMessage(String fieldName, String message) {
		this.fieldErrorMessageMap.put(fieldName, message);
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public BaseRequest getRequest() {
		return request;
	}

	public void setRequest(BaseRequest request) {
		this.request = request;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getOsBuildVersion() {
		return osBuildVersion;
	}

	public void setOsBuildVersion(String osBuildVersion) {
		this.osBuildVersion = osBuildVersion;
	}

}
