package com.clinic.team16.beans.DTO;

public class CalendarDataDTO {
	public String date;
	public String patient;
	public String doctor;
	public String appointmentType;
	public double duration;


	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public CalendarDataDTO() {
		super();
	}

	public CalendarDataDTO(String date, String patient, String doctor, String appointmentType, double duration) {
		super();
		this.date = date;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
		this.duration = duration;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
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
