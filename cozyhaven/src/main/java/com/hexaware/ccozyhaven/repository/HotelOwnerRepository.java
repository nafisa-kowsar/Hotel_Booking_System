package com.hexaware.ccozyhaven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.HotelOwner;
/*
 * Author: Nafisa
 * 
 * Repository description: Handles database operations related to the HotelOwner entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */

@Repository
public interface HotelOwnerRepository extends JpaRepository<HotelOwner, Long>{
	
	@Query("select h from HotelOwner h where h.email=?1")
	Optional<HotelOwner> findByEmail(String email);
	Optional<HotelOwner> findByUsername(String username);
	
	boolean existsByUsername(String username);
}
