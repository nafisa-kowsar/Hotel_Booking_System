package com.hexaware.ccozyhaven.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Author: Nafisa 
 * 
 * Entity description: contains properties related to Administrator, getter and setters , 
 * constructors and relevant validations and mappings
*/

@Entity
@Table(name = "Administrator_Details")
public class Administrator {

	@Id
	@Column(name = "admin_id")
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    @SequenceGenerator(name = "admin_sequence", initialValue = 10001, allocationSize = 1)
	private Long adminId;

	@Column(name = "firstName")
	private String adminFirstName;

	@Column(name = "lastName")
	private String adminLastName;

	@NotBlank(message = "Username is required")
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username must contain only letters, numbers, underscores, and hyphens")
	@Column(name = "UserName", nullable = false, unique = true)
	private String username;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 6, message = "Password must be at least 6 characters")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "Password cannot be blank")
	@Email(message = "Password cannot be blank")
	@Column(name = "email")
	private String email;

	@Column(columnDefinition = "varchar(5) default 'Admin'", nullable = false)
	private String role;

	public Administrator() {
		super();
	}

	public Administrator(Long adminId, String adminFirstName, String adminLastName,
			@NotBlank(message = "Username is required") @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username must contain only letters, numbers, underscores, and hyphens") String username,
			@NotBlank(message = "Password cannot be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotBlank(message = "Password cannot be blank") @Email(message = "Password cannot be blank") String email,
			String role) {
		super();
		this.adminId = adminId;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", adminFirstName=" + adminFirstName + ", adminLastName="
				+ adminLastName + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + "]";
	}

}
