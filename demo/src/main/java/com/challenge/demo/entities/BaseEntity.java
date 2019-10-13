package com.challenge.demo.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ENTITY_STATUS")
	private EntityStatus entityStatus = EntityStatus.ACTIVE;

	@Column(name = "CREATED_BY", insertable = true, updatable = false)
	private Long createdBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "MODIFIED_BY", insertable = false, updatable = true)
	private Long modifiedBy;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@PrePersist
	void onCreate() {
		this.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	void onPersist() {
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}

	public EntityStatus getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp timestamp) {
		this.createdDate = timestamp;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
