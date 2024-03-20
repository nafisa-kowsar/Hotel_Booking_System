package com.hexaware.ccozyhaven.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.UserDTO;

import com.hexaware.ccozyhaven.entities.User;

import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

import com.hexaware.ccozyhaven.repository.UserRepository;

import jakarta.transaction.Transactional;
/*
 * Author: Haswanth
 * 
 * Service description: Provides business logic related to the User entity.
 * It contains methods for registering a new User, logging in, updating details, etc.
 */

@Service
@Transactional
public class UserServiceImp implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean login(String username, String password) {
		LOGGER.info("User is loggin in...");
		return false;
	}

	@Override
	public Long register(UserDTO userDTO) throws DataAlreadyPresentException {
		if (checkIfUserExists(userDTO.getUsername())) {
	        throw new DataAlreadyPresentException("Username already exists. Please choose a different username.");
	    }

		User user = new User();
		user.setFirstName(userDTO.getUserFirstName());
		user.setLastName(userDTO.getUserLastName());
		user.setEmail(userDTO.getEmail());
		user.setContactNumber(userDTO.getContactNumber());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setGender(userDTO.getGender());
		user.setUsername(userDTO.getUsername());
		user.setAddress(userDTO.getAddress());
		user.setRole("USER");
		LOGGER.info("Registering Customer: {}", user);

		User addedUser = userRepository.save(user);

		if (addedUser != null) {
			LOGGER.info("Registered Customer: {}", addedUser);

			return user.getUserId();
		}
		LOGGER.error("Customer not registered");
		return null;
	}

	@Override

	public User updateUser(Long userId, UserDTO updatedUserDTO) throws UserNotFoundException {
		LOGGER.info("Updating user with ID {}", userId);

		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

		existingUser.setEmail(updatedUserDTO.getEmail());

		existingUser.setFirstName(updatedUserDTO.getUserFirstName());

		existingUser.setLastName(updatedUserDTO.getUserLastName());
		existingUser.setContactNumber(updatedUserDTO.getContactNumber());
		existingUser.setUsername(updatedUserDTO.getUsername());
		existingUser.setPassword(passwordEncoder.encode(updatedUserDTO.getPassword()));
		existingUser.setGender(updatedUserDTO.getGender());
		existingUser.setAddress(updatedUserDTO.getAddress());

		LOGGER.info("User updated successfully");
		return userRepository.save(existingUser);

	}

	@Override

	public String deleteUser(Long userId) throws UserNotFoundException {
		LOGGER.info("Deleting user with ID {}", userId);
		try {

			userRepository.deleteById(userId);

			LOGGER.info("User with ID {} deleted successfully", userId);
			return "User with ID " + userId + " deleted successfully";

		} catch (Exception e) {
			LOGGER.error("Error deleting user with ID {}: {}", userId, e.getMessage());
			return "Error deleting user with ID " + userId + ": " + e.getMessage();
		}

	}

	@Override
	public UserDTO findById(Long userId) throws UserNotFoundException {
		 User user = userRepository.findById(userId)
		            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

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
		    userDTO.setRole(user.getRole());

		    return userDTO;
	}
	
	@Override
    public boolean checkIfUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public String updateUserPassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username).orElse(null);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "User password updated successfully";
    }

}
