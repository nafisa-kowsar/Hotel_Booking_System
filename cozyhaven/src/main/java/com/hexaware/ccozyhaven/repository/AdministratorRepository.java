package com.hexaware.ccozyhaven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.Administrator;
/*
 * Author: Nafisa
 * 
 * Repository description: Handles database operations related to the Administrator entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	
	
	
	 Optional<Administrator> findByUsername(String username);
	 
	 boolean existsByUsername(String username);

}
