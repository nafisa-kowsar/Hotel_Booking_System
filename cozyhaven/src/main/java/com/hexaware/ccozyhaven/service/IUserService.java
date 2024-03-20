package com.hexaware.ccozyhaven.service;



import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.dto.UserDTO;

import com.hexaware.ccozyhaven.entities.User;

import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

@Repository
public interface IUserService {
	
	public boolean login(String username, String password);

	Long register(UserDTO userDTO) throws DataAlreadyPresentException;


	User updateUser(Long userId, UserDTO updatedUserDTO) throws UserNotFoundException;

	String deleteUser(Long userId) throws UserNotFoundException;

	public UserDTO findById(Long userId) throws UserNotFoundException;
	
	boolean checkIfUserExists(String username);
	
    String updateUserPassword(String username, String newPassword);

	
	
}

	