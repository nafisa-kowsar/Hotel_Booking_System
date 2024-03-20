package com.hexaware.ccozyhaven.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;



public class SearchDTO {
	
	
	private String location;
	
	@NotNull(message = "Check-in date cannot be null")
	@Future(message = "Check-in date must be in the future")
	private LocalDate checkInDate;
	@NotNull(message = "Check-out date cannot be null")
	@Future(message = "Check-out date must be in the future")
	private LocalDate checkOutDate;

	public SearchDTO() {
		super();
		
	}

	public SearchDTO(String location, LocalDate checkInDate, LocalDate checkOutDate) {
		super();
		this.location = location;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "searchDTO [location=" + location + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ "]";
	}
	
	
	
	
	
	

}
