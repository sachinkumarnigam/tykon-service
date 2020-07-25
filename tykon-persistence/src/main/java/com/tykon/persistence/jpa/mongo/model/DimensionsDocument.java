package com.tykon.persistence.jpa.mongo.model;

import org.springframework.data.mongodb.core.mapping.Field;



public class DimensionsDocument {

	@Field("Width")
	private Integer width;

	@Field("Height")
	private Integer height;

	public DimensionsDocument() {
		
	}
	
	public DimensionsDocument(Integer width, Integer height) {
		super();
		this.width = width;
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
}