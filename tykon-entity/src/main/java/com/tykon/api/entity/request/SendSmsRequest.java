package com.tykon.api.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tykon.entity.request.base.BaseRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsRequest extends BaseRequest {

	@JsonProperty(value = "mobile")
	private String mobile;

	@JsonProperty("text_message")
	private String textMessage;


}

