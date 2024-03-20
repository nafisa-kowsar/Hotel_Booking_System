package com.hexaware.ccozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.ccozyhaven.dto.AdministratorDTO;
import com.hexaware.ccozyhaven.entities.Administrator;
import com.hexaware.ccozyhaven.entities.HotelOwner;
import com.hexaware.ccozyhaven.entities.Reservation;
import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.repository.AdministratorRepository;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.repository.ReservationRepository;
import com.hexaware.ccozyhaven.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class AdministratorServiceImpTest {

	@Autowired
	private IAdministratorService administratorService;

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelOwnerRepository hotelOwnerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void testRegister() throws DataAlreadyPresentException, UserNotFoundException {

		AdministratorDTO administratorDTO = new AdministratorDTO();
		administratorDTO.setAdminFirstName("John");
		administratorDTO.setAdminLastName("Doe");
		administratorDTO.setUsername("john_123");
		administratorDTO.setPassword("johnPass123");
		administratorDTO.setEmail("john.doe@gmail.com");

		Long adminId = administratorService.register(administratorDTO);

		assertNotNull(adminId);

		assertNotNull(administratorRepository.findById(adminId).orElse(null));

		Administrator storedAdmin = administratorRepository.findById(adminId).orElse(null);
		assertNotNull(storedAdmin);
		assertTrue(passwordEncoder.matches("johnPass123", storedAdmin.getPassword()));
	}

	@Test

	void testDeleteUserAccount() {

		assertDoesNotThrow(() -> administratorService.deleteUserAccount(1L));

		Optional<User> deletedUser = userRepository.findById(1L);
		assert (!deletedUser.isPresent());
	}

	@Test

	void testDeleteHotelOwnerAccount() {

		assertDoesNotThrow(() -> administratorService.deleteHotelOwnerAccount(1L));

		Optional<HotelOwner> deletedHotelOwner = hotelOwnerRepository.findById(1L);
		assertFalse(deletedHotelOwner.isPresent());

	}

	@Test

	void testViewAllUser() {

		List<User> userList = administratorService.viewAllUser();
		assertEquals(1, userList.size());
	}

	@Test
	void testViewAllHotelOwner() {

		List<HotelOwner> hotelOwnerList = administratorService.viewAllHotelOwner();
		assertEquals(2, hotelOwnerList.size());
	}

	

}
