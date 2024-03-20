package com.hexaware.ccozyhaven.service;

import java.util.List;

import com.hexaware.ccozyhaven.dto.HotelDTO;
import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.Room;
import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;

public interface IHotelService {
	
	
	public List<HotelDTO> getAllHotels();
	
	public Hotel getHotelDetailsById(Long hotelId) throws HotelNotFoundException;
	
	List<Room> getAllAvailableRoomsInHotel(Long hotelId);
	
	public List<Hotel> findHotelsByLocation(String location);
	
	public List<Hotel> findHotelsByHotelName(String hotelName);

}
