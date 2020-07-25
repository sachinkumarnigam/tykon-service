package com.tykon.api.framework.service.core.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class S3Upload {

	private static final String AWS_ACCESSKEY = "AKIAWZ2TDGY34ZSALLC4";
	private static final String AWS_SECREATKEY = "W6TTML/+9X21gq63Lmt526dIWz3kplsehtxMBL9u";
	private static final String S3_BUCKETNAME = "hello-parent-test";

	private static AmazonS3 s3client = null;

	public static String uploadInS3Bucket(String bucketName, String filePath, InputStream inputStream,
			String contentType) {
		byte[] contents = new byte[0];
		try {
			contents = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream stream = new ByteArrayInputStream(contents);
		if (StringUtils.isEmpty(bucketName)) {
			bucketName = S3_BUCKETNAME;
		}
		ObjectMetadata data = new ObjectMetadata();
		data.setContentType(contentType);
		data.setContentLength(contents.length);
		getS3clientObject().putObject(bucketName, filePath, stream, data);
		return getS3clientObject().getUrl(bucketName, filePath).toString();
	}

	private static AmazonS3 getS3clientObject() {
		if (s3client == null) {
			AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESSKEY, AWS_SECREATKEY);
			AmazonS3 s3clientObj = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1)
					.build();
			s3client = s3clientObj;
		}
		return s3client;
	}

	public static void main(String a[]) {
		try {
			File file = new File("/Users/ashishsrivastava/Downloads/javaagent_sample.png");
			InputStream targetStream = new FileInputStream(file);
			uploadInS3Bucket(null, null, targetStream, "image/png");
			System.out.println("Upload image by ashish: ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
