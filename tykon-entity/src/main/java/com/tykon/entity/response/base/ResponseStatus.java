/**
 * 
 */
package com.tykon.entity.response.base;

/**
 * @author amleshsinha
 *
 */
public enum ResponseStatus {

	
	SUCCESS(200),
	FAILED(400),
	INVALID(401),
	UNAUTHENTICATED(403);
	
	
	public int value;
	
	private ResponseStatus(int value) {
		this.value = value;
	}
}
