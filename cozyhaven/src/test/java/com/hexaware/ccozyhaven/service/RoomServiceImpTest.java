package com.hexaware.ccozyhaven.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.ccozyhaven.dto.RoomDTO;
import com.hexaware.ccozyhaven.entities.Room;

import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerMismatchException;
import com.hexaware.ccozyhaven.exceptions.RoomNotFoundException;

import com.hexaware.ccozyhaven.repository.ReservationRepository;
import com.hexaware.ccozyhaven.repository.RoomRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class RoomServiceImpTest {

	@Autowired
	private RoomServiceImp roomService;

	@Autowired
	private RoomRepository roomRepository;

	@Test

	void testAddRoomToHotel() throws HotelNotFoundException, HotelOwnerMismatchException {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomSize("40 m²/430 ft²");
		roomDTO.setBedType("double bed");
		roomDTO.setMaxOccupancy(2);
		roomDTO.setBaseFare(100.0);
		roomDTO.setAC(true);
		roomDTO.setAvailabilityStatus(true);

		Long hotelId = 1L;

		Room savedRoom = roomService.addRoomToHotel(roomDTO, hotelId);

		assertNotNull(savedRoom);
		assertNotNull(savedRoom.getRoomId());
		assertEquals(roomDTO.getRoomSize(), savedRoom.getRoomSize());
		assertEquals(roomDTO.getBedType(), savedRoom.getBedType());
		assertEquals(roomDTO.getMaxOccupancy(), savedRoom.getMaxOccupancy());
		assertEquals(roomDTO.getBaseFare(), savedRoom.getBaseFare());
		assertEquals(roomDTO.isAC(), savedRoom.isAC());
		assertEquals(roomDTO.isAvailabilityStatus(), savedRoom.isAvailabilityStatus());
	}

	@Test
	void testEditRoom() throws RoomNotFoundException {

		Long roomId = 3L;

		RoomDTO updatedRoomDTO = new RoomDTO();
		updatedRoomDTO.setRoomSize("55 m²/592 ft²");
		updatedRoomDTO.setBedType("king size");
		updatedRoomDTO.setMaxOccupancy(4);
		updatedRoomDTO.setBaseFare(150.0);
		updatedRoomDTO.setAC(true);
		updatedRoomDTO.setAvailabilityStatus(false);

		Room editedRoom = roomService.editRoom(roomId, updatedRoomDTO);

		assertNotNull(editedRoom);
		assertEquals(updatedRoomDTO.getRoomSize(), editedRoom.getRoomSize());
		assertEquals(updatedRoomDTO.getBedType(), editedRoom.getBedType());
		assertEquals(updatedRoomDTO.getMaxOccupancy(), editedRoom.getMaxOccupancy());
		assertEquals(updatedRoomDTO.getBaseFare(), editedRoom.getBaseFare());
		assertEquals(updatedRoomDTO.isAC(), editedRoom.isAC());
		assertEquals(updatedRoomDTO.isAvailabilityStatus(), editedRoom.isAvailabilityStatus());
	}

	@Test
	void testRemoveRoom() throws RoomNotFoundException {

		Long roomIdToRemove = 3L;

		roomService.removeRoom(roomIdToRemove);

		assertThrows(RoomNotFoundException.class, () -> roomRepository.findById(roomIdToRemove)
				.orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomIdToRemove)));
	}

	@Test
	void testSearchRooms() {

		String location = "Hyderabad";
		LocalDate checkInDate = LocalDate.of(2024, 4, 10);
		LocalDate checkOutDate = LocalDate.of(2024, 4, 15);

		List<Room> availableRooms = roomService.searchRooms(location, checkInDate, checkOutDate);

		assertNotNull(availableRooms);
		assertFalse(availableRooms.isEmpty());

	}

	@Test
	void testIsRoomAvailable() throws RoomNotFoundException {

		Long roomId = 3L;
		LocalDate checkInDate = LocalDate.of(2024, 3, 20);
		LocalDate checkOutDate = LocalDate.of(2024, 3, 25);

		boolean isAvailable = roomService.isRoomAvailable(roomId, checkInDate, checkOutDate);

		// Assert
		assertTrue(isAvailable);

	}

	@Test
	void testCalculateTotalFare() throws RoomNotFoundException {

		Long roomId = 3L;
		int numberOfAdults = 2;
		int numberOfChildren = 3;

		double totalFare = roomService.calculateTotalFare(roomId, numberOfAdults, numberOfChildren);

		assertTrue(totalFare > 0);
		assertEquals(280, totalFare);

	}

}
