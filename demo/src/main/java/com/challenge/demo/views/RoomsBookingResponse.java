package com.challenge.demo.views;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ankit
 *
 */
public class RoomsBookingResponse {

	Long id;

	String roomType;

	List<LocalDate> bookedDates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public List<LocalDate> getBookedDates() {
		return bookedDates;
	}

	public void setBookedDates(List<LocalDate> bookedDates) {
		this.bookedDates = bookedDates;
	}

}
