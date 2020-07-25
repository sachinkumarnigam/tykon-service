package com.tykon.entity.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tykon.entity.common.model.DimensionsModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttachmentModel {

	@JsonProperty("url")
	private String url;

	@JsonProperty("name")
	private String name;

	@JsonProperty("dimensions")
	private DimensionsModel dimensionsModel;

	public AttachmentModel(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}

}
