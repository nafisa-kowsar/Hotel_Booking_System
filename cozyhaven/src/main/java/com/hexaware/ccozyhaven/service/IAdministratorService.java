package com.hexaware.ccozyhaven.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hexaware.ccozyhaven.dto.AdministratorDTO;
import com.hexaware.ccozyhaven.dto.UserDTO;
import com.hexaware.ccozyhaven.entities.HotelOwner;
import com.hexaware.ccozyhaven.entities.User;
import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;

import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

@Repository
public interface IAdministratorService {
	
     // login
	String login(String username, String password);
    // register
	Long register(AdministratorDTO adminDto) throws DataAlreadyPresentException;

	// delete user account by user ID
	void deleteUserAccount(Long userId) throws UserNotFoundException;

	// delete hotel owner account by hotel owner ID
	void deleteHotelOwnerAccount(Long hotelOwnerId) throws UserNotFoundException;

	// View all user accounts
	List<UserDTO> viewAllUser();

	// View all hotel owner accounts
	List<HotelOwner> viewAllHotelOwner();
	
	public boolean checkIfAdminExists(String username);

	
	

	

}
