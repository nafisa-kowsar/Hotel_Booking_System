package com.hexaware.ccozyhaven.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/*
 * Author: Nafisa 
 * 
 * Entity description: contains properties related to Hotel , getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "hotel_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "hotelId")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
    @SequenceGenerator(name = "hotel_sequence", initialValue = 701, allocationSize = 1)
  
	private Long hotelId;

	@Column(name = "hotel_name")
	@NotBlank(message = "Hotel name cannot be blank")
	@Size(min = 3, max = 100, message = "Hotel name must be between 3 and 100 characters")
	private String hotelName;

	@NotBlank(message = "Location cannot be blank")
	@Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
	private String location;

	@NotNull(message = "Dining information cannot be null")
	@Column(name = "has_dining")
	private boolean hasDining;

	@NotNull(message = "Parking information cannot be null")
	@Column(name = "has_parking")
	private boolean hasParking;

	// @NotNull(message = "Wifi information cannot be null")
	@Column(name = "has_free_wifi")
	private boolean hasFreeWiFi;

	@NotNull(message = "Room Service information cannot be null")
	@Column(name = "has_room_service")
	private boolean hasRoomService;

	@NotNull(message = "Swimming pool information cannot be null")
	@Column(name = "has_swimming_pool")
	private boolean hasSwimmingPool;

	@NotNull(message = "Fitness Center information cannot be null")
	@Column(name = "has_fitness_center")
	private boolean hasFitnessCenter;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Room> room = new HashSet<>();

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Reservation> reservation = new HashSet<>();

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Review> review = new HashSet<>();

	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id")
	@JsonIdentityReference(alwaysAsId = true)
	private HotelOwner hotelOwner;

	public Hotel() {
		super();
	}

	public Hotel(Long hotelId,
			@NotBlank(message = "Hotel name cannot be blank") @Size(min = 3, max = 100, message = "Hotel name must be between 3 and 100 characters") String hotelName,
			@NotBlank(message = "Location cannot be blank") @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters") String location,
			@NotNull(message = "Dining information cannot be null") boolean hasDining,
			@NotNull(message = "Parking information cannot be null") boolean hasParking,
			@NotNull(message = "Wifi information cannot be null") boolean hasFreeWiFi,
			@NotNull(message = "Room Service information cannot be null") boolean hasRoomService,
			@NotNull(message = "Swimming pool information cannot be null") boolean hasSwimmingPool,
			@NotNull(message = "Fitness Center information cannot be null") boolean hasFitnessCenter) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.location = location;
		this.hasDining = hasDining;
		this.hasParking = hasParking;
		this.hasFreeWiFi = hasFreeWiFi;
		this.hasRoomService = hasRoomService;
		this.hasSwimmingPool = hasSwimmingPool;
		this.hasFitnessCenter = hasFitnessCenter;
	}

	public Hotel(
			@NotBlank(message = "Hotel name cannot be blank") @Size(min = 3, max = 100, message = "Hotel name must be between 3 and 100 characters") String hotelName,
			@NotBlank(message = "Location cannot be blank") @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters") String location,
			@NotNull(message = "Dining information cannot be null") boolean hasDining,
			@NotNull(message = "Parking information cannot be null") boolean hasParking,
			@NotNull(message = "Wifi information cannot be null") boolean hasFreeWiFi,
			@NotNull(message = "Room Service information cannot be null") boolean hasRoomService,
			@NotNull(message = "Swimming pool information cannot be null") boolean hasSwimmingPool,
			@NotNull(message = "Fitness Center information cannot be null") boolean hasFitnessCenter) {
		super();
		this.hotelName = hotelName;
		this.location = location;
		this.hasDining = hasDining;
		this.hasParking = hasParking;
		this.hasFreeWiFi = hasFreeWiFi;
		this.hasRoomService = hasRoomService;
		this.hasSwimmingPool = hasSwimmingPool;
		this.hasFitnessCenter = hasFitnessCenter;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isHasDining() {
		return hasDining;
	}

	public void setHasDining(boolean hasDining) {
		this.hasDining = hasDining;
	}

	public boolean isHasParking() {
		return hasParking;
	}

	public void setHasParking(boolean hasParking) {
		this.hasParking = hasParking;
	}

	public boolean isHasFreeWiFi() {
		return hasFreeWiFi;
	}

	public void setHasFreeWiFi(boolean hasFreeWiFi) {
		this.hasFreeWiFi = hasFreeWiFi;
	}

	public boolean isHasRoomService() {
		return hasRoomService;
	}

	public void setHasRoomService(boolean hasRoomService) {
		this.hasRoomService = hasRoomService;
	}

	public boolean isHasSwimmingPool() {
		return hasSwimmingPool;
	}

	public void setHasSwimmingPool(boolean hasSwimmingPool) {
		this.hasSwimmingPool = hasSwimmingPool;
	}

	public boolean isHasFitnessCenter() {
		return hasFitnessCenter;
	}

	public void setHasFitnessCenter(boolean hasFitnessCenter) {
		this.hasFitnessCenter = hasFitnessCenter;
	}

	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	public Set<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}

	public Set<Review> getReview() {
		return review;
	}

	public void setReview(Set<Review> review) {
		this.review = review;
	}

	public HotelOwner getHotelOwner() {
		return hotelOwner;
	}

	public void setHotelOwner(HotelOwner hotelOwner) {
		this.hotelOwner = hotelOwner;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", location=" + location + ", hasDining="
				+ hasDining + ", hasParking=" + hasParking + ", hasFreeWiFi=" + hasFreeWiFi + ", hasRoomService="
				+ hasRoomService + ", hasSwimmingPool=" + hasSwimmingPool + ", hasFitnessCenter=" + hasFitnessCenter
				+ ", room=" + room + ", reservation=" + reservation + ", review=" + review + "]";
	}
}
