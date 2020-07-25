package com.tykon.api.framework.service.core.notification.api;


import java.util.List;

import com.tykon.api.framework.service.core.notification.model.ImgUrlRequest;


public interface ISlack {
	public void sendNotification(String message, String userName, String iconEmoji, List<ImgUrlRequest> attachments) throws Exception;
}
