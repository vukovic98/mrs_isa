package com.clinic.team16.beans.DTO;

import java.util.Date;

public class AppointmentDTO {
	private long id;
	private Date datetime;
	private double duration;
	private String doctor;
	private String patient;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getDoctor() {
		return doctor;
	}
	public AppointmentDTO() {
		super();
	}
	public AppointmentDTO(long id, Date datetime, double duration, String doctor, String patient) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.duration = duration;
		this.doctor = doctor;
		this.patient = patient;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
}
