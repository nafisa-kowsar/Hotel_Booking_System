package com.hexaware.ccozyhaven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.User;
/*
 * Author: Haswanth
 * 
 * Repository description: Handles database operations related to the User entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

	Optional<User> findById(Long userId);
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);

}
