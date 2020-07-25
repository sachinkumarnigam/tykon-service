/**
 * 
 */
package com.tykon.api.framework.service.core.exception;

/**
 * @author sachin
 *
 */
public class AppCommonException extends Exception {

	/**
	 * 
	 */
	public AppCommonException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public AppCommonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public AppCommonException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AppCommonException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AppCommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
