package com.tykon.entity.request.base;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SearchEntityRequest extends BaseRequest{

	private String q;
	
	private int pageNumber;
	
	private int pageSize;
	
	private String deviceType;
	
	private String packetType;

	private int daysSince = 0;
	
	private String schoolId;
	
	private String classId;

	private String classSectionId;

	private String studentId;
	
	private String deviceTypeId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = EntityConstant.DATE_PATTERN, timezone = EntityConstant.DATE_TIMEZONE)
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = EntityConstant.DATE_PATTERN, timezone = EntityConstant.DATE_TIMEZONE)
	private Date endDate;

	@NotBlank(message = "sortBy|{org.hibernate.validator.constraints.NotBlank.message}")
	private String sortBy;
	
	@NotBlank(message = "sortDirection|{org.hibernate.validator.constraints.NotBlank.message}")
	private String sortDirection;
	
	@JsonIgnore
	private boolean getAllRecords = false;

	private boolean getUnMappedOnly = false;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPacketType() {
		return packetType;
	}

	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}

	public int getDaysSince() {
		return daysSince;
	}

	public void setDaysSince(int daysSince) {
		this.daysSince = daysSince;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	@JsonIgnore
	public boolean isGetAllRecords() {
		return getAllRecords;
	}

	@JsonIgnore
	public void setGetAllRecords(boolean getAllRecords) {
		this.getAllRecords = getAllRecords;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public boolean isGetUnMappedOnly() {
		return getUnMappedOnly;
	}

	public void setGetUnMappedOnly(boolean getUnMappedOnly) {
		this.getUnMappedOnly = getUnMappedOnly;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassSectionId() {
		return classSectionId;
	}

	public void setClassSectionId(String classSectionId) {
		this.classSectionId = classSectionId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
