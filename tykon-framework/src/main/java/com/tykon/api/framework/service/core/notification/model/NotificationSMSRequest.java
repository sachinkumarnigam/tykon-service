/**
 * 
 */
package com.tykon.api.framework.service.core.notification.model;

/**
 * @author sachin
 *
 */
public class NotificationSMSRequest {
	
	private String message;
	
	private String mobileNumber;
	
	String []variableValues;
	
	public NotificationSMSRequest() {
		
	}
	
	public NotificationSMSRequest(String message, String mobileNumber) {
		super();
		this.message = message;
		this.mobileNumber = mobileNumber;
	}
	
	public NotificationSMSRequest(String message, String mobileNumber, String[] variableValues) {
		super();
		this.message = message;
		this.mobileNumber = mobileNumber;
		this.variableValues = variableValues;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String[] getVariableValues() {
		return variableValues;
	}

	public void setVariableValues(String[] variableValues) {
		this.variableValues = variableValues;
	}
	

}
