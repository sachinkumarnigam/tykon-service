package com.tykon.api.framework.service.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

public class MongoCommonUtil {

	public static String newObjectIdString() {
		return new ObjectId().toString();
	}

	public static ObjectId convertStringToObjectId(String field) {
		return (field != null) ? new ObjectId(field) : null;
	}

	public static String convertObjectIdToString(ObjectId field) {
		return (field != null) ? field.toString() : null;
	}

	/**
	 * convert list of object ids to list of string
	 *
	 * @param listStringIds
	 * @return
	 */
	public static List<ObjectId> convertListOfStringToListOfObjectId(List<String> listStringIds) {
		List<ObjectId> listOfObjectIds = new ArrayList<ObjectId>();
		if (!CommonUtil.isEmpty(listStringIds)) {
			listStringIds.forEach(ids -> {
				listOfObjectIds.add(convertStringToObjectId(ids));
			});
			return listOfObjectIds;
		}
		return null;
	}

	public static List<ObjectId> getListOfStringToListOfObjectId(List<String> listStringIds) {
		List<ObjectId> listOfObjectIds = new ArrayList<ObjectId>();
		if (!CommonUtil.isEmpty(listStringIds)) {
			listStringIds.forEach(ids -> {
				listOfObjectIds.add(convertStringToObjectId(ids));
			});
		}
		return listOfObjectIds;
	}

	/**
	 * convert list of object ids to list of string
	 *
	 * @param listStringIds
	 * @return
	 */
	public static Set<ObjectId> convertSetOfStringToSetOfObjectId(Set<String> listStringIds) {
		Set<ObjectId> listOfObjectIds = new HashSet<ObjectId>();
		if (!CommonUtil.isEmpty(listStringIds)) {
			listStringIds.forEach(ids -> {
				listOfObjectIds.add(convertStringToObjectId(ids));
			});
			return listOfObjectIds;
		}
		return null;
	}
	/**
	 * convert list of string ids to list of object
	 *
	 * @param listOfObjectIds
	 * @return
	 */
	public static List<String> convertListOfObjectIdToListOfString(List<ObjectId> listOfObjectIds) {
		List<String> listOfStringIds = new ArrayList<String>();
		if (!CommonUtil.isEmpty(listOfObjectIds)) {
			listOfObjectIds.forEach(ids -> {
				listOfStringIds.add(convertObjectIdToString(ids));
			});
			return listOfStringIds;
		}
		return null;
	}

	/**
	 * convert list of string ids to list of object
	 *
	 * @param listOfObjectIds
	 * @return
	 */
	public static Set<String> convertSetOfObjectIdToSetOfString(Set<ObjectId> listOfObjectIds) {
		Set<String> listOfStringIds = new HashSet<String>();
		if (!CommonUtil.isEmpty(listOfObjectIds)) {
			listOfObjectIds.forEach(ids -> {
				listOfStringIds.add(convertObjectIdToString(ids));
			});
			return listOfStringIds;
		}
		return null;
	}
	public static ObjectId newObjectId() {
		return new ObjectId();
	}

	/**
	 * isValid method checks the string is valid or not
	 *
	 * @param hexString
	 * @return
	 */
	public static boolean isValidObjectId(String hexString) {
		if (hexString == null) {
			return false;
		}

		int len = hexString.length();
		if (len != 24) {
			return false;
		}

		for (int i = 0; i < len; i++) {
			char c = hexString.charAt(i);
			if (c >= '0' && c <= '9') {
				continue;
			}
			if (c >= 'a' && c <= 'f') {
				continue;
			}
			if (c >= 'A' && c <= 'F') {
				continue;
			}

			return false;
		}

		return true;
	}

	/**
	 * isValidList method to check the string list valid or not
	 *
	 * @param hexStringList
	 * @return
	 */
	public static boolean isValidObjectIdList(List<String> hexStringList) {
		boolean result = true;
		if (hexStringList == null || hexStringList.isEmpty()) {
			return false;
		}
		for (String hexString : hexStringList) {
			result = isValidObjectId(hexString);
		}
		return result;
	}
}
