package com.hexaware.ccozyhaven.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
/*
 * Author: Nafisa
 * 
 * DTO description: Represents the data transfer object for Administrator entity.
 * It contains fields relevant to Administrator, getter and setters, constructors, and relevant validations.
 */



public class AdministratorDTO {

	private Long adminId;
	
	private String adminFirstName;

	
	private String adminLastName;
	private String username;
	
	@Size(min=5,max=20)
	private String password;
	
	@Email
	private String email;
	
	private String role;

	public AdministratorDTO() {
		super();

	}

	public AdministratorDTO(Long adminId, String adminFirstName, String adminLastName, String username,
			@Size(min = 5, max = 20) String password, @Email String email, String role) {
		super();
		this.adminId = adminId;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}


	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminFirstName() {
		return adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminLastName() {
		return adminLastName;
	}

	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdministratorDTO [adminId=" + adminId + ", adminFirstName=" + adminFirstName + ", adminLastName="
				+ adminLastName + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + "]";
	}


}