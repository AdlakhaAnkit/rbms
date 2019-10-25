package com.medici.rbms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medici.rbms.RoomsBookingRequest;
import com.medici.rbms.RoomsResponse;
import com.medici.rbms.constants.RBMSConstants;
import com.medici.rbms.entities.EntityStatus;
import com.medici.rbms.entities.Rooms;
import com.medici.rbms.entities.RoomsBooking;
import com.medici.rbms.entities.RoomsType;
import com.medici.rbms.exception.RoomsCustomException;
import com.medici.rbms.repositories.RoomsBookingRepository;
import com.medici.rbms.repositories.RoomsRepository;
import com.medici.rbms.repositories.RoomsTypeRepository;

/**
 * The Class RoomsBookingService- - It provides functionality: 1. Get all
 * available rooms 2. Booking request 3. Get All Bookings 4. Cancel a bookiing
 * 
 * @author ankadlak
 */
@Service
public class RoomsBookingService {

	/** The rooms repository. */
	private final RoomsRepository roomsRepository;

	/** The rooms booking repository. */
	private final RoomsBookingRepository roomsBookingRepository;

	/** The rooms type repository. */
	private final RoomsTypeRepository roomsTypeRepository;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(RoomsBookingService.class);

	/**
	 * Instantiates a new rooms booking service.
	 *
	 * @param roomsRepository        the rooms repository
	 * @param roomsBookingRepository the rooms booking repository
	 * @param roomsTypeRepository    the rooms type repository
	 */
	@Autowired
	public RoomsBookingService(RoomsRepository roomsRepository, RoomsBookingRepository roomsBookingRepository,
			RoomsTypeRepository roomsTypeRepository) {
		this.roomsBookingRepository = roomsBookingRepository;
		this.roomsRepository = roomsRepository;
		this.roomsTypeRepository = roomsTypeRepository;
	}

	/**
	 * Fetch all rooms availability with booking end date for a room is greater than
	 * equal to today's date
	 *
	 * @return the list
	 */
	public List<RoomsResponse> fetchAllRoomsAvailability() {
		log.info("START-->fetchAllRoomsAvailability");
		LocalDate today = LocalDate.now();
		List<RoomsBooking> response = roomsBookingRepository
				.findAllByEntityStatusAndEndDateGreaterThanEqual(EntityStatus.ACTIVE, today);
		Map<Long, List<LocalDate>> roomBookingDates = getRoomsBookings(response);
		List<RoomsResponse> responseRoomBookingList = new ArrayList<RoomsResponse>();
		Iterable<Rooms> roomsItr = roomsRepository.findAll();
		Iterator<Rooms> allRooms = roomsItr.iterator();
		while (allRooms.hasNext()) {
			RoomsResponse roomBooking = new RoomsResponse();
			Rooms room = allRooms.next();
			roomBooking.setId(room.getId());
			roomBooking.setRoomType(room.getRoomType().getRoomType());
			roomBooking.setBookedDates(roomBookingDates.get(room.getId()));
			responseRoomBookingList.add(roomBooking);
		}
		log.info("END-->fetchAllRoomsAvailability");
		return responseRoomBookingList;
	}

	/**
	 * Book a room
	 *
	 * @param roomsBookingRequest the rooms booking request
	 * @return the rooms booking
	 * @throws Exception the exception
	 */
	public RoomsBooking bookRooms(RoomsBookingRequest roomsBookingRequest) throws Exception {
		log.info("START-->bookRooms");
		if (roomsBookingRequest.getEndDate().isBefore(roomsBookingRequest.getStartDate())
				|| roomsBookingRequest.getEndDate().isEqual(roomsBookingRequest.getStartDate())) {
			log.error(
					"Invalid Dates:" + roomsBookingRequest.getEndDate() + " or " + roomsBookingRequest.getStartDate());
			throw new RoomsCustomException(HttpStatus.BAD_REQUEST, RBMSConstants.DATES_ERROR_MESSAGE);
		}
		// Fetch rooms type by type id
		// To DO - Caching mechanism can be added on this as this information will
		// change rarely
		Optional<RoomsType> roomType = roomsTypeRepository.findById(roomsBookingRequest.getRoomTypeId());
		RoomsType obj = roomType.get();
		RoomsBooking roomsBookingEntity = new RoomsBooking();
		// Fetch all rooms by room type id
		List<Rooms> rooms = roomsRepository.findAllByRoomTypeId(obj.getId());
		LocalDate today = LocalDate.now();
		// Fetch all active room bookings
		List<RoomsBooking> bookings = roomsBookingRepository
				.findAllByEntityStatusAndEndDateGreaterThanEqual(EntityStatus.ACTIVE, today);
		// Map with key as Room id and value as list of booked dates
		Map<Long, List<LocalDate>> roomBookingDates = getRoomsBookings(bookings);
		// List of dates between two dates
		List<LocalDate> requestedDates = getDates(roomsBookingRequest.getStartDate(), roomsBookingRequest.getEndDate());
		// Fetch rooms available for booking on the requested dates
		Map<LocalDate, Rooms> room = getAvailableRoom(rooms, roomBookingDates, requestedDates);
		roomsBookingEntity.setRooms(room);
		roomsBookingEntity.setEndDate(roomsBookingRequest.getEndDate());
		roomsBookingEntity.setStartDate(roomsBookingRequest.getStartDate());
		log.info("END-->bookRooms");
		return roomsBookingRepository.save(roomsBookingEntity);
	}

	/**
	 * Gets the available room for booking
	 *
	 * @param rooms            the rooms
	 * @param roomBookingDates the room booking dates
	 * @param requestedDates   the requested dates
	 * @return the available room
	 * @throws Exception the exception
	 */
	private Map<LocalDate, Rooms> getAvailableRoom(List<Rooms> rooms, Map<Long, List<LocalDate>> roomBookingDates,
			List<LocalDate> requestedDates) throws Exception {
		Map<LocalDate, Rooms> bookRooms = new HashMap<>();
		for (Rooms r : rooms) {
			if (roomBookingDates.get(r.getId()) == null) {
				requestedDates.forEach(date -> {
					bookRooms.put(date, r);
				});
			} else {
				List<LocalDate> bookingDates = roomBookingDates.get(r.getId());
				for (LocalDate d : requestedDates) {
					if (!bookingDates.stream().anyMatch(d::equals)) {
						bookRooms.put(d, r);
					}
				}
			}
		}
		if (bookRooms.size() == requestedDates.size()) {
			return bookRooms;
		} else {
			log.error("Rooms not available for booking");
			throw new RoomsCustomException(HttpStatus.NOT_FOUND, RBMSConstants.ROOM_NOT_AVAILABLE);
		}
	}

	/**
	 * Gets the rooms bookings with dates
	 *
	 * @param bookings the bookings
	 * @return the rooms bookings
	 */
	private Map<Long, List<LocalDate>> getRoomsBookings(List<RoomsBooking> bookings) {
		Map<Long, List<LocalDate>> returnRoomBookingDates = new HashMap<>();
		bookings.forEach(item -> {
			Map<LocalDate, Rooms> map = item.getRooms();
			Set<LocalDate> dates = map.keySet();
			dates.forEach(date -> {
				Rooms room = map.get(date);
				List<LocalDate> roomDates = new ArrayList<>();
				if (returnRoomBookingDates.get(room.getId()) != null) {
					roomDates = returnRoomBookingDates.get(room.getId());
				}
				roomDates.add(date);
				returnRoomBookingDates.put(room.getId(), roomDates);
			});
		});
		return returnRoomBookingDates;
	}

	/**
	 * Gets all dates between two dates
	 *
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the dates
	 */
	private List<LocalDate> getDates(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		LocalDate date = startDate;
		dates.add(date);
		while (true) {
			date = date.plusDays(1);
			if (date.isBefore(endDate)) {
				dates.add(date);
			} else {
				break;
			}
		}
		return dates;
	}

	/**
	 * Fetch all bookings with booking end date greater than equal to today's date
	 *
	 * @return the list
	 */
	public List<RoomsBooking> fetchAllBookings() {
		log.info("START-->fetchAllBookings");
		LocalDate today = LocalDate.now();
		List<RoomsBooking> response = roomsBookingRepository
				.findAllByEntityStatusAndEndDateGreaterThanEqual(EntityStatus.ACTIVE, today);
		log.info("END-->fetchAllBookings");
		return response;
	}

	/**
	 * Cancel booking.
	 *
	 * @param id the id
	 */
	public void cancelBooking(Long id) {
		log.info("START-->cancelBooking");
		RoomsBooking booking = roomsBookingRepository.findByIdAndEntityStatus(id, EntityStatus.ACTIVE);
		if (null == booking) {
			log.error("Booking details not found");
			throw new RoomsCustomException(HttpStatus.NOT_FOUND, RBMSConstants.BOOKING_NOT_FOUND);
		}
		booking.setEntityStatus(EntityStatus.INACTIVE);
		roomsBookingRepository.save(booking);
		log.info("END-->cancelBooking");
	}

}
