package com.expertsvision.erp.core.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDTO {
	
	@JsonProperty("user_id")
	private Integer userId;
	
	@JsonProperty("password")
	private String password;

	
	public LoginDTO() {
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", password=" + password + "]";
	}

}
