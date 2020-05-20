package com.clinic.team16.beans.DTO;

public class PatientPasswordDTO {
	private String password;

	public PatientPasswordDTO() {
		
	}
	public PatientPasswordDTO(String password) {
		super();
		this.password = password;
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
