package com.challenge.demo.repositories;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.demo.entities.EntityStatus;
import com.challenge.demo.entities.RoomsBooking;

/**
 * @author ankit
 *
 */
@Repository
public interface RoomsBookingRepository extends CrudRepository<RoomsBooking, Long> {

	List<RoomsBooking> findAllByEndDateGreaterThanEqual(Timestamp endDate);

	List<RoomsBooking> findAllByEndDateGreaterThanEqual(LocalDate today);

	List<RoomsBooking> findAllByEntityStatusIdAndEndDateGreaterThanEqual(EntityStatus active, LocalDate today);

}
