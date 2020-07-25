package com.tykon.api.framework.service.core.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${aws.access.key}")
	private String awsAccessKey;

	@Value("${aws.secreatkey}")
	private String awsSecreatkey;

	@Value("${sqs.region}")
	private String sqsRegion;

	private static AmazonSQS sqs;

	private static ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	public void init() {
		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(awsAccessKey, awsSecreatkey));
		sqs = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(sqsRegion).build();
	}



	/**
	 * prepared SendMessageBatchRequest from Notification Request
	 * 
	 * @param notificationEventRequest
	 * @return
	 */
	
}
