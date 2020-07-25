/**
 *
 */
package com.tykon.persistence.jpa.mongo.model.base;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author SachinNigam
 *
 */
public abstract class AuditEntity {

	@Id
	private String id;

	@CreatedDate
	@Field("CreatedAt")
	private Date createdAt;

	@CreatedBy
	@Field("CreatedBy")
	private String createdBy;

	@LastModifiedDate
	@Field("UpdatedAt")
	private Date updatedAt;

	@LastModifiedBy
	@Field("LastModifiedBy")
	private String lastModifiedBy;

	@Transient
	private Date cachePutTime;

	@Transient
	private Date cacheExpiryTime;

	@Transient
	private boolean beingRefreshed;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public final Date getCachePutTime() {
		return cachePutTime;
	}

	public final void setCachePutTime(Date cachePutTime) {
		this.cachePutTime = cachePutTime;
	}

	public final Date getCacheExpiryTime() {
		return cacheExpiryTime;
	}

	public final void setCacheExpiryTime(Date cacheExpiryTime) {
		this.cacheExpiryTime = cacheExpiryTime;
	}

	public final boolean isBeingRefreshed() {
		return beingRefreshed;
	}

	public final void setBeingRefreshed(boolean beingRefreshed) {
		this.beingRefreshed = beingRefreshed;
	}

}
