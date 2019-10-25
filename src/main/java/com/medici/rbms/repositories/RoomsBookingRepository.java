package com.medici.rbms.repositories;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medici.rbms.entities.EntityStatus;
import com.medici.rbms.entities.RoomsBooking;

/**
 * The Interface RoomsBookingRepository.
 *
 * @author ankadlak
 */
@Repository
public interface RoomsBookingRepository extends CrudRepository<RoomsBooking, Long> {

	/**
	 * Find all by end date greater than equal.
	 *
	 * @param endDate the end date
	 * @return the list
	 */
	List<RoomsBooking> findAllByEndDateGreaterThanEqual(Timestamp endDate);

	/**
	 * Find all by end date greater than equal.
	 *
	 * @param today the today
	 * @return the list
	 */
	List<RoomsBooking> findAllByEndDateGreaterThanEqual(LocalDate today);

	/**
	 * Find all by entity status and end date greater than equal.
	 *
	 * @param active the active
	 * @param today the today
	 * @return the list
	 */
	List<RoomsBooking> findAllByEntityStatusAndEndDateGreaterThanEqual(EntityStatus active, LocalDate today);

	/**
	 * Find by id and entity status.
	 *
	 * @param id the id
	 * @param active the active
	 * @return the rooms booking
	 */
	RoomsBooking findByIdAndEntityStatus(Long id, EntityStatus active);

}
