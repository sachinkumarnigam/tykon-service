package com.tykon.api.framework.service.core.notification.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SlackNotificationRequest {
	@JsonProperty("text")
	private String message;
	
//	@JsonProperty("channel")
//	private String channel;
//	
	@JsonProperty("icon_emoji")
	private String iconEmoji;
	
	

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("attachments")
	private List<ImgUrlRequest> attachments;
	
	public List<ImgUrlRequest> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ImgUrlRequest> attachments) {
		this.attachments = attachments;
	}

	public SlackNotificationRequest(String message, String username, String iconEmoji, ImgUrlRequest imgUrlRequest) {
		this.message = message;
//		this.channel = channel;
		this.iconEmoji = iconEmoji;
		this.userName = username;
		this.attachments = new ArrayList<ImgUrlRequest>();
		attachments.add(imgUrlRequest);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getIconEmoji() {
		return iconEmoji;
	}

	public void setIconEmoji(String iconEmoji) {
		this.iconEmoji = iconEmoji;
	}
}
