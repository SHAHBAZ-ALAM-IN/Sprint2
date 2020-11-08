package com.cg.onlineshopping.modal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserRequest {
	@NotBlank
	@Size(min = 2, max = 20)
	private String userId;
	@NotBlank
	@Size(min = 2, max = 20)
	private String password;
	@NotBlank
	@Size(min = 2, max = 20)
	private String role;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
