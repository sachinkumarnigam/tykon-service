/**
 * 
 */
package com.tykon.api.framework.service.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tykon.api.framework.service.core.notification.impl.SMSNotifier;
import com.tykon.api.framework.service.core.notification.model.NotificationSMSRequest;

/**
 * @author sachin
 *
 */
@Aspect
@Component
public class SendSMSAspect {

	protected final Logger logger = LoggerFactory.getLogger(SendSMSAspect.class);

	@Autowired
	SMSNotifier notifier;

	@Pointcut("@annotation(com.hp.api.framework.service.core.annotation.SendSMS)")
	public void sendSMS() {
	}

	@Pointcut("within(com.hp..*)")
	public void inHelloParent() {
	}

	@AfterReturning(pointcut = "@annotation(com.hp.api.framework.service.core.annotation.SendSMS)", returning = "notificationSMSRequest")
	public void sendSMS(NotificationSMSRequest notificationSMSRequest) throws Throwable {
		logger.info("Started sending mail....");
		try {
			notifier.sendMessage(notificationSMSRequest);
		} catch (Exception e) {
			logger.error("Error while sending mail ", e);
		}
	}
}
