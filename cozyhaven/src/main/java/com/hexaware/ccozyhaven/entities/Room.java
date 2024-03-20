package com.hexaware.ccozyhaven.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


/*
 * Author: Haswanth
 * 
 * Entity description: contains properties related to room , getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "Room_Details")

public class Room {

	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
	@SequenceGenerator(name = "room_sequence", initialValue = 101, allocationSize = 1)
	private Long roomId;

	@NotBlank(message = "Room size is required")
	@Size(max = 20, message = "Room size must be at most 20 characters")
	@Column(name = "room_size")
	private String roomSize;

	@NotBlank(message = "Bed type is required")
	@Size(max = 20, message = "Bed type must be at most 20 characters")
	@Pattern(regexp = "Single Bed|Double Bed|King Size Bed", message = "Invalid bed type")
	@Column(name = "bed_type")
	private String bedType;

	@Positive(message = "Max occupancy must be a positive number")
	@Column(name = "max_occupancy")
	private int maxOccupancy;

	@DecimalMin(value = "0.00", inclusive = false, message = "Base fare must be greater than 0.00")
	@Column(name = "base_fare")
	private double baseFare;

	@Column(name = "is_ac")
	private boolean isAC;

	@Column(name = "availability_status")
	private boolean availabilityStatus;

	@ManyToMany(mappedBy = "rooms", cascade = CascadeType.REMOVE)
	private Set<Reservation> reservations = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	
	private Hotel hotel;

	public Room() {
		super();
	}

	public Room(Long roomId,
			@NotBlank(message = "Room size is required") @Size(max = 20, message = "Room size must be at most 20 characters") String roomSize,
			@NotBlank(message = "Bed type is required") @Size(max = 20, message = "Bed type must be at most 20 characters") @Pattern(regexp = "single bed|double bed|king size", message = "Invalid bed type") String bedType,
			@Positive(message = "Max occupancy must be a positive number") int maxOccupancy,
			@DecimalMin(value = "0.00", inclusive = false, message = "Base fare must be greater than 0.00") double baseFare,
			boolean isAC, boolean availabilityStatus, Set<Reservation> reservations, Hotel hotel) {
		super();
		this.roomId = roomId;
		this.roomSize = roomSize;
		this.bedType = bedType;
		this.maxOccupancy = maxOccupancy;
		this.baseFare = baseFare;
		this.isAC = isAC;
		this.availabilityStatus = availabilityStatus;
		this.reservations = reservations;
		this.hotel = hotel;
	}

	public Room(
			@NotBlank(message = "Room size is required") @Size(max = 20, message = "Room size must be at most 20 characters") String roomSize,
			@NotBlank(message = "Bed type is required") @Size(max = 20, message = "Bed type must be at most 20 characters") @Pattern(regexp = "single bed|double bed|king size", message = "Invalid bed type") String bedType,
			@Positive(message = "Max occupancy must be a positive number") int maxOccupancy,
			@DecimalMin(value = "0.00", inclusive = false, message = "Base fare must be greater than 0.00") double baseFare,
			boolean isAC, boolean availabilityStatus, Set<Reservation> reservations, Hotel hotel) {
		super();
		this.roomSize = roomSize;
		this.bedType = bedType;
		this.maxOccupancy = maxOccupancy;
		this.baseFare = baseFare;
		this.isAC = isAC;
		this.availabilityStatus = availabilityStatus;
		this.reservations = reservations;
		this.hotel = hotel;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public boolean isAC() {
		return isAC;
	}

	public void setAC(boolean isAC) {
		this.isAC = isAC;
	}

	public boolean isAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomSize=" + roomSize + ", bedType=" + bedType + ", maxOccupancy="
				+ maxOccupancy + ", baseFare=" + baseFare + ", isAC=" + isAC + ", availabilityStatus="
				+ availabilityStatus + ", reservations=" + reservations + ", hotel=" + hotel + "]";
	}

}
