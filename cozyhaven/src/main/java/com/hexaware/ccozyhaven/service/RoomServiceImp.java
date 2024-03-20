package com.hexaware.ccozyhaven.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hexaware.ccozyhaven.dto.RoomDTO;
import com.hexaware.ccozyhaven.entities.HotelOwner;
import com.hexaware.ccozyhaven.entities.Reservation;
import com.hexaware.ccozyhaven.entities.Room;

import com.hexaware.ccozyhaven.exceptions.HotelNotFoundException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerMismatchException;
import com.hexaware.ccozyhaven.exceptions.HotelOwnerNotFoundException;
import com.hexaware.ccozyhaven.exceptions.RoomNotFoundException;
import com.hexaware.ccozyhaven.repository.HotelOwnerRepository;
import com.hexaware.ccozyhaven.repository.ReservationRepository;
import com.hexaware.ccozyhaven.repository.RoomRepository;

import jakarta.transaction.Transactional;
/*
 * Author:Haswanth
 * 
 * Service description: Provides business logic related to the Room entity.
 * It contains methods for registering a new Room, logging in, updating details, etc.
 */

@Service
@Transactional
public class RoomServiceImp implements IRoomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImp.class);

	private final RoomRepository roomRepository;
   
    private final ReservationRepository reservationRepository;

	private HotelOwnerRepository hotelRepo;
    
    private static String roomNotFound = "Room not found with id: ";
    @Autowired
    public RoomServiceImp(
            RoomRepository roomRepository,
            ReservationRepository reservationRepository,
            HotelOwnerRepository hotelRepo
    ) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.hotelRepo = hotelRepo;
    }

	@Override
	public Room addRoomToHotel(RoomDTO roomDTO, Long hotelId)
			throws HotelNotFoundException, HotelOwnerMismatchException{
		HotelOwner hotelOwner = hotelRepo.findById(hotelId).orElse(null);
		Long actualHotelId = hotelOwner.getHotel().getHotelId();
		
		LOGGER.info("Adding room to hotel");

		Room room = new Room();
		room.setRoomSize(roomDTO.getRoomSize());
		room.setBedType(roomDTO.getBedType());
		room.setMaxOccupancy(roomDTO.getMaxOccupancy());
		room.setBaseFare(roomDTO.getBaseFare());
	    LOGGER.info("roomDTO " + roomDTO.isAC());
		room.setAC(roomDTO.isAC());
		LOGGER.info("room " + room.isAC());
		room.setAvailabilityStatus(roomDTO.isAvailabilityStatus());

		Room savedRoom = roomRepository.save(room);
		roomRepository.addRoomToHotel(savedRoom.getRoomId(), actualHotelId);

		LOGGER.info("Room added to hotel successfully");
		return savedRoom;
	}

	@Override
	public Room editRoom(Long roomId, RoomDTO updatedRoomDTO)
			throws RoomNotFoundException {
		LOGGER.info("Editing room with ID {}", roomId);

		Room existingRoom = roomRepository.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException(roomNotFound + roomId));

		existingRoom.setRoomSize(updatedRoomDTO.getRoomSize());
		existingRoom.setBedType(updatedRoomDTO.getBedType());
		existingRoom.setMaxOccupancy(updatedRoomDTO.getMaxOccupancy());
		existingRoom.setBaseFare(updatedRoomDTO.getBaseFare());
		existingRoom.setAC(updatedRoomDTO.isAC());
		existingRoom.setAvailabilityStatus(updatedRoomDTO.isAvailabilityStatus());

		LOGGER.info("Room with ID {} edited successfully", roomId);
		return roomRepository.save(existingRoom);

	}

	@Override
	public void removeRoom(Long roomId)
			throws RoomNotFoundException{
		LOGGER.info("Removing room with ID {}", roomId);

		Room roomToDelete = roomRepository.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException(roomNotFound + roomId));

		roomRepository.delete(roomToDelete);
		LOGGER.info("Room with ID {} removed successfully", roomId);

	}
	
	@Override
	public List<Room> getAllRoomsByHotelOwnerId(Long hotelOwnerId) throws HotelOwnerNotFoundException {
		
		HotelOwner hotelOwner = hotelRepo.findById(hotelOwnerId).orElseThrow(() -> new HotelOwnerNotFoundException("Hotel Owner Not Found" + hotelOwnerId));
		Long hotelId = hotelOwner.getHotel().getHotelId();
		
		
        return roomRepository.findAllByHotelId(hotelId);
    }
	
	@Override
	public List<Room> getAllRoomsByHotelId(Long hotelId) throws HotelNotFoundException {
		return roomRepository.findAllByHotelId(hotelId);
    }

	@Override
	public List<Room> searchRooms(String location, LocalDate checkInDate, LocalDate checkOutDate) {
		LOGGER.info("Searching rooms");
		if (checkInDate.isAfter(checkOutDate)) {
			throw new IllegalArgumentException("Check-in date must be before or equal to check-out date");
		}
		List<Room> availableRooms = roomRepository.findAvailableRooms(location, checkInDate, checkOutDate);
		LOGGER.info("Rooms searched successfully");
		return availableRooms;
	}

	@Override
	public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate)
			throws RoomNotFoundException {
		LOGGER.info("Checking room availability with ID {}", roomId);
		if (checkInDate.isAfter(checkOutDate)) {
			throw new IllegalArgumentException("Check-in date must be before or equal to check-out date");
		}
		Optional<Room> optionalRoom = roomRepository.findById(roomId);

		if (optionalRoom.isPresent()) {
			

			List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(roomId,
					checkInDate, checkOutDate);

			return  overlappingReservations.stream()
	                .noneMatch(reservation -> !reservation.getReservationStatus().equals("CANCELLED"));

			
		} else {
			LOGGER.error("Room not found with ID: {}", roomId);
			throw new RoomNotFoundException(roomNotFound + roomId);
		}

	}

	@Override
	public double calculateTotalFare(Long roomId, int numberOfAdults, int numberOfChildren)
			throws RoomNotFoundException {
		validateNumberOfAdults(numberOfAdults);

		Optional<Room> optionalRoom = roomRepository.findById(roomId);

		if (optionalRoom.isPresent()) {
			Room room = optionalRoom.get();
			double baseFare = room.getBaseFare();

			int maxCapacity = calculateMaxCapacity(room);
			int occupancy = room.getMaxOccupancy();

			int totalPeople = numberOfAdults + numberOfChildren;
			if (totalPeople > maxCapacity) {
				throw new IllegalArgumentException("Number of people exceeds the room's maximum capacity.");
			}
			double additionalCharge = 0;
			if (totalPeople > occupancy) {

				additionalCharge = calculateAdditionalCharge(numberOfAdults, numberOfChildren, totalPeople, occupancy,
						maxCapacity, baseFare);
			}

			return  baseFare + additionalCharge;
			
		} else {
			throw new RoomNotFoundException(roomNotFound + roomId);
		}
	}

	private void validateNumberOfAdults(int numberOfAdults) {
		if (numberOfAdults <= 0) {
			throw new IllegalArgumentException("Number of adults must be greater than zero.");
		}
	}

	private double calculateAdditionalCharge(int numberOfAdults, int numberOfChildren, int totalPeople, int occupancy,
			int maxCapacity, double baseFare) {
		double additionalCharge = 0;

		if (numberOfAdults == occupancy) {
			for (int i = 1; i <= numberOfChildren; i++) {
				additionalCharge += baseFare * 0.4;
			}

		} else if (numberOfAdults > occupancy) {
			int remainingAdults = 0;
			remainingAdults = numberOfAdults - occupancy;
			for (int i = 1; i <= remainingAdults; i++) {
				additionalCharge += baseFare * 0.6;
			}
			for (int i = 1; i <= numberOfChildren; i++) {
				additionalCharge += baseFare * 0.4;
			}
		} else {
			int remainingChildren = 0;
			remainingChildren = maxCapacity - totalPeople;
			for (int i = 1; i <= remainingChildren; i++) {
				additionalCharge += baseFare * 0.4;
			}

		}
		return additionalCharge;
	}

	private int calculateMaxCapacity(Room room) {

		switch (room.getBedType().toLowerCase()) {
		case "single bed":
			return 2;
		case "double bed":
			return 4;
		default:
			return 6;
		}
	}

}
