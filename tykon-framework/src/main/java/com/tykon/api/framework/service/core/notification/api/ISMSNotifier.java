/**
 * 
 */
package com.tykon.api.framework.service.core.notification.api;

import com.tykon.api.framework.service.core.notification.model.NotificationSMSRequest;

/**
 * @author sachin
 *
 */
public interface ISMSNotifier {
	public void sendMessage(NotificationSMSRequest request) throws Exception;
}
