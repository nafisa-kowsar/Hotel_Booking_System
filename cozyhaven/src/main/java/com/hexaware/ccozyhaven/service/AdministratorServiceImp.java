package com.hexaware.ccozyhaven.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.AdministratorDTO;
import com.hexaware.ccozyhaven.dto.UserDTO;
import com.hexaware.ccozyhaven.entities.Administrator;
import com.hexaware.ccozyhaven.entities.HotelOwner;

import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;
import com.hexaware.ccozyhaven.repository.AdministratorRepository;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;

import com.hexaware.ccozyhaven.repository.UserRepository;

import jakarta.transaction.Transactional;
/*
 * Author: Nafisa
 * 
 * Service description: Provides business logic related to the Administrator entity.
 * It contains methods for registering a new administrator, logging in, updating details, etc.
 */

@Service
@Transactional
public class AdministratorServiceImp implements IAdministratorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorServiceImp.class);

	private final AdministratorRepository adminRepository;
	private final UserRepository userRepository;
	private final HotelOwnerRepository hotelOwnerRepository;

	@Autowired
	public AdministratorServiceImp(AdministratorRepository adminRepository, UserRepository userRepository,
			HotelOwnerRepository hotelOwnerRepository) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
		this.hotelOwnerRepository = hotelOwnerRepository;

	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String login(String username, String password) {
		return null;
	}

	@Override
	public Long register(AdministratorDTO adminDTO) throws DataAlreadyPresentException {
		
		if (checkIfAdminExists(adminDTO.getUsername())) {
	        throw new DataAlreadyPresentException("Username already exists. Please choose a different username.");
	    }
		Administrator admin = new Administrator();
		admin.setAdminId(adminDTO.getAdminId());
		admin.setUsername(adminDTO.getUsername());
		admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
		admin.setAdminFirstName(adminDTO.getAdminFirstName());
		admin.setAdminLastName(adminDTO.getAdminLastName());
		admin.setEmail(adminDTO.getEmail());
		admin.setRole("ADMIN");

		adminRepository.save(admin);

		return admin.getAdminId();
	}

	@Override
	public void deleteUserAccount(Long userId) throws UserNotFoundException {
		LOGGER.info("Deleting user account with ID: {}", userId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

		userRepository.delete(user);
		LOGGER.info("User account deleted successfully");
	}

	@Override
	public void deleteHotelOwnerAccount(Long hotelOwnerId) throws UserNotFoundException {
		LOGGER.info("Deleting hotel owner account with ID: {}", hotelOwnerId);
		HotelOwner hotelOwner = hotelOwnerRepository.findById(hotelOwnerId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + hotelOwnerId));

		hotelOwnerRepository.delete(hotelOwner);
		LOGGER.info("Hotel owner account deleted successfully");

	}

	@Override
	public List<UserDTO> viewAllUser() {
		LOGGER.info("Viewing all users");

	    List<User> users = userRepository.findAll();
	    List<UserDTO> userDTOs = new ArrayList<>();
	    
	    for (User user : users) {
	        UserDTO userDTO = convertToUserDTO(user);
	        userDTOs.add(userDTO);
	    }
	    
	    return userDTOs;
	}
	
	private UserDTO convertToUserDTO(User user) {
	    UserDTO userDTO = new UserDTO();
	    userDTO.setUserId(user.getUserId());
	    userDTO.setUserFirstName(user.getFirstName());
	    userDTO.setUserLastName(user.getLastName());
	    userDTO.setUsername(user.getUsername());
	    userDTO.setPassword(user.getPassword());
	    userDTO.setEmail(user.getEmail());
	    userDTO.setContactNumber(user.getContactNumber());
	    userDTO.setGender(user.getGender());
	    userDTO.setAddress(user.getAddress());
	    return userDTO;
	}

	@Override
	public List<HotelOwner> viewAllHotelOwner() {
		LOGGER.info("Viewing all hotel owners");

		return hotelOwnerRepository.findAll();
	}
	
	@Override
    public boolean checkIfAdminExists(String username) {
        return adminRepository.existsByUsername(username);
    }

}
