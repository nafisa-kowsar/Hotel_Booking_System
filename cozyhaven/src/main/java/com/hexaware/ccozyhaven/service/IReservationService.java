package com.hexaware.ccozyhaven.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.ccozyhaven.dto.BookedRoomDTO;
import com.hexaware.ccozyhaven.dto.ReservationDTO;
import com.hexaware.ccozyhaven.entities.Reservation;
import com.hexaware.ccozyhaven.exceptions.InconsistentHotelException;
import com.hexaware.ccozyhaven.exceptions.InvalidCancellationException;
import com.hexaware.ccozyhaven.exceptions.InvalidRefundException;
import com.hexaware.ccozyhaven.exceptions.RefundProcessedException;
import com.hexaware.ccozyhaven.exceptions.ReservationNotFoundException;
import com.hexaware.ccozyhaven.exceptions.RoomNotAvailableException;
import com.hexaware.ccozyhaven.exceptions.RoomNotFoundException;
import com.hexaware.ccozyhaven.exceptions.UserNotFoundException;

public interface IReservationService {

	public List<ReservationDTO> viewReservationByHotelId(Long hotelId);

	public double refundAmount(Long reservationId)
			throws RefundProcessedException, InvalidRefundException, ReservationNotFoundException;

	Long reservationRoom(Long userId, List<BookedRoomDTO> bookedRooms, LocalDate checkInDate, LocalDate checkOutDate)
			throws RoomNotAvailableException, RoomNotFoundException, UserNotFoundException, InconsistentHotelException;
	
	public double bookinFare( List<BookedRoomDTO> bookedRooms, LocalDate checkInDate,
			LocalDate checkOutDate) throws RoomNotFoundException;
	

	public List<ReservationDTO> getCancelledReservationsByHotelId(Long hotelId);
	

	public List<ReservationDTO> getUserReservations(Long userId);

	public void cancelReservationAndRequestRefund(Long userId, Long reservationId)
			throws InvalidCancellationException, ReservationNotFoundException;

	List<Reservation> viewValidReservationByHotelId(Long hotelId);
	
	public List<ReservationDTO> viewReservationByHotelOwnerId(Long hotelId);

	boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) throws RoomNotFoundException;
	
	void manageRoomReservation(Long reservationId)
			throws ReservationNotFoundException, InvalidCancellationException;

}
