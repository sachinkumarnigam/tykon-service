/**
 * 
 */
package com.tykon.api.framework.connector.rest.base;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author sachin
 *
 */
@Service
public class RestClient {

	protected final Logger logger = LoggerFactory.getLogger(RestClient.class);

	@Autowired
	private RestTemplate restTemplate;

	private MediaType contentMediaType = MediaType.APPLICATION_JSON;
	private MediaType acceptMediaType = MediaType.APPLICATION_JSON;

	@Value("${hp.api.read.timeout}")
	private int readTimeOut;

	@Value("${hp.api.connection.timeout}")
	private int connectionTimeOut;

	@PostConstruct
	public void postInit() {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeOut * 1000);
		httpComponentsClientHttpRequestFactory.setConnectTimeout(connectionTimeOut * 1000);
		restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
	}

	public <S> S post(String url, Object request, Class<S> responseClass) {
		try {
			return restTemplate.postForObject(url, request, responseClass);
		} catch (Exception e) {
			logger.error("Exception while connecting API " + url + " " + request, e);
			throw e;
		}
	}

	public <S> S postWithHeader(String url, Object request, Class<S> responseClass, HttpHeaders httpHeaders) {
		HttpEntity<?> entity = new HttpEntity<>(request, httpHeaders);
		return restTemplate.postForObject(url, entity, responseClass);
	}

	public <S> S get(String url, Map<String, ?> queryParams, Class<S> responseClass) {
		return restTemplate.getForObject(url, responseClass, queryParams);
	}

	public <S> S getWithHeader(String url, Map<String, ?> queryParams, Class<S> responseClass,
			HttpHeaders httpHeaders) {
		String strUrl = buildTemplateUrl(url, queryParams.keySet());
		HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
		ResponseEntity<S> response = restTemplate.exchange(strUrl, HttpMethod.GET, entity, responseClass, queryParams);
		return response.getBody();
	}

	public <S> S getWithDynamicParams(String url, Map<String, ?> queryParams, Class<S> responseClass) {

		String strUrl = buildTemplateUrl(url, queryParams.keySet());
		return restTemplate.getForObject(strUrl, responseClass, queryParams);
	}

	public <S> S get(String url, Class<S> responseClass) {
		return restTemplate.getForObject(url, responseClass);
	}

	public MediaType getContentMediaType() {
		return contentMediaType;
	}

	public void setContentMediaType(MediaType contentMediaType) {
		this.contentMediaType = contentMediaType;
	}

	public MediaType getAcceptMediaType() {
		return acceptMediaType;
	}

	public void setAcceptMediaType(MediaType acceptMediaType) {
		this.acceptMediaType = acceptMediaType;
	}

	String buildTemplateUrl(String path, Set<String> queryParameters) {
		StringBuilder builder = new StringBuilder();
		builder.append(path).append("?");
		for (String queryKey : queryParameters) {
			builder.append(queryKey).append("={").append(queryKey).append("}&");
		}
		return builder.toString();
	}

	/**
	 * Regarding Online Classes
	 */

	public <S> S patchWithHeader(String url, Object request, Class<S> responseClass, HttpHeaders httpHeaders) {
		HttpEntity<?> entity = new HttpEntity<>(request, httpHeaders);
		return restTemplate.patchForObject(url, entity, responseClass);
	}

	public <S> S deleteWithHeader(String url, Map<String, ?> queryParams, Class<S> responseClass,
			HttpHeaders httpHeaders) {
		HttpEntity<?> request = new HttpEntity<Object>(httpHeaders);
		ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.DELETE, request, responseClass);
		return response.getBody();
	}

	public void putWithHeader(String url, Object request, HttpHeaders httpHeaders) {
		HttpEntity<?> entity = new HttpEntity<>(request, httpHeaders);
		restTemplate.put(url, entity);
	}
}
