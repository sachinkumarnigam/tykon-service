package com.tykon.api.framework.staticdata.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticDataCache {

	private static StaticData staticData = new StaticData();

	private static ObjectMapper mapper = new ObjectMapper();

	protected final static Logger logger = LoggerFactory.getLogger(StaticDataCache.class);

	static {
		System.out.println("Initiating cache static data.");
		try {
			String json = getJsonFromResources();
			staticData = mapper.readValue(json, StaticData.class);
		} catch(IOException e) {
			logger.error("error getting static data", e);
		}
		System.out.println("Completed caching static data.");
		System.out.println(staticData);
	}
	public static StaticData getStaticData() {
		return staticData;
	}

	private static String getJsonFromResources() throws IOException {
		String fileName = "classpath:data/StaticData.json";
		InputStream file = ResourceUtils.getURL(fileName).openStream();
		return getContent(file);

	}

	private static String getContent(InputStream file) throws IOException {

		if (file == null) return null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		return sb.toString();
	}

}
