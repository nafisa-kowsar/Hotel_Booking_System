package com.hexaware.ccozyhaven.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ccozyhaven.dto.BookedRoomDTO;

import com.hexaware.ccozyhaven.dto.ReservationDTO;
import com.hexaware.ccozyhaven.entities.Reservation;

import com.hexaware.ccozyhaven.exceptions.InconsistentHotelException;
import com.hexaware.ccozyhaven.exceptions.InvalidCancellationException;
import com.hexaware.ccozyhaven.exceptions.InvalidRefundException;
import com.hexaware.ccozyhaven.exceptions.RefundProcessedException;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.RoomNotAvailableException;
import com.hexaware.ccozyhaven.exceptions.RoomNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.service.IReservationService;

/*
 * Author: Nafisa
 * 
 * Controller description: Handles HTTP requests related to the Reservation entity.
 * It contains methods for registering a new Reservation, logging in, updating details, etc.
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	private String reservationRequest = "Received request to view reservations by hotel ID: {}";

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	private final IReservationService reservationService;

	@Autowired
	public ReservationController(IReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/get-by-hotel/{hotelId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') ")
	public List<ReservationDTO> viewReservationByHotelId(@PathVariable Long hotelId) {
		LOGGER.info(reservationRequest, hotelId);
		return reservationService.viewReservationByHotelId(hotelId);

	}

	@GetMapping("/get-by-hotelowner/{hotelOwnerId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') ")
	public List<ReservationDTO> viewReservationByHotelOwnerId(@PathVariable Long hotelOwnerId) {
		LOGGER.info(reservationRequest, hotelOwnerId);
		return reservationService.viewReservationByHotelOwnerId(hotelOwnerId);

	}

	@GetMapping("/cancelled-reservation/{hotelOwnerId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') ")
	public List<ReservationDTO> viewCancelledReservations(@PathVariable Long hotelOwnerId) {
		LOGGER.info(reservationRequest, hotelOwnerId);
		return reservationService.getCancelledReservationsByHotelId(hotelOwnerId);

	}

	@GetMapping("/valid-get-by-hotel/{hotelId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') ")
	public List<Reservation> viewValidReservationByHotelId(@PathVariable Long hotelId) {
		LOGGER.info("Received request to view valid reservations by hotel ID: {}", hotelId);
		return reservationService.viewValidReservationByHotelId(hotelId);

	}

	@GetMapping("/refundAmount/{reservationId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public Double refundAmount(@PathVariable Long reservationId)
			throws RefundProcessedException, InvalidRefundException, ReservationNotFoundException {
		LOGGER.info("Received request to get refund amount for reservation ID: {}", reservationId);
		return reservationService.refundAmount(reservationId);

	}

	@GetMapping("/get-by-user/{userId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') or hasAuthority('USER') ")
	public List<ReservationDTO> getUserReservations(@PathVariable Long userId) {
		LOGGER.info("Received request to view reservations by user ID: {}", userId);
		return reservationService.getUserReservations(userId);
	}

	@DeleteMapping("/cancel-and-refund/{userId}/{reservationId}")
	@PreAuthorize("hasAuthority('USER')")
	public void cancelReservationAndRequestRefund(@PathVariable Long userId, @PathVariable Long reservationId)
			throws InvalidCancellationException, ReservationNotFoundException {
		LOGGER.info("Received request to cancel and refund reservation with ID: {} for user ID: {}", reservationId,
				userId);
		reservationService.cancelReservationAndRequestRefund(userId, reservationId);
	}

	@PostMapping("/make-reservation/{userId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Long> reserveRooms(@PathVariable Long userId,
			@RequestParam("checkInDate") LocalDate checkInDate, @RequestParam("checkOutDate") LocalDate checkOutDate,
			@RequestBody List<BookedRoomDTO> bookedRoomDTO) {

		try {
			Long reservationId = reservationService.reservationRoom(userId, bookedRoomDTO, checkInDate, checkOutDate);
			return ResponseEntity.ok(reservationId);
		} catch (RoomNotAvailableException | RoomNotFoundException | UserNotFoundException
				| InconsistentHotelException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/reservation-fare")
	@PreAuthorize("hasAuthority('USER')")
	public double fareForReservation(@RequestParam("checkInDate") LocalDate checkInDate,
			@RequestParam("checkOutDate") LocalDate checkOutDate, @RequestBody List<BookedRoomDTO> bookedRoomDTO)
			throws RoomNotFoundException {
		LOGGER.info("Calculating total fare for this reservation");
		return reservationService.bookinFare(bookedRoomDTO, checkInDate, checkOutDate);

	}

	@DeleteMapping("/manage-room-reservation/{reservationId}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HOTEL_OWNER') ")
	public String manageRoomReservation(@PathVariable Long reservationId)
			throws ReservationNotFoundException, InvalidCancellationException {
		LOGGER.info("Received request to manage room reservation with ID: {}", reservationId);
		reservationService.manageRoomReservation(reservationId);
		return "Room reservation managed successfully";
	}

}
