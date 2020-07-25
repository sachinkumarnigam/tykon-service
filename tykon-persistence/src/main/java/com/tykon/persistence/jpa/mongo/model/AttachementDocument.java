package com.tykon.persistence.jpa.mongo.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class AttachementDocument {

	@Field("Url")
	private String url;

	@Field("Name")
	private String name;

	@Field("Dimensions")
	private DimensionsDocument dimensions;

	public AttachementDocument() {

	}

	public AttachementDocument(String url, String name, DimensionsDocument dimensions) {
		super();
		this.url = url;
		this.name = name;
		this.dimensions = dimensions;
	}

	public AttachementDocument(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DimensionsDocument getDimensions() {
		return dimensions;
	}

	public void setDimensions(DimensionsDocument dimensions) {
		this.dimensions = dimensions;
	}

}