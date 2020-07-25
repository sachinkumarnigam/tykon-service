/**
 * 
 */
package com.tykon.api.framework.service.core.notification.model;

import com.tykon.api.framework.service.core.notification.api.IRecipient;

/**
 * @author sachin
 *
 */
public class Recipient implements IRecipient{
	
	private final String recipientAddress;

	public Recipient(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	/**
	 * @return the recipientAddress
	 */
	public String getRecipientAddress() {
		return recipientAddress;
	}

}
