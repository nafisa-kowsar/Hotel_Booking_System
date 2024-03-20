package com.hexaware.ccozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ccozyhaven.dto.HotelDTO;
import com.hexaware.ccozyhaven.dto.HotelOwnerDTO;
import com.hexaware.ccozyhaven.entities.HotelOwner;

import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;

import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.repository.HotelRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class HotelOwnerServiceImpTest {

	@Autowired
	private IHotelOwnerService hotelOwnerService;

	@Autowired
	private HotelOwnerRepository hotelOwnerRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Test
	void testRegisterHotelOwner() throws DataAlreadyPresentException {

		HotelOwnerDTO hotelOwnerDTO = new HotelOwnerDTO();
		hotelOwnerDTO.setHotelOwnerName("John Doe");
		hotelOwnerDTO.setEmail("john.doe@example.com");
		hotelOwnerDTO.setUsername("john_doe_owner");
		hotelOwnerDTO.setPassword("john@123");
		hotelOwnerDTO.setGender("male");
		hotelOwnerDTO.setAddress("123 Main St");

		
		hotelOwnerDTO.setHotelName("Hotel ABC");
		hotelOwnerDTO.setLocation("Location XYZ");
		hotelOwnerDTO.setHasDining(true);
		hotelOwnerDTO.setHasParking(true);
		hotelOwnerDTO.setHasFreeWiFi(true);
		hotelOwnerDTO.setHasRoomService(true);
		hotelOwnerDTO.setHasSwimmingPool(true);
		hotelOwnerDTO.setHasFitnessCenter(true);

		

		Long hotelOwnerId = hotelOwnerService.registerHotelOwner(hotelOwnerDTO);

		assertNotNull(hotelOwnerId);
		HotelOwner savedHotelOwner = hotelOwnerRepository.findById(hotelOwnerId).orElse(null);
		assertNotNull(savedHotelOwner);
		assertEquals("John Doe", savedHotelOwner.getHotelOwnerName());
		assertNotNull(savedHotelOwner.getHotel());
		assertEquals("Hotel ABC", savedHotelOwner.getHotel().getHotelName());
	}

	@Test
	void testUpdateHotelOwnerWithHotel() throws HotelOwnerNotFoundException, DataAlreadyPresentException {

		HotelOwnerDTO hotelOwnerDTO = new HotelOwnerDTO();
		hotelOwnerDTO.setHotelOwnerName("John Doe");
		hotelOwnerDTO.setEmail("john.doe@example.com");
		hotelOwnerDTO.setUsername("john_doe_owner_123");
		hotelOwnerDTO.setPassword("johnPass123");
		hotelOwnerDTO.setGender("male");
		hotelOwnerDTO.setAddress("123 Main St");

		
		hotelOwnerDTO.setHotelName("Hotel ABC");
		hotelOwnerDTO.setLocation("Location XYZ");
		hotelOwnerDTO.setHasDining(true);
		hotelOwnerDTO.setHasParking(true);
		hotelOwnerDTO.setHasFreeWiFi(true);
		hotelOwnerDTO.setHasRoomService(true);
		hotelOwnerDTO.setHasSwimmingPool(true);
		hotelOwnerDTO.setHasFitnessCenter(true);

		

		Long hotelOwnerId = hotelOwnerService.registerHotelOwner(hotelOwnerDTO);

		HotelOwnerDTO updatedHotelOwnerDTO = new HotelOwnerDTO();
		updatedHotelOwnerDTO.setHotelOwnerName("Updated Name");
		updatedHotelOwnerDTO.setEmail("updated.email@example.com");
		updatedHotelOwnerDTO.setUsername("updated_owner");
		updatedHotelOwnerDTO.setPassword("johnFail123");
		updatedHotelOwnerDTO.setGender("female");
		updatedHotelOwnerDTO.setAddress("Updated Address");

		
		updatedHotelOwnerDTO.setHotelName("Updated Hotel");
		updatedHotelOwnerDTO.setLocation("Updated Location");
		updatedHotelOwnerDTO.setHasDining(false);
		updatedHotelOwnerDTO.setHasParking(false);
		updatedHotelOwnerDTO.setHasFreeWiFi(false);
		updatedHotelOwnerDTO.setHasRoomService(false);
		updatedHotelOwnerDTO.setHasSwimmingPool(false);
		updatedHotelOwnerDTO.setHasFitnessCenter(false);

		

		hotelOwnerService.updateHotelOwnerWithHotel(hotelOwnerId, updatedHotelOwnerDTO);

		HotelOwner updatedHotelOwner = hotelOwnerRepository.findById(hotelOwnerId).orElse(null);
		assertNotNull(updatedHotelOwner);
		assertEquals("Updated Name", updatedHotelOwner.getHotelOwnerName());
		assertEquals("updated.email@example.com", updatedHotelOwner.getEmail());
		assertEquals("updated_owner", updatedHotelOwner.getUsername());
		assertEquals("female", updatedHotelOwner.getGender());
		assertEquals("Updated Address", updatedHotelOwner.getAddress());

		assertNotNull(updatedHotelOwner.getHotel());
		assertEquals("Updated Hotel", updatedHotelOwner.getHotel().getHotelName());
		assertEquals("Updated Location", updatedHotelOwner.getHotel().getLocation());
		assertFalse(updatedHotelOwner.getHotel().isHasDining());
		assertFalse(updatedHotelOwner.getHotel().isHasParking());
		assertFalse(updatedHotelOwner.getHotel().isHasFreeWiFi());
		assertFalse(updatedHotelOwner.getHotel().isHasRoomService());
		assertFalse(updatedHotelOwner.getHotel().isHasSwimmingPool());
		assertFalse(updatedHotelOwner.getHotel().isHasFitnessCenter());
	}

	@Test
	void testDeleteHotelOwnerById() throws HotelOwnerNotFoundException, DataAlreadyPresentException {

		HotelOwnerDTO hotelOwnerDTO = new HotelOwnerDTO();
		hotelOwnerDTO.setHotelOwnerName("John Doe");
		hotelOwnerDTO.setEmail("john.doe@example.com");
		hotelOwnerDTO.setUsername("john_doe_owner");
		hotelOwnerDTO.setPassword("john@123");
		hotelOwnerDTO.setGender("male");
		hotelOwnerDTO.setAddress("123 Main St");

		
		hotelOwnerDTO.setHotelName("Hotel ABC");
		hotelOwnerDTO.setLocation("Location XYZ");
		hotelOwnerDTO.setHasDining(true);
		hotelOwnerDTO.setHasParking(true);
		hotelOwnerDTO.setHasFreeWiFi(true);
		hotelOwnerDTO.setHasRoomService(true);
		hotelOwnerDTO.setHasSwimmingPool(true);
		hotelOwnerDTO.setHasFitnessCenter(true);

		

		Long hotelOwnerId = hotelOwnerService.registerHotelOwner(hotelOwnerDTO);

		String result = hotelOwnerService.deleteHotelOwner(hotelOwnerId);

		assertFalse(hotelOwnerRepository.existsById(hotelOwnerId));

	}

	@Test
	 void testDeleteHotelOwner_HotelOwnerNotFound() {

		Long nonExistentHotelOwnerId = 999L;

		assertThrows(HotelOwnerNotFoundException.class,
				() -> hotelOwnerService.deleteHotelOwner(nonExistentHotelOwnerId));
	}
}
