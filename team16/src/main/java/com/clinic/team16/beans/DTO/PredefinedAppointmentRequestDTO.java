package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.AppointmentType;

public class PredefinedAppointmentRequestDTO {
	private String email;
	private String doctor; //email njegov
	private String dateTime;
	private String examType;
	private String onlyTime;
	private String patientName;
	private String doctorName;
	private Double discount;
	private int ordId;
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
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getOnlyTime() {
		return onlyTime;
	}
	public void setOnlyTime(String onlyTime) {
		this.onlyTime = onlyTime;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public PredefinedAppointmentRequestDTO() {
		super();
	}
	public PredefinedAppointmentRequestDTO(String email, String doctor, String dateTime, String examType,
			String onlyTime, String patientName, String doctorName, Double discount) {
		super();
		this.email = email;
		this.doctor = doctor;
		this.dateTime = dateTime;
		this.examType = examType;
		this.onlyTime = onlyTime;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.discount = discount;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	
	
}
