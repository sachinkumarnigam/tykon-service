package com.tykon.api.framework.connector.rest.notification.sms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tykon.api.framework.connector.rest.base.RestClient;

@Service
public class SMSNotifierClient {

	@Autowired
	RestClient restClient;

	@Value("${app.sms.base.url}")
	String url;

	public void get(Map<String, String> queryParams) {
		restClient.get(url, queryParams, String.class);
	}
}
