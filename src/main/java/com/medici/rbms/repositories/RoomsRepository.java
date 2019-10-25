package com.medici.rbms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medici.rbms.entities.Rooms;

/**
 * The Interface RoomsRepository.
 */
@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Long> {

	/**
	 * Find all by room type id.
	 *
	 * @param roomTypeId the room type id
	 * @return the list
	 */
	List<Rooms> findAllByRoomTypeId(Long roomTypeId);

}
