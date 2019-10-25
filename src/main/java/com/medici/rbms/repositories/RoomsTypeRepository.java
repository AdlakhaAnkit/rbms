package com.medici.rbms.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medici.rbms.entities.RoomsType;

/**
 * The Interface RoomsTypeRepository.
 */
@Repository
public interface RoomsTypeRepository extends CrudRepository<RoomsType, Long> {

	
	Optional<RoomsType> findById(Long Id);

}
