package com.hexaware.ccozyhaven.dto;

public class BookedRoomDTO {

	private Long roomId;
	private int numberOfAdults;
	private int numberOfChildren;

	public BookedRoomDTO() {
		super();

	}

	public BookedRoomDTO(Long roomId, int numberOfAdults, int numberOfChildren) {
		super();
		this.roomId = roomId;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

}
