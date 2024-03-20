package com.hexaware.ccozyhaven.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ccozyhaven.dto.HotelDTO;
import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.Room;
import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.service.IHotelService;
/*
 * Author: Nafisa
 * 
 * Controller description: Handles HTTP requests related to the Hotel entity.
 * It contains methods for registering a new Hotel, logging in, updating details, etc.
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

	private final IHotelService hotelService;

	@Autowired
	public HotelController(IHotelService hotelService) {
		this.hotelService = hotelService;
	}

	@GetMapping("/getall")
	public List<HotelDTO> getAllHotels() {
		LOGGER.info("Received request to get all hotels");
		return hotelService.getAllHotels();
	}

	@GetMapping("/get/{hotelId}")
	public Hotel getHotelDetailsById(@PathVariable Long hotelId) throws HotelNotFoundException {
		LOGGER.info("Received request to get hotel details for ID: {}", hotelId);
		return hotelService.getHotelDetailsById(hotelId);
	}

	@GetMapping("/available-rooms/{hotelId}")
	public List<Room> getAllAvailableRoomsInHotel(@PathVariable Long hotelId) {
		LOGGER.info("Received request to get all available rooms for hotel with ID: {}", hotelId);
		return hotelService.getAllAvailableRoomsInHotel(hotelId);
	}

	@GetMapping("/get/location/{location}")
	public ResponseEntity<List<Hotel>> getHotelDetailsById(@PathVariable String location) {
		LOGGER.info("Received request to get hotels by Location {}", location);
		List<Hotel> hotels = hotelService.findHotelsByLocation(location);
		if (hotels.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(hotels, HttpStatus.OK);
		}
	}

	@GetMapping("/get-by-name/{hotelName}")
	public ResponseEntity<List<Hotel>> findHotelsByHotelName(@PathVariable String hotelName) {
		List<Hotel> hotels = hotelService.findHotelsByHotelName(hotelName);
		if (hotels.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(hotels, HttpStatus.OK);
		}
	}

}
