package com.medici.rbms;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.medici.rbms.validator.DateValidator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Class RoomsBookingRequest- Represent the booking request object.
 * 
 * @author ankadlak
 *
 */
@ApiModel(value = "BookingRequest", description = "Room Booking Request")
public class RoomsBookingRequest {

	/** The Room Type Id. */
	@ApiModelProperty(value = "Room Type Id")
	@NotNull(message = "Room Id is mandatory")
	private Long roomTypeId;

	/** Room Booking Start Date. */
	@ApiModelProperty(value = "Check-in Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateValidator(message = "Start date is mandatory, Only Today's or Future Date is allowed")
	private LocalDate startDate;

	/** Room Booking End Date. */
	@ApiModelProperty(value = "Check-out Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@DateValidator(message = "End date is mandatory, Only Future Date is allowed")
	private LocalDate endDate;

	/**
	 * Gets the room type id.
	 *
	 * @return room type id
	 */
	public Long getRoomTypeId() {
		return roomTypeId;
	}

	/**
	 * Sets the room type id.
	 *
	 * @param roomTypeId the new room type id
	 */
	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	/**
	 * Gets the start date.
	 *
	 * @return booking request start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return booking request end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
