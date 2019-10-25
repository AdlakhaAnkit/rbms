package com.medici.rbms;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medici.rbms.entities.RoomsBooking;
import com.medici.rbms.service.RoomsBookingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class RoomsBookingController- It provides functionality: 1. Get all
 * available rooms /rooms 2. Booking request /booking 3. Get All Bookings
 * /bookings
 */
@RestController
@RequestMapping("/rbms")
public class RoomsBookingController {

	/** The rooms booking service. */
	private final RoomsBookingService roomsBookingService;

	/**
	 * Instantiates a new rooms booking controller.
	 *
	 * @param roomsBookingService the rooms booking service
	 */
	public RoomsBookingController(RoomsBookingService roomsBookingService) {
		this.roomsBookingService = roomsBookingService;
	}

	/**
	 * Booking Request.
	 *
	 * @param roomsBookingRequest the rooms booking request
	 * @return the rooms booking
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "Book a room", response = RoomsBooking.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Booking Done| Success | OK"),
			@ApiResponse(code = 404, message = "Rooms Not Available"),
			@ApiResponse(code = 400, message = "Bad Request, Input not valid") })
	@PostMapping("/booking")
	public RoomsBooking bookRooms(@Validated @RequestBody RoomsBookingRequest roomsBookingRequest) throws Exception {
		return roomsBookingService.bookRooms(roomsBookingRequest);
	}

	/**
	 * Fetch rooms availability.
	 *
	 * @return the list
	 */
	@ApiOperation(value = "Get All Rooms Availability", response = RoomsResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK") })
	@GetMapping("/rooms")
	public List<RoomsResponse> fetchAllRoomsAvailability() {
		return roomsBookingService.fetchAllRoomsAvailability();
	}

	/**
	 * Fetch all bookings.
	 *
	 * @return the list
	 */
	@ApiOperation(value = "Get All Bookings", response = RoomsBooking.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success | OK") })
	@GetMapping("/bookings")
	public List<RoomsBooking> fetchAllBookings() {
		return roomsBookingService.fetchAllBookings();
	}

}
