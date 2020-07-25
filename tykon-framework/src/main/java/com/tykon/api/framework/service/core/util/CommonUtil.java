package com.tykon.api.framework.service.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

	protected static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection collection) {
		return (collection == null) || collection.isEmpty();
	}

	public static boolean isEmpty(String str) {
		return (str == null) || str.isEmpty();
	}

	/**
	 * Generates statement of lists of ids separated by delimiter.
	 * 
	 * @param ids
	 * @param delimiter
	 * @return delimiter separated ids
	 */
	public static String getDelimitedStatementOfListOfIds(Collection<? extends Object> ids, String delimiter) {
		if (CommonUtil.isEmpty(ids) || StringUtils.isEmpty(delimiter)) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			for (Object id : ids) {
				if (id != null && !sb.toString().contains(id.toString())) {
					if (sb.toString().equals("")) {
						sb.append(id);
					} else {
						sb.append(delimiter).append(id);
					}
				}
			}
			return sb.toString();
		}
	}

	// Generic function to convert set to list
	public static <T> List<T> convertToList(Set<T> set) {
		return new ArrayList<>(set);
	}

	// Generic function to convert list to set
	public static <T> Set<T> convertToSet(List<T> list) {
		// create an empty set
		Set<T> items = new HashSet<>();
		// push each element in the list into the set
		for (T e : list)
			items.add(e);
		// return the set
		return items;
	}

	/**
	 * validate given input string is alphanumeric or not
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		return s.matches(pattern);
	}

	public static void main(String[] args) {
		String ss = "''";
		System.out.println(isAlphaNumeric(ss));
	}

	/**
	 * capitalize the first letter of a string
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if (str == null)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * validate given youtube url
	 * 
	 * @param youtubeUrl
	 * @return
	 */
	public static boolean isYoutubeVedioUrl(String youtubeUrl) {
		String youtubeRegx = ".*(youtube|youtu.be).*";
		if (youtubeUrl.matches(youtubeRegx) && youtubeUrl.contains("watch?v=")) {
			return true;
		}
		return false;
	}
}
