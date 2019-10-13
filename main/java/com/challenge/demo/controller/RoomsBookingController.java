package com.challenge.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.demo.service.RoomsBookingService;
import com.challenge.demo.views.RoomsBookingRequest;
import com.challenge.demo.views.RoomsBookingResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class RoomsBookingController.
 */
@RestController
@Api(value = "/", produces = "application/json")
@RequestMapping("/rbms")
public class RoomsBookingController {

	/** The log. */
	private Logger log = LoggerFactory.getLogger(RoomsBookingController.class);

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

	@ApiOperation(value = "Book a Room")
	@RequestMapping(method = RequestMethod.POST)
	public void bookRooms(@RequestBody RoomsBookingRequest roomsBookingRequest) throws Exception {
		roomsBookingService.bookRooms(roomsBookingRequest);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<RoomsBookingResponse> fetchAllRoomBookings() {
		return roomsBookingService.fetchAllRoomBookings();
	}

}
