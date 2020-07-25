package com.tykon.entity.request.base;

public enum SearchType {
	NONE(0),
	FILTER(10);
	
	
	public int value;

	private SearchType(int value) {
		this.value = value;
	}

}
