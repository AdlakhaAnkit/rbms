package com.challenge.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.demo.entities.Rooms;

@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Long> {

	List<Rooms> findAllByRoomTypeId(Long roomTypeId);

}
