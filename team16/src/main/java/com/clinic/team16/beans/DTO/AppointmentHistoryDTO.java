package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.Doctor;

public class AppointmentHistoryDTO {
	private String datetime;
	private String appointmentType;
	private String doctor;
	private String clinic;
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public AppointmentHistoryDTO(String datetime, String appointmentType, String doctor, String clinic) {
		super();
		this.datetime = datetime;
		this.appointmentType = appointmentType;
		this.doctor = doctor;
		this.clinic = clinic;
	}
	
public AppointmentHistoryDTO() {
}
}

