package com.tykon.entity.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DimensionsModel {
	@JsonProperty("width")
	private Double width;

	@JsonProperty("height")
	private Double height;
}
