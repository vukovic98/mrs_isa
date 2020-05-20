package com.clinic.team16.beans.DTO;

public class UserLoginDTO {
	private String jwt;
	private String email;
	private String role;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
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
	public UserLoginDTO(String jwt, String email, String role) {
		super();
		this.jwt = jwt;
		this.email = email;
		this.role = role;
	}
	public UserLoginDTO() {
		super();
	}
	

	
	
}
