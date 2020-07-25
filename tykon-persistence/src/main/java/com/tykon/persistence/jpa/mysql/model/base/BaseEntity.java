package com.tykon.persistence.jpa.mysql.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@MappedSuperclass
@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
    @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class)
})
public class BaseEntity {


	private String createdBy;
	
	private String updatedBy;
	
	private String deletedBy;

	private Date createdAt;
	
	private Date updatedAt;
	
	private Date deletedAt;

	private Boolean isActive;

	private Boolean isDeleted;

	@Column(name = "created_by")
	public  String getCreatedBy() {
		return createdBy;
	}

	public  void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	public  Date getCreatedAt() {
		return createdAt;
	}

	public  void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	public  Date getUpdatedAt() {
		return updatedAt;
	}

	public  void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", length = 19)
	public  Date getDeletedAt() {
		return deletedAt;
	}

	public  void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Column(name = "is_active")
	public  Boolean getIsActive() {
		return isActive;
	}

	public  void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "is_deleted")
	public  Boolean getIsDeleted() {
		return isDeleted;
	}

	public  void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "updated_by")
	public  String getUpdatedBy() {
		return updatedBy;
	}

	public  void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "deleted_by")
	public  String getDeletedBy() {
		return deletedBy;
	}

	public  void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	
	
	
}
