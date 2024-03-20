package com.hexaware.ccozyhaven.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.hexaware.ccozyhaven.entities.Reservation;
/*
 * Author: Nafisa
 * 
 * Repository description: Handles database operations related to the Reservation entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


	
	@Query(value = "SELECT * FROM reservation_details WHERE user_id = :userId", nativeQuery = true)
	List<Reservation> findByUserId(@Param("userId") Long userId);

	Optional<Reservation> findByReservationIdAndUser_UserId(Long reservationId, Long userId);

	List<Reservation> findAllByHotel_HotelId(Long hotelId);

	@Query(value = "SELECT * FROM reservation_details WHERE hotel_id = :hotelId AND reservation_status != 'CANCELLED'", nativeQuery = true)
	List<Reservation> findValidReservationsByHotelId(@Param("hotelId") Long hotelId);

	@Query(value = "SELECT r.* FROM reservation_details r " +
	        "JOIN reservation_room rr ON r.reservation_id = rr.reservation_id " +
	        "JOIN room_details rd ON rr.room_id = rd.room_id " +
	        "WHERE rd.room_id = :roomId " +
	        "AND (r.reservation_status = 'CANCELLED' " +
	        "OR	:checkInDate BETWEEN r.check_in_date AND r.check_out_date " +
	        "OR :checkOutDate BETWEEN r.check_in_date AND r.check_out_date " +
	        "OR r.check_in_date BETWEEN :checkInDate AND :checkOutDate " +
	        "OR r.check_out_date BETWEEN :checkInDate AND :checkOutDate)" , nativeQuery = true)
	List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
	                                              @Param("checkInDate") LocalDate checkInDate,
	                                              @Param("checkOutDate") LocalDate checkOutDate);
	

	@Query(value = "SELECT * FROM reservation_details WHERE hotel_id = :hotelId AND reservation_status = 'CANCELLED'", nativeQuery = true)
	List<Reservation> findCancelledReservationsByHotelId(@Param("hotelId") Long hotelId);
	
	


}
