package com.hexaware.ccozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ccozyhaven.entities.Hotel;
import com.hexaware.ccozyhaven.entities.Room;
import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.repository.HotelRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class HotelServiceImpTest {

	@Autowired
	IHotelService hotelService;
	
	@Autowired
	HotelRepository hotelRepository;
	
	

	@Test
	void testGetAllHotels() {
		List<Hotel> hotels = hotelService.getAllHotels();
		assertNotNull(hotels);
	}

	@Test
	void testGetHotelDetailsById() throws HotelNotFoundException {
		Long hotelId = 1L;
		Hotel hotel = hotelService.getHotelDetailsById(hotelId);
		assertNotNull(hotel);
		assertEquals(hotelId, hotel.getHotelId());
	}

	@Test
	void testGetAllAvailableRoomsInHotel() {
		Long hotelId = 1L;
		List<Room> rooms = hotelService.getAllAvailableRoomsInHotel(hotelId);
		assertNotNull(rooms);
	}
	
	@Test
    void testFindHotelsByLocation() {
        
        Hotel hotel1 = new Hotel("Hotel1", "Location1", true, true, true, true, true, true);
        Hotel hotel2 = new Hotel("Hotel2", "Location1", true, true, true, true, true, true);
        Hotel hotel3 = new Hotel("Hotel3", "Location2", true, true, true, true, true, true);

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);

        
        List<Hotel> hotels1 = hotelService.findHotelsByLocation("Location1");
        List<Hotel> hotels2 = hotelService.findHotelsByLocation("Location2");

        
        assertEquals(2, hotels1.size());
        assertEquals(1, hotels2.size());
    }
	
	@Test
    void testFindHotelsByHotelName() {
        
        Hotel hotel1 = new Hotel("Hotel1", "Location1", true, true, true, true, true, true);
        Hotel hotel2 = new Hotel("Hotel2", "Location1", true, true, true, true, true, true);
        Hotel hotel3 = new Hotel("Hotel3", "Location2", true, true, true, true, true, true);

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);

        
        List<Hotel> hotels1 = hotelService.findHotelsByHotelName("Hotel1");
        List<Hotel> hotels2 = hotelService.findHotelsByHotelName("Hotel2");
        List<Hotel> hotels3 = hotelService.findHotelsByHotelName("Hotel3");

        
        assertEquals(1, hotels1.size());
        assertEquals(1, hotels2.size());
        assertEquals(1, hotels3.size());
    }

}
