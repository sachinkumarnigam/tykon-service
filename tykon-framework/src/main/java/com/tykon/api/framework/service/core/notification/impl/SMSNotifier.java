/**
 * 
 */
package com.tykon.api.framework.service.core.notification.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tykon.api.framework.connector.rest.notification.sms.SMSNotifierClient;
import com.tykon.api.framework.service.core.notification.api.ISMSNotifier;
import com.tykon.api.framework.service.core.notification.model.NotificationSMSRequest;

/**
 * @author sachin
 *
 */
@Service
public class SMSNotifier implements ISMSNotifier {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.sms.base.url}")
	private String defaultFromEmailid;

	@Autowired
	protected SMSNotifierClient smsNotifierClient;

	ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 
	 */
	public SMSNotifier() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Async
	public void sendMessage(NotificationSMSRequest request) throws Exception {

		if (request.getMessage() == null || request.getMessage().isEmpty() || request.getMobileNumber() == null
				|| request.getMobileNumber().isEmpty()) {
			throw new Exception("No recipient or message to send sms");
		}

		String msgBody = this.getMessage(request);
		Map<String, String> variables = new HashMap<>();
		variables.put("mobiles", request.getMobileNumber());
		variables.put("message", msgBody);

		try {
			smsNotifierClient.get(variables);
		} catch (Exception e) {
			logger.error("exception in creating mail helper while sending with request "
					+ objectMapper.writeValueAsString(request), e);
		}

	}

	private String getMessage(NotificationSMSRequest request) {
		String str = MessageFormat.format(request.getMessage(), request.getVariableValues());
		return str;
		// return HtmlEncoder.text(str);
	}

}
