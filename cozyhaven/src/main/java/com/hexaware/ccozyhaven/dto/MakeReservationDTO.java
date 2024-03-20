package com.hexaware.ccozyhaven.dto;

import java.util.List;

public class MakeReservationDTO {
	
	Long userId;
	String checkInDate;
	String checkOutDate;
	List<BookedRoomDTO> bookedRoomDTO;
	
	public MakeReservationDTO() {
		super();
		
	}

	public MakeReservationDTO(Long userId, String checkInDate, String checkOutDate, List<BookedRoomDTO> bookedRoomDTO) {
		super();
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookedRoomDTO = bookedRoomDTO;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public List<BookedRoomDTO> getBookedRoomDTO() {
		return bookedRoomDTO;
	}

	public void setBookedRoomDTO(List<BookedRoomDTO> bookedRoomDTO) {
		this.bookedRoomDTO = bookedRoomDTO;
	}

	@Override
	public String toString() {
		return "MakeReservationDTO [userId=" + userId + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", bookedRoomDTO=" + bookedRoomDTO + "]";
	}
	
	

}
