package com.hexaware.ccozyhaven.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.HotelDTO;
import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.Room;
import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.repository.HotelRepository;

import jakarta.transaction.Transactional;
/*
 * Author: Nafisa
 * 
 * Service description: Provides business logic related to the Hotel entity.
 * It contains methods for registering a new Hotel, logging in, updating details, etc.
 */

@Service
@Transactional
public class HotelServiceImp implements IHotelService{
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImp.class);
	
	 private final HotelRepository hotelRepository;

	    @Autowired
	    public HotelServiceImp(HotelRepository hotelRepository) {
	        this.hotelRepository = hotelRepository;
	    }

	@Override
	public List<HotelDTO> getAllHotels() {
		LOGGER.info("Fetching all hotels");
	    List<Hotel> hotels = hotelRepository.findAll();
	    List<HotelDTO> hotelDTOs = new ArrayList<>();
	    
	    for (Hotel hotel : hotels) {
	        HotelDTO hotelDTO = convertToHotelDTO(hotel);
	        hotelDTOs.add(hotelDTO);
	    }
	    
	    return hotelDTOs;
	}
	
	private HotelDTO convertToHotelDTO(Hotel hotel) {
	    HotelDTO hotelDTO = new HotelDTO();
	    hotelDTO.setHotelId(hotel.getHotelId());
	    hotelDTO.setHotelName(hotel.getHotelName());
	    hotelDTO.setLocation(hotel.getLocation());
	    hotelDTO.setHasDining(hotel.isHasDining());
	    hotelDTO.setHasParking(hotel.isHasParking());
	    hotelDTO.setHasFreeWiFi(hotel.isHasFreeWiFi());
	    hotelDTO.setHasRoomService(hotel.isHasRoomService());
	    hotelDTO.setHasSwimmingPool(hotel.isHasSwimmingPool());
	    hotelDTO.setHasFitnessCenter(hotel.isHasFitnessCenter());
	    return hotelDTO;
	}

	@Override
	public Hotel getHotelDetailsById(Long hotelId) throws HotelNotFoundException {
		 LOGGER.info("Fetching hotel details for ID: {}", hotelId);
		return hotelRepository.findById(hotelId).orElseThrow(() -> {
            LOGGER.error("Hotel not found with ID: {}", hotelId);
            return new HotelNotFoundException("Hotel not found with Id:" + hotelId);
        });
	}

	@Override
	public List<Room> getAllAvailableRoomsInHotel(Long hotelId) {
		 LOGGER.info("Fetching all available rooms for hotel ID: {}", hotelId);
		return hotelRepository.findAvailableRoomsInHotel(hotelId);
	}
	
	@Override
	public List<Hotel> findHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location);
    }
	
	public List<Hotel> findHotelsByHotelName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
    }
	

}
