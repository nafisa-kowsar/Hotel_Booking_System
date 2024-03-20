package com.hexaware.ccozyhaven.service;

import com.hexaware.ccozyhaven.dto.HotelOwnerDTO;

import com.hexaware.ccozyhaven.exceptions.DataAlreadyPresentException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;

public interface IHotelOwnerService {

	// register
	Long registerHotelOwner(HotelOwnerDTO hotelOwnerDTO) throws DataAlreadyPresentException;

	// login
	boolean login(String username, String password);

	// deleteHotelOwner
	String deleteHotelOwner(Long hotelOwnerId) throws HotelOwnerNotFoundException;

	// updateHotelOwnerwith HOtel
	void updateHotelOwnerWithHotel(Long hotelOwnerId, HotelOwnerDTO updatedHotelOwnerDTO)
			throws HotelOwnerNotFoundException;

	HotelOwnerDTO findById(Long hotelOwnerId) throws HotelOwnerNotFoundException;
	
	public boolean checkIfHotelOwnerExists(String username);

}