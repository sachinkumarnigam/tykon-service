/**
 * 
 */
package com.tykon.api.framework.service.core.notification.api;

import com.tykon.api.framework.service.core.notification.model.NotificationEmailRequest;

/**
 * @author sachin
 *
 */
public interface INotifier {
	public void sendMail(NotificationEmailRequest request) throws Exception;
}
