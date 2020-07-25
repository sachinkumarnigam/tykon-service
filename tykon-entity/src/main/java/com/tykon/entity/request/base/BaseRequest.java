package com.tykon.entity.request.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BaseRequest {

	@JsonIgnore
	private UserRequestIdentity userRequestIdentity;

	@JsonProperty(value = "selected_school_id")
	private String selectedSchoolId;

	@JsonProperty(value = "selected_student_id")
	private String selectedStudentId;

	@JsonProperty(value = "screen_name")
	private String screenName;

	@JsonProperty(value = "last_screen_name")
	private String lastScreenName;

	@JsonProperty(value = "source")
	private String source = "web";

	@JsonProperty(value = "app_version")
	private String appVersion;

	@JsonProperty(value = "lat")
	private Float latitude;

	@JsonProperty(value = "long")
	private Float longitude;

	@JsonProperty(value = "os")
	private String os;

	@JsonProperty(value = "os_version")
	private String osVersion;

	@JsonProperty(value = "os_build_version")
	private String osBuildVersion;

	@JsonProperty("language")
	private String language;

	@JsonIgnore
	private String cmpId;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UserRequestIdentity getUserRequestIdentity() {
		return userRequestIdentity;
	}

	public void setUserRequestIdentity(UserRequestIdentity userRequestIdentity) {
		this.userRequestIdentity = userRequestIdentity;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getLastScreenName() {
		return lastScreenName;
	}

	public void setLastScreenName(String lastScreenName) {
		this.lastScreenName = lastScreenName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getCmpId() {
		return cmpId;
	}

	public void setCmpId(String cmpId) {
		this.cmpId = cmpId;
	}

	/**
	 * This function is for using in master crudbase service. This function would be
	 * overridden in edit requests where as in create request it will return null
	 *
	 * @return
	 */
	public String getId() {
		return null;
	}

	public String getSelectedSchoolId() {
		return selectedSchoolId;
	}

	public void setSelectedSchoolId(String selectedSchoolId) {
		this.selectedSchoolId = selectedSchoolId;
	}

	public String getSelectedStudentId() {
		return selectedStudentId;
	}

	public void setSelectedStudentId(String selectedStudentId) {
		this.selectedStudentId = selectedStudentId;
	}

	public String getOsBuildVersion() {
		return osBuildVersion;
	}

	public void setOsBuildVersion(String osBuildVersion) {
		this.osBuildVersion = osBuildVersion;
	}

}
