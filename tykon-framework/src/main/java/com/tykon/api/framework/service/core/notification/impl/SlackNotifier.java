package com.tykon.api.framework.service.core.notification.impl;


import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tykon.api.framework.service.core.notification.api.ISlack;
import com.tykon.api.framework.service.core.notification.model.ImgUrlRequest;
import com.tykon.api.framework.service.core.notification.model.SlackNotificationRequest;

@Service
public class SlackNotifier implements ISlack{
	@Override
	public void sendNotification(String message, String userName, String iconEmoji, List<ImgUrlRequest> attachments) throws Exception {
		InetAddress ip = null;
		try {

			ip = InetAddress.getLocalHost();

		} catch (UnknownHostException e) {

			//do nothing
		}

		//if it s test instance trigger notification on different channel other than feed
		if(ip != null && ip.equals(InetAddress.getByName("172.31.27.180"))) {
			//#bigquery-etl-alerts channel
			String url = "https://hooks.slack.com/services/TRTRTRT/VVHGVH/jhkjhkjhkjhkj";	
			URL obj = new URL(url);
			ImgUrlRequest imgUrlRequest = attachments.get(0);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			SlackNotificationRequest request = new SlackNotificationRequest(message,userName, iconEmoji, imgUrlRequest);

			ObjectMapper mapper = new ObjectMapper();

			String urlParameters = mapper.writeValueAsString(request);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			con.getResponseCode();
		}
		else {

			String url = "https://hooks.slack.com/services/TRTRTRT/VGVHJJG/kjhkhkkhklhll";
			URL obj = new URL(url);
			ImgUrlRequest imgUrlRequest = attachments.get(0);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			SlackNotificationRequest request = new SlackNotificationRequest(message,userName, iconEmoji, imgUrlRequest);

			ObjectMapper mapper = new ObjectMapper();

			String urlParameters = mapper.writeValueAsString(request);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			con.getResponseCode();
		}
	}
}
