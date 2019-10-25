package com.medici.rbms;

import java.time.LocalDate;
import java.util.List;

/**
 * Class RoomsBookingResponse- Represent the rooms availability response.
 * 
 * @author ankadlak
 *
 */
public class RoomsResponse {

	/** id. */
	Long id;

	/** roomType. */
	String roomType;

	/** bookedDates. */
	List<LocalDate> bookedDates;

	/**
	 * Gets the id.
	 *
	 * @return id
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
	 * @return roomType
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

	/**
	 * Gets the booked dates.
	 *
	 * @return bookedDates
	 */
	public List<LocalDate> getBookedDates() {
		return bookedDates;
	}

	/**
	 * Sets the booked dates.
	 *
	 * @param bookedDates the new booked dates
	 */
	public void setBookedDates(List<LocalDate> bookedDates) {
		this.bookedDates = bookedDates;
	}

}
