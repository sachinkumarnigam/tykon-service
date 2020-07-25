/**
 * 
 */
package com.tykon.api.framework.service.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sachin
 *
 */
public class ImageUtil {

	protected static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 
	 */
	public ImageUtil() {
		// TODO Auto-generated constructor stub
	}

	static public String guessContentTypeFromStream(InputStream is) throws IOException {
		String contentType = URLConnection.guessContentTypeFromStream(is);
		if (contentType == null) {
			is.mark(16);
			int c1 = is.read();
			int c2 = is.read();
			int c3 = is.read();
			int c4 = is.read();
			logger.info("contentType From URLConnection is null explicity checking further c1 = " + c1 + " c2 " + c2
					+ " c3 " + c3 + " c4 " + c4);
			if (c1 == 0xFF && c2 == 0xD8 && c3 == 0xFF) {
				if (c4 == 226) {
					contentType = "image/jpg";
				}
			}
		}
		return contentType;
	}

	/**
	 * @param file
	 * @return File Extension from file Object
	 */
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @param file
	 * @return name of the file withoutExtension
	 */
	public static String getFileNameWithOutExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		} else {
			return "";
		}
	}

	/**
	 * find the extension using a utility class provided by Apache Commons IO
	 * library
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtensionByApacheCommonLib(String filename) {
		return FilenameUtils.getExtension(filename);
	}
}
