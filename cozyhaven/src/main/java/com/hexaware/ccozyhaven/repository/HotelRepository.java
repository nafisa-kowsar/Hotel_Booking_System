package com.hexaware.ccozyhaven.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.Hotel;

import com.hexaware.ccozyhaven.entities.Room;
/*
 * Author: Nafisa
 * 
 * Repository description: Handles database operations related to the Hotel entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */


@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>{
	
	List<Hotel> findByLocation(String location);
	
	List<Hotel> findByHotelName(String hotelName);
	
	
	@Query("SELECT room FROM Room room WHERE room.hotel.id = :hotelId AND room.availabilityStatus = true")
    List<Room> findAvailableRoomsInHotel(@Param("hotelId") Long hotelId);


	

}
