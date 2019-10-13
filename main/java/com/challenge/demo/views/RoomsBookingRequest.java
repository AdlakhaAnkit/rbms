package com.challenge.demo.views;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import io.swagger.annotations.ApiModel;

/**
 * 
 * Class RoomsBookingRequest- Represent the booking request object.
 * @author ankit
 *
 */
@ApiModel(value = "BookingRequest")
public class RoomsBookingRequest {

	/**
	 * The Room Type Id
	 */
	private Long roomTypeId;

	/**
	 * Room Booking Start Date
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate;

	/**
	 * Room Booking End Date
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate;

	/**
	 * @return room type id
	 */
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	/**
	 * @param roomTypeId
	 */
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * @return booking request start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return booking request end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
