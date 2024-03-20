package com.hexaware.ccozyhaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.Review;
import com.hexaware.ccozyhaven.entities.User;
/*
 * Author: Haswanth
 * 
 * Repository description: Handles database operations related to the Review entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long>{
	
	List<Review> findAllByHotel(Hotel hotel);
	List<Review> findAllByUser(User user);

}
