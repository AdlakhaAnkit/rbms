package com.medici.rbms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Rooms.
 * 
 * @author ankadlak
 */
@Entity(name = "ROOMSTYPE")
public class RoomsType {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The room type. */
	@Column(name = "ROOM_TYPE")
	String roomType;

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
	 * Gets the room type.
	 *
	 * @return the room type
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Sets the room type.
	 *
	 * @param roomType the new room type
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

}
