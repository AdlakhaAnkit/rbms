package com.challenge.demo.entities;

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

	public Map<LocalDate, Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(Map<LocalDate, Rooms> rooms) {
		this.rooms = rooms;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
