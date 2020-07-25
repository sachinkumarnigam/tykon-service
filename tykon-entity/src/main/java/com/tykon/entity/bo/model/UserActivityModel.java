package com.tykon.entity.bo.model;

import java.util.Date;

public class UserActivityModel {
    private Long id;
    private String visitorId;
    private String url;
    private String api;
    private Long customerProfileId;
    private Date crdt;
    private String partnerCampaignId;
    private Double userLatitude;
    private Double userLongitude;
    private String userCity;
    private String userCountry;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public Long getCustomerProfileId() {
		return customerProfileId;
	}
	public void setCustomerProfileId(Long customerProfileId) {
		this.customerProfileId = customerProfileId;
	}
	public Date getCrdt() {
		return crdt;
	}
	public void setCrdt(Date crdt) {
		this.crdt = crdt;
	}
	public String getPartnerCampaignId() {
		return partnerCampaignId;
	}
	public void setPartnerCampaignId(String partnerCampaignId) {
		this.partnerCampaignId = partnerCampaignId;
	}
	public Double getUserLatitude() {
		return userLatitude;
	}
	public void setUserLatitude(Double userLatitude) {
		this.userLatitude = userLatitude;
	}
	public Double getUserLongitude() {
		return userLongitude;
	}
	public void setUserLongitude(Double userLongitude) {
		this.userLongitude = userLongitude;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

}
