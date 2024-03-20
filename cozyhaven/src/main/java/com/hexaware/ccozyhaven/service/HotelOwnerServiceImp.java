package com.hexaware.ccozyhaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.HotelDTO;
import com.hexaware.ccozyhaven.dto.HotelOwnerDTO;
import com.hexaware.ccozyhaven.dto.UserDTO;
import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.HotelOwner;
import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.repository.HotelRepository;

import jakarta.transaction.Transactional;
/*
 * Author: Nafisa
 * 
 * Service description: Provides business logic related to the HotelOwner entity.
 * It contains methods for registering a new HotelOwner, logging in, updating details, etc.
 */

@Service
@Transactional
public class HotelOwnerServiceImp implements IHotelOwnerService {

	 private final HotelOwnerRepository hotelOwnerRepository;
	    private final HotelRepository hotelRepository;

	    @Autowired
	    public HotelOwnerServiceImp(HotelOwnerRepository hotelOwnerRepository, HotelRepository hotelRepository) {
	        this.hotelOwnerRepository = hotelOwnerRepository;
	        this.hotelRepository = hotelRepository;
	    }

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelOwnerServiceImp.class);

	@Override
	public boolean login(String username, String password) {
		LOGGER.info("Hotel Owner is loggin in...");
		return false;

	}

	@Override
	public Long registerHotelOwner(HotelOwnerDTO hotelOwnerDTO) throws DataAlreadyPresentException {
		if (checkIfHotelOwnerExists(hotelOwnerDTO.getUsername())) {
	        throw new DataAlreadyPresentException("Username already exists. Please choose a different username.");
	    }

		LOGGER.info("Adding hotel owner with hotel: {}", hotelOwnerDTO);

		HotelOwner hotelOwner = convertHotelOwnerDTOToEntity(hotelOwnerDTO);
		
		Hotel hotel = convertHotelDTOToEntity(hotelOwnerDTO);
		hotelOwner.setRole("HOTEL_OWNER");
		hotel.setHotelOwner(hotelOwner);
		hotelOwner.setHotel(hotel);

		Hotel addedHotel = hotelRepository.save(hotel);
		HotelOwner addedHotelOwner = hotelOwnerRepository.save(hotelOwner);

		if ((addedHotelOwner != null) && (addedHotel != null)) {
			LOGGER.info("Registered Hotel Owner with Hotel Details: Owner={}, Hotel={}", addedHotelOwner, addedHotel);

			return addedHotelOwner.getHotelOwnerId();
		}
		LOGGER.error("Hotel Owner not registered");
		return null;

	}

	private HotelOwner convertHotelOwnerDTOToEntity(HotelOwnerDTO hotelOwnerDTO) {
		HotelOwner hotelOwner = new HotelOwner();

		hotelOwner.setHotelOwnerName(hotelOwnerDTO.getHotelOwnerName());
		hotelOwner.setPassword(passwordEncoder.encode(hotelOwnerDTO.getPassword()));
		hotelOwner.setEmail(hotelOwnerDTO.getEmail());
		hotelOwner.setUsername(hotelOwnerDTO.getUsername());
		hotelOwner.setGender(hotelOwnerDTO.getGender());
		hotelOwner.setAddress(hotelOwnerDTO.getAddress());
		return hotelOwner;
	}

	private Hotel convertHotelDTOToEntity(HotelOwnerDTO hotelOwnerDTO) {
		Hotel hotel = new Hotel();

		hotel.setHotelName(hotelOwnerDTO.getHotelName());
		hotel.setLocation(hotelOwnerDTO.getLocation());
		hotel.setHasDining(hotelOwnerDTO.isHasDining());
		hotel.setHasParking(hotelOwnerDTO.isHasParking());
		hotel.setHasFreeWiFi(hotelOwnerDTO.isHasFreeWiFi());
		hotel.setHasRoomService(hotelOwnerDTO.isHasRoomService());
		hotel.setHasSwimmingPool(hotelOwnerDTO.isHasSwimmingPool());
		hotel.setHasFitnessCenter(hotelOwnerDTO.isHasFitnessCenter());
		return hotel;
	}

	@Override

	public void updateHotelOwnerWithHotel(Long hotelOwnerId, HotelOwnerDTO updatedHotelOwnerDTO)
			throws HotelOwnerNotFoundException {

		LOGGER.info("Updating hotel owner with ID: {}", hotelOwnerId);
		HotelOwner existingHotelOwner = hotelOwnerRepository.findById(hotelOwnerId)
				.orElseThrow(() -> new HotelOwnerNotFoundException("HotelOwner not found with id: " + hotelOwnerId));

		HotelOwner updatedHotelOwner = convertHotelOwnerDTOToEntity(updatedHotelOwnerDTO);

		existingHotelOwner.setHotelOwnerName(updatedHotelOwner.getHotelOwnerName());
		existingHotelOwner.setEmail(updatedHotelOwner.getEmail());
		existingHotelOwner.setGender(updatedHotelOwner.getGender());
		existingHotelOwner.setUsername(updatedHotelOwner.getUsername());
		existingHotelOwner.setPassword(updatedHotelOwner.getPassword());
		existingHotelOwner.setAddress(updatedHotelOwner.getAddress());

		
		Hotel existingHotel = existingHotelOwner.getHotel();

		existingHotel.setHotelName(updatedHotelOwnerDTO.getHotelName());
		existingHotel.setLocation(updatedHotelOwnerDTO.getLocation());
		existingHotel.setHasDining(updatedHotelOwnerDTO.isHasDining());
		existingHotel.setHasParking(updatedHotelOwnerDTO.isHasParking());
		existingHotel.setHasFreeWiFi(updatedHotelOwnerDTO.isHasFreeWiFi());
		existingHotel.setHasRoomService(updatedHotelOwnerDTO.isHasRoomService());
		existingHotel.setHasSwimmingPool(updatedHotelOwnerDTO.isHasSwimmingPool());
		existingHotel.setHasFitnessCenter(updatedHotelOwnerDTO.isHasFitnessCenter());

		hotelOwnerRepository.save(existingHotelOwner);

		LOGGER.info("Hotel owner and associated hotel details updated successfully");
	}

	@Override

	public String deleteHotelOwner(Long hotelOwnerId) throws HotelOwnerNotFoundException {
		LOGGER.info("Deleting hotel owner with ID: {}", hotelOwnerId);
		HotelOwner hotelOwnerToDelete = hotelOwnerRepository.findById(hotelOwnerId)
				.orElseThrow(() -> new HotelOwnerNotFoundException("HotelOwner not found with id: " + hotelOwnerId));

		hotelOwnerRepository.delete(hotelOwnerToDelete);
		LOGGER.info("Hotel owner deleted successfully");

		return "Hotel Owner with ID: " + hotelOwnerId + "deleted succesfully";
	}
	
	@Override
	public HotelOwnerDTO findById(Long hotelOwnerId) throws HotelOwnerNotFoundException {
	    LOGGER.info("Finding hotel owner by ID: {}", hotelOwnerId);
	    HotelOwner hotelOwner = hotelOwnerRepository.findById(hotelOwnerId)
	            .orElseThrow(() -> new HotelOwnerNotFoundException("Hotel Owner not found with id: " + hotelOwnerId));

	    HotelOwnerDTO hotelOwnerDTO = new HotelOwnerDTO();
	    hotelOwnerDTO.setHotelOwnerId(hotelOwner.getHotelOwnerId());
	    hotelOwnerDTO.setHotelOwnerName(hotelOwner.getHotelOwnerName());
	    hotelOwnerDTO.setEmail(hotelOwner.getEmail());
	    hotelOwnerDTO.setGender(hotelOwner.getGender());
	    hotelOwnerDTO.setUsername(hotelOwner.getUsername());
	    hotelOwnerDTO.setPassword(hotelOwner.getPassword());
	    hotelOwnerDTO.setAddress(hotelOwner.getAddress());

	    Hotel hotel = hotelOwner.getHotel();
	    if (hotel != null) {
	        //hotelOwnerDTO.setHotelId(hotel.getHotelId());
	        hotelOwnerDTO.setHotelName(hotel.getHotelName());
	        hotelOwnerDTO.setLocation(hotel.getLocation());
	        hotelOwnerDTO.setHasDining(hotel.isHasDining());
	        hotelOwnerDTO.setHasParking(hotel.isHasParking());
	        hotelOwnerDTO.setHasFreeWiFi(hotel.isHasFreeWiFi());
	        hotelOwnerDTO.setHasRoomService(hotel.isHasRoomService());
	        hotelOwnerDTO.setHasSwimmingPool(hotel.isHasSwimmingPool());
	        hotelOwnerDTO.setHasFitnessCenter(hotel.isHasFitnessCenter());
	    }

	    LOGGER.info("Hotel owner found successfully by ID: {}", hotelOwnerId);
	    return hotelOwnerDTO;
	}
	
	@Override
    public boolean checkIfHotelOwnerExists(String username) {
        return hotelOwnerRepository.existsByUsername(username);
    }



}
