package com.tykon.api.framework.service.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tykon.api.framework.service.core.notification.impl.SlackNotifier;
import com.tykon.api.framework.service.core.notification.model.SlackNotificationRequest;



@Aspect
@Component
public class SendSlackNotificationAspect {
protected final Logger logger = LoggerFactory.getLogger(SendSlackNotificationAspect.class);
	
	@Autowired
	SlackNotifier notifier;
	
	@Pointcut("@annotation(com.hp.api.framework.service.core.annotation.SendSlackNotification)")
    public void sendSlackNotification() {}

	@Pointcut("within(com.hp..*)")
    public void inHelloParent() {}
	
	@AfterReturning(pointcut="@annotation(com.hp.api.framework.service.core.annotation.SendSlackNotification)", returning="slackNotificationRequest")
	public void sendSlackNotification(SlackNotificationRequest slackNotificationRequest) throws Throwable {
		logger.info("Started sending SlackNotification....");
		try {
			if(slackNotificationRequest == null) {
				return;
			}else {
				notifier.sendNotification(slackNotificationRequest.getMessage(), slackNotificationRequest.getUserName(), slackNotificationRequest.getIconEmoji(), slackNotificationRequest.getAttachments());
			}
		} catch(Exception e) {
			logger.error("Error while sending slack notification ",e);
		}
	}
}
