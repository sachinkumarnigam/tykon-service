/**
 *
 */
package com.tykon.api.framework.service.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sachin
 *
 */
public class BaseService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final static String CACHE_KEY_SEPERATOR = "_";
	protected static long DEFAULT_TTL = 4;

	protected ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	protected UserDetailsServiceImpl userDetailsService;

	protected List<ObjectId> convertFromStringToObjectId(Set<String> ids) {
		List<ObjectId> list = new ArrayList<>();
		for (String id : ids) {
			try {
				list.add(new ObjectId(id));
			} catch (Exception e) {
				logger.error("got invalid object id for id : " + id, e);
			}
		}
		return list;
	}
}
