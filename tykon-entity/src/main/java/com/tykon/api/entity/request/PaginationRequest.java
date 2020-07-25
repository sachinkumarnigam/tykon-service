package com.tykon.api.entity.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tykon.entity.request.base.BaseRequest;

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
public class PaginationRequest extends BaseRequest {

	@JsonProperty(value = "page_no")
	@Min(value = 1)
	private int pageNumber = 1;

	@JsonProperty(value = "page_size")
	@Min(value = 1)
	@Max(value = 250)
	private int pageSize = 10;
}
