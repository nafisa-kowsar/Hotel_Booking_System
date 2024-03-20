package com.hexaware.ccozyhaven.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author: Nafisa 
 * 
 * Entity description: contains properties related to HotelOwner , getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "HotelOwner_Details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "hotelOwnerId")
public class HotelOwner {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_owner_sequence")
	@SequenceGenerator(name = "hotel_owner_sequence", initialValue = 9091, allocationSize = 1)
	private Long hotelOwnerId;

	@Column(name = "hotel_owner_name")
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String hotelOwnerName;

	@NotBlank(message = "Username is required")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username should contain only alphanumeric characters and underscores")
	private String username;

	@Column(name = "password")
	@NotBlank(message = "Password cannot be blank")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@Column(name = "email")
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "^(Male|Female|Non-Binary)$", message = "Invalid gender")
	private String gender;

	@NotBlank(message = "Address is required")
	private String address;

	private String role = "HOTEL_OWNER";

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id")
	@JsonBackReference
	private Hotel hotel = new Hotel();

	public HotelOwner() {
		super();
	}

	public HotelOwner(Long hotelOwnerId,
			@NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String hotelOwnerName,
			@NotBlank(message = "Username is required") @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username should contain only alphanumeric characters and underscores") String username,
			@NotBlank(message = "Password cannot be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") String email,
			@Pattern(regexp = "^(male|female|non-binary)$", message = "Invalid gender") String gender,
			@NotBlank(message = "Address is required") String address) {
		super();
		this.hotelOwnerId = hotelOwnerId;
		this.hotelOwnerName = hotelOwnerName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getHotelOwnerId() {
		return hotelOwnerId;
	}

	public void setHotelOwnerId(Long hotelOwnerId) {
		this.hotelOwnerId = hotelOwnerId;
	}

	public String getHotelOwnerName() {
		return hotelOwnerName;
	}

	public void setHotelOwnerName(String hotelOwnerName) {
		this.hotelOwnerName = hotelOwnerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	@JsonIgnore
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "HotelOwner [hotelOwnerId=" + hotelOwnerId + ", hotelOwnerName=" + hotelOwnerName + ", password="
				+ password + ", email=" + email + ", gender=" + gender + ", address=" + address + ", hotel=" + hotel
				+ ", role=" + role + "]";
	}

}
