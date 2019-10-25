package com.medici.rbms.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The Class BaseEntity.
 * 
 * @author ankadlak
 */
@MappedSuperclass
public class BaseEntity {

	/** The entity status. */
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ENTITY_STATUS")
	private EntityStatus entityStatus = EntityStatus.ACTIVE;

	/** The created by. */
	@Column(name = "CREATED_BY", insertable = true, updatable = false)
	private Long createdBy;

	/** The created date. */
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	/** The modified by. */
	@Column(name = "MODIFIED_BY", insertable = false, updatable = true)
	private Long modifiedBy;

	/** The modified date. */
	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	/**
	 * On create.
	 */
	@PrePersist
	void onCreate() {
		this.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * On persist.
	 */
	@PreUpdate
	void onPersist() {
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * Gets the entity status.
	 *
	 * @return the entity status
	 */
	public EntityStatus getEntityStatus() {
		return entityStatus;
	}

	/**
	 * Sets the entity status.
	 *
	 * @param entityStatus the new entity status
	 */
	public void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param timestamp the new created date
	 */
	public void setCreatedDate(Timestamp timestamp) {
		this.createdDate = timestamp;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
