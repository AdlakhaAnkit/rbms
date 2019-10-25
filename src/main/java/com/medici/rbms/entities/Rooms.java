package com.medici.rbms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The Class Rooms.
 * 
 * @author ankadlak
 */
@Entity(name = "ROOMS")
public class Rooms {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The room type. */
	@ManyToOne(fetch = FetchType.EAGER)
	RoomsType roomType;

	/** The room number. */
	@Column(name = "ROOM_NUMBER")
	Long roomNumber;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the room number.
	 *
	 * @return the room number
	 */
	public Long getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Sets the room number.
	 *
	 * @param roomNumber the new room number
	 */
	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Get Room Type.
	 *
	 * @return the room type
	 */
	public RoomsType getRoomType() {
		return roomType;
	}

	/**
	 * Sets Room Type.
	 *
	 * @param roomType the new room type
	 */
	public void setRoomType(RoomsType roomType) {
		this.roomType = roomType;
	}
}
