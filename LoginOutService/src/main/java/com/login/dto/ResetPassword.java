package com.login.dto;

//import javax.validation.constraints.NotBlank;

public class ResetPassword {
	
//	@NotBlank
	private String username;

//	@NotBlank
	private String newPassword;
	
//	@NotBlank
	private String oldPassword;

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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}


