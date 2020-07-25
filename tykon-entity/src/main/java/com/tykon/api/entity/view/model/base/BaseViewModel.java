package com.tykon.api.entity.view.model.base;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class BaseViewModel {

	private String createdBy;

	private String updatedBy;

	private String deletedBy;

	private Date createdAt;

	private Date updatedAt;

	private Date deletedAt;

	private Boolean isActive;

	private Boolean isDeleted;

	@JsonIgnore
	private Date cachePutTime;

}
