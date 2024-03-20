package com.hexaware.ccozyhaven.dto;

public class PasswordDTO {
	
	private String username;
	private String newPassword;
	
	
	public PasswordDTO() {
		super();
		
	}
	
	


	public PasswordDTO(String username, String newPassword) {
		super();
		this.username = username;
		this.newPassword = newPassword;
	}




	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	

}
