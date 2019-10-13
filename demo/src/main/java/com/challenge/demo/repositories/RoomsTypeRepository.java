package com.challenge.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.demo.entities.RoomsType;

@Repository
public interface RoomsTypeRepository extends CrudRepository<RoomsType, Long> {

	Optional<RoomsType> findById(Long Id);

}
