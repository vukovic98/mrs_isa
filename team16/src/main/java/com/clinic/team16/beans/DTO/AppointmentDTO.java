package com.clinic.team16.beans.DTO;

import java.util.Date;

import com.clinic.team16.beans.AppointmentType;

public class AppointmentDTO {
	private long id;
	private String datetime;
	private double duration;
	private String doctor;
	private String patient;
	private AppointmentType appointmentType;
	private String price;
	private String roomNo;
	private String roomName;
	private Double discount;
	public AppointmentDTO() {
		super();
	}
	public AppointmentDTO(long id, String datetime, double duration, String doctor, String patient,
			AppointmentType appointmentType, String price, String roomNo) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.duration = duration;
		this.doctor = doctor;
		this.patient = patient;
		this.appointmentType = appointmentType;
		this.price = price;
		this.roomNo = roomNo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
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
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public AppointmentType getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public AppointmentDTO(long id, String datetime, double duration, String doctor, String patient,
			AppointmentType appointmentType, String price, String roomNo, String roomName, Double discount) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.duration = duration;
		this.doctor = doctor;
		this.patient = patient;
		this.appointmentType = appointmentType;
		this.price = price;
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.discount = discount;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	

}
