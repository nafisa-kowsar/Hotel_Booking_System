package com.hexaware.ccozyhaven.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
 * Author: Haswanth
 * 
 * Entity description: contains properties related to user , getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "User_Details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

	@Id
	@Column(name = "user_id")
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", initialValue = 1, allocationSize = 1)
	private Long userId;

	@NotBlank(message = "Username is required")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username should contain only alphanumeric characters and underscores")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@Size(max = 10, message = "Contact number must be at most 10 characters")
	@Pattern(regexp = "\\d{10}", message = "Invalid contact number")
	@Column(name = "contact_number")
	private String contactNumber;

	@Pattern(regexp = "^(Male|Female|Non-Binary)$", message = "Invalid gender")
	private String gender;

	@NotBlank(message = "Address is required")
	private String address;

	private String role;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	@JsonIgnoreProperties("user")
	private Set<Reservation> reservations = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Review> reviews = new HashSet<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Payment> paymentSet = new HashSet<>();

	public User() {
		super();
	}

	public User(Long userId,
			@NotBlank(message = "Username is required") @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username should contain only alphanumeric characters and underscores") String username,
			@NotBlank(message = "Password is required") String password,
			@Email(message = "Invalid email format") String email,
			@NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@Size(max = 10, message = "Contact number must be at most 10 characters") @Pattern(regexp = "\\d{10}", message = "Invalid contact number") String contactNumber,
			@Pattern(regexp = "^(male|female|non-binary)$", message = "Invalid gender") String gender,
			@NotBlank(message = "Address is required") String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGender() {
		return gender;
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

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contactNumber=" + contactNumber + ", gender=" + gender + ", address="
				+ address + ", role=" + role + ", reservations=" + reservations + ", reviews=" + reviews + "]";
	}

}
