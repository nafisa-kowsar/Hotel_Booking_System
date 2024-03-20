package com.hexaware.ccozyhaven.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.entities.Room;
/*
 * Author: Haswanth
 * 
 * Repository description: Handles database operations related to the Room entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Modifying
	@Query("UPDATE Room r SET r.hotel.hotelId = :hotelId WHERE r.roomId = :roomId")
	void addRoomToHotel(@Param("roomId") Long roomId, @Param("hotelId") Long hotelId);
	
	@Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    List<Room> findAllByHotelId(@Param("hotelId") Long hotelId);

	@Query(value = "SELECT DISTINCT r.* FROM room_details r " +
	        "JOIN hotel_details h ON r.hotel_id = h.hotel_id " +
	        "LEFT JOIN reservation_room rr ON r.room_id = rr.room_id " +
	        "LEFT JOIN reservation_details res ON rr.reservation_id = res.reservation_id " +
	        "WHERE h.location = :location " +
	        "AND r.availability_status = true " +
	        "AND (res.reservation_id IS NULL OR " +
	        "    res.reservation_status = 'CANCELLED' OR " +
	        "    NOT ( " +
	        "        (res.check_in_date BETWEEN :checkInDate AND :checkOutDate) " +
	        "        OR (res.check_out_date BETWEEN :checkInDate AND :checkOutDate)" +
	        "    )" +
	        ") " +
	        "ORDER BY r.base_fare ASC", nativeQuery = true)
	List<Room> findAvailableRooms(@Param("location") String location,
	                              @Param("checkInDate") LocalDate checkInDate,
	                              @Param("checkOutDate") LocalDate checkOutDate);


}
