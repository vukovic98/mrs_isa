package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.AppointmentType;

public class AppointmentRequestDTO {
	private String email;
	private String doctor; //email njegov
	private String dateTime;
	private AppointmentType examType;
	
	public AppointmentRequestDTO() {
		super();
	}
	public AppointmentRequestDTO(String email, String doctor, String dateTime, AppointmentType examType) {
		super();
		this.email = email;
		this.doctor = doctor;
		this.dateTime = dateTime;
		this.examType = examType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public AppointmentType getExamType() {
		return examType;
	}
	public void setExamType(AppointmentType examType) {
		this.examType = examType;
	}

}
