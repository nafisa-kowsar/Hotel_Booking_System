package com.hexaware.ccozyhaven.dto;

/*
 * Author: Haswanth
 * 
 * DTO description: Represents the data transfer object for User entity.
 * It contains fields relevant to User, getter and setters, constructors, and relevant validations.
 */

public class UserDTO {

	private Long userId;
	private String userFirstName;
	private String userLastName;
	private String username;
	private String password;
	private String email;
	private String contactNumber;
	private String gender;
	private String address;

	private String role;

	public UserDTO() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", contactNumber="
				+ contactNumber + ", gender=" + gender + ", address=" + address + ", role=" + role + "]";
	}

}
