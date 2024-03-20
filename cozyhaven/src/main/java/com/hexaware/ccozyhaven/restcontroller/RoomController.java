package com.hexaware.ccozyhaven.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hexaware.ccozyhaven.dto.RoomDTO;

import com.hexaware.ccozyhaven.entities.Room;

import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerMismatchException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;
import com.hexaware.ccozyhaven.exceptions.RoomNotFoundException;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.service.IRoomService;
/*
 * Author: Haswanth
 * 
 * Controller description: Handles HTTP requests related to the Room entity.
 * It contains methods for registering a new Room, logging in, updating details, etc.
 */




@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/room")
public class RoomController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

	private final IRoomService roomService;

	private HotelOwnerRepository hotelRepo;
	
	

	@Autowired
	public RoomController(IRoomService roomService, HotelOwnerRepository hotelRepo) {
		this.roomService = roomService;
		this.hotelRepo = hotelRepo;
	}

	@PostMapping("/add/{hotelId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public Room addRoomsToHotel(@RequestBody RoomDTO room, @PathVariable Long hotelId)
			throws HotelNotFoundException, HotelOwnerMismatchException {
		LOGGER.info("Received request to add a room to the hotel with ID: {}", hotelId);
		
		Room addedRoom = roomService.addRoomToHotel(room, hotelId);
		LOGGER.info("Room added successfully");
		return addedRoom;
	}

	@PutMapping("/edit/{roomId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public Room editRoom(@PathVariable Long roomId, @RequestBody RoomDTO updatedRoomDTO) throws RoomNotFoundException {
		LOGGER.info("Received request to edit room with ID: {}", roomId);
		Room editedRoom = roomService.editRoom(roomId, updatedRoomDTO);
		LOGGER.info("Room edited successfully");
		return editedRoom;
	}

	@DeleteMapping("/remove/{roomId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public String removeRoom(@PathVariable Long roomId) throws RoomNotFoundException {
		LOGGER.info("Received request to remove room with ID: {}", roomId);
		roomService.removeRoom(roomId);
		LOGGER.info("Room removed successfully");
		return "Room removed successfully";
	}
	
	@GetMapping("/getall-by-hotelowner/{hotelOwnerId}")
	@PreAuthorize("hasAuthority('HOTEL_OWNER')")
	public List<Room> getAllRoomsByHotelOwner(@PathVariable Long hotelOwnerId)
			throws   HotelOwnerNotFoundException {
		LOGGER.info("Getting Rooms with all hotelOwnerID: {}", hotelOwnerId);
		return roomService.getAllRoomsByHotelOwnerId(hotelOwnerId);
	}
	
	@GetMapping("/getall-by-hotelId/{hotelId}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('HOTEL_OWNER') ")
	public List<Room> getAllRoomsByHotelId(@PathVariable Long hotelId)
			throws HotelNotFoundException {
		LOGGER.info("Getting Rooms with all hotel: {}", hotelId);
		return roomService.getAllRoomsByHotelId(hotelId);
	}
	
	

	@GetMapping("/search")
	public List<Room> searchRooms(@RequestParam("location") String location, @RequestParam("checkInDate") LocalDate checkInDate, @RequestParam("checkOutDate") LocalDate checkOutDate) {
		LOGGER.info("Received request to search rooms in location: {} for the date range: {} to {}", location,
				checkInDate, checkOutDate);

		return roomService.searchRooms(location, checkInDate, checkOutDate);
	}

	@GetMapping("/availability/{roomId}")
	public boolean isRoomAvailable(@PathVariable Long roomId,@RequestParam("checkInDate") LocalDate checkInDate, @RequestParam("checkOutDate") LocalDate checkOutDate )
			throws RoomNotFoundException {
		LOGGER.info("Checking room availability for room ID {} in the date range: {} to {}", roomId, checkInDate,
				checkInDate);
		return roomService.isRoomAvailable(roomId, checkInDate,
				checkInDate);
	}

	@GetMapping("/calculate-fare-room/{roomId}")
	public double calculateTotalFare(@PathVariable Long roomId,@RequestParam("numberOfAdults") int numberOfAdults , @RequestParam("numberOfChildren") int numberOfChildren) throws RoomNotFoundException {
		LOGGER.info("Calculating total fare for room ID {} with {} adults and {} children", roomId, numberOfAdults,
				numberOfChildren);
		return roomService.calculateTotalFare( roomId, numberOfAdults,
				numberOfChildren);
	}

}
