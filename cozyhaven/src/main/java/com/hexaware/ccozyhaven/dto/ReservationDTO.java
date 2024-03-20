package com.hexaware.ccozyhaven.dto;

import java.time.LocalDate;

/*
 * Author: Nafisa
 * 
 * DTO description: Represents the data transfer object for Reservation entity.
 * It contains fields relevant to Reservation, getter and setters, constructors, and relevant validations.
 */



public class ReservationDTO {
	private Long reservationId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numberOfAdults;
	private int numberOfChildren;
	private double totalAmount;
	private String reservationStatus;
	private boolean refundProcessed;
	
	
	public ReservationDTO() {
		super();
		
	}


	


	





	





	public boolean isRefundProcessed() {
		return refundProcessed;
	}

















	public void setRefundProcessed(boolean refundProcessed) {
		this.refundProcessed = refundProcessed;
	}

















	



	public ReservationDTO(Long reservationId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfAdults,
			int numberOfChildren, double totalAmount, String reservationStatus, boolean refundProcessed) {
		super();
		this.reservationId = reservationId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.totalAmount = totalAmount;
		this.reservationStatus = reservationStatus;
		this.refundProcessed = refundProcessed;
	}

















	public Long getReservationId() {
		return reservationId;
	}


	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}





	public LocalDate getCheckInDate() {
		return checkInDate;
	}





	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}





	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}





	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
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


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getReservationStatus() {
		return reservationStatus;
	}


	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

















	@Override
	public String toString() {
		return "ReservationDTO [reservationId=" + reservationId + ", checkInDate=" + checkInDate + ", checkOutDate="
				+ checkOutDate + ", numberOfAdults=" + numberOfAdults + ", numberOfChildren=" + numberOfChildren
				+ ", totalAmount=" + totalAmount + ", reservationStatus=" + reservationStatus + ", refundProcessed="
				+ refundProcessed + "]";
	}





	





	


	
	
	
	


}
