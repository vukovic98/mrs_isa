package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.AppointmentType;

public class AppointmentRequestDTO {
	private long requestId;
	private String email;
	private String doctor; // email njegov
	private String dateTime;
	private AppointmentType examType;
	private String onlyTime;
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

	private String patientName;
	private String doctorName;

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
	public AppointmentRequestDTO(String email, String doctor, String dateTime, AppointmentType examType, String patientName,
		super();
		this.email = email;
		this.doctor = doctor;
		this.dateTime = dateTime;
		this.examType = examType;
		this.patientName = patientName;
		this.doctorName = doctorName;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public AppointmentRequestDTO(String email, String doctor, String dateTime, AppointmentType examType, long requestId,
			String patientName, String doctorName) {
		super();
		this.email = email;
		this.doctor = doctor;
		this.dateTime = dateTime;
		this.examType = examType;
		this.requestId = requestId;
		this.patientName = patientName;
		this.doctorName = doctorName;
	}

}
