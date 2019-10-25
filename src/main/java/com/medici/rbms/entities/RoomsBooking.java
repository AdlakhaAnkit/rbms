package com.medici.rbms.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;

/**
 * The Class RoomsBooking.
 * 
 * @author ankadlak
 *
 */
@Entity(name = "ROOMSBOOKING")
public class RoomsBooking extends BaseEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The rooms. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BOOKED_ROOMS_MAPPING", joinColumns = { @JoinColumn(name = "BOOKING_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROOM_ID") })
	@MapKeyColumn(name = "BOOKING_DATE")
	private Map<LocalDate, Rooms> rooms = new HashMap<LocalDate, Rooms>();

	/** The start date. */
	@Column(name = "START_DATE")
	private LocalDate startDate;

	/** The end date. */
	@Column(name = "END_DATE")
	private LocalDate endDate;

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
	 * Gets the rooms.
	 *
	 * @return the rooms
	 */
	public Map<LocalDate, Rooms> getRooms() {
		return rooms;
	}

	/**
	 * Sets the rooms.
	 *
	 * @param rooms the rooms
	 */
	public void setRooms(Map<LocalDate, Rooms> rooms) {
		this.rooms = rooms;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
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
	 * @return the end date
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
