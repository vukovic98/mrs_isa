package com.clinic.team16.beans.DTO;

public class CalendarDataDTO {
	public String date;
	public String patient;
	public String appointmentType;

	public CalendarDataDTO() {
	}

	public CalendarDataDTO(String date, String patient, String appointmentType) {
		this.date = date;
		this.patient = patient;
		this.appointmentType = appointmentType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

}
