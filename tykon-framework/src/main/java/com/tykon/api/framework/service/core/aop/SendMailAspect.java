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

import com.tykon.api.framework.service.core.notification.impl.EmailNotifier;
import com.tykon.api.framework.service.core.notification.model.NotificationEmailRequest;


/**
 * @author sachin
 *
 */
@Aspect
@Component
public class SendMailAspect {

	protected final Logger logger = LoggerFactory.getLogger(SendMailAspect.class);
	
	@Autowired
	EmailNotifier notifier;

	@Pointcut("@annotation(com.hp.api.framework.service.core.annotation.SendMail)")
    public void sendMail() {}

	@Pointcut("within(com.hp..*)")
    public void inHelloParent() {}
	
	@AfterReturning(pointcut="@annotation(com.hp.api.framework.service.core.annotation.SendMail)", returning="notificationEmailRequest")
	public void sendMail(NotificationEmailRequest notificationEmailRequest) throws Throwable {
		logger.info("Started sending mail....");
		try {
			notifier.sendMail(notificationEmailRequest);
		} catch(Exception e) {
			logger.error("Error while sending mail ",e);
		}
	}
}
