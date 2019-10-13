package com.challenge.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.challenge.demo.controller.RoomsBookingController;
import com.challenge.demo.entities.EntityStatus;
import com.challenge.demo.entities.Rooms;
import com.challenge.demo.entities.RoomsBooking;
import com.challenge.demo.entities.RoomsType;
import com.challenge.demo.exception.RoomsNAException;
import com.challenge.demo.repositories.RoomsBookingRepository;
import com.challenge.demo.repositories.RoomsRepository;
import com.challenge.demo.repositories.RoomsTypeRepository;
import com.challenge.demo.views.RoomsBookingRequest;
import com.challenge.demo.views.RoomsBookingResponse;

/**
 * The Class RoomsBookingService.
 */
@Service
public class RoomsBookingService {

	/** The rooms repository. */
	private final RoomsRepository roomsRepository;

	/** The rooms booking repository. */
	private final RoomsBookingRepository roomsBookingRepository;

	private final RoomsTypeRepository roomsTypeRepository;

	private Logger log = LoggerFactory.getLogger(RoomsBookingController.class);

	private String datePattern = "dd MMM yyyy";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

	/**
	 * Instantiates a new rooms booking service.
	 *
	 * @param roomsRepository        the rooms repository
	 * @param roomsBookingRepository the rooms booking repository
	 */
	@Autowired
	public RoomsBookingService(RoomsRepository roomsRepository, RoomsBookingRepository roomsBookingRepository,
			RoomsTypeRepository roomsTypeRepository) {
		this.roomsBookingRepository = roomsBookingRepository;
		this.roomsRepository = roomsRepository;
		this.roomsTypeRepository = roomsTypeRepository;
	}

	public List<RoomsBookingResponse> fetchAllRoomBookings() {
		LocalDate today = LocalDate.now();
		List<RoomsBooking> response = roomsBookingRepository
				.findAllByEntityStatusIdAndEndDateGreaterThanEqual(EntityStatus.ACTIVE, today);
		Map<Long, List<LocalDate>> roomBookingDates = getRoomsBookings(response);
		List<RoomsBookingResponse> responseRoomBookingList = new ArrayList<RoomsBookingResponse>();
		Iterable<Rooms> roomsItr = roomsRepository.findAll();
		Iterator<Rooms> allRooms = roomsItr.iterator();
		while (allRooms.hasNext()) {
			RoomsBookingResponse roomBooking = new RoomsBookingResponse();
			Rooms room = allRooms.next();
			roomBooking.setId(room.getId());
			roomBooking.setRoomType(room.getRoomType().getRoomType());
			roomBooking.setBookedDates(roomBookingDates.get(room.getId()));
			responseRoomBookingList.add(roomBooking);
		}
		return responseRoomBookingList;
	}

	public RoomsBooking bookRooms(RoomsBookingRequest roomsBookingRequest) throws Exception {
		Optional<RoomsType> roomType = roomsTypeRepository.findById(roomsBookingRequest.getRoomTypeId());
		RoomsType obj = roomType.get();
		RoomsBooking roomsBookingEntity = new RoomsBooking();
		List<Rooms> rooms = roomsRepository.findAllByRoomTypeId(obj.getId());
		LocalDate today = LocalDate.now();
		List<RoomsBooking> bookings = roomsBookingRepository.findAllByEndDateGreaterThanEqual(today);
		Map<Long, List<LocalDate>> roomBookingDates = getRoomsBookings(bookings);
		List<LocalDate> requestedDates = getDates(roomsBookingRequest.getStartDate(), roomsBookingRequest.getEndDate());
		Map<LocalDate, Rooms> room = getAvailableRoom(rooms, roomBookingDates, requestedDates);
		roomsBookingEntity.setRooms(room);
		roomsBookingEntity.setEndDate(roomsBookingRequest.getEndDate());
		roomsBookingEntity.setStartDate(roomsBookingRequest.getStartDate());
		return roomsBookingRepository.save(roomsBookingEntity);
	}

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
			throw new RoomsNAException(HttpStatus.NOT_FOUND, "Rooms Not Available",this.getClass());
		}
	}

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

	private List<LocalDate> getDates(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<>();
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

}
