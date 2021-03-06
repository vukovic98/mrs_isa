package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.AppointmentType;

public class AppointmentHistoryDTO {
	private long appointmentID;
	private String datetime;
	private String appointmentType;
	private String doctor;
	private long clinicID;
	private long doctorID;
	private String clinic;
	private boolean approved;
	private boolean hasMedicalReport;
	
	public AppointmentHistoryDTO(long appointmentID, String datetime, String appointmentType, String doctor,
			long clinicID, long doctorID, String clinic, boolean approved, boolean hasMedicalReport) {
		super();
		this.appointmentID = appointmentID;
		this.datetime = datetime;
		this.appointmentType = appointmentType;
		this.doctor = doctor;
		this.clinicID = clinicID;
		this.doctorID = doctorID;
		this.clinic = clinic;
		this.approved = approved;
		this.hasMedicalReport = hasMedicalReport;
	}
	public boolean isHasMedicalReport() {
		return hasMedicalReport;
	}
	public void setHasMedicalReport(boolean hasMedicalReport) {
		this.hasMedicalReport = hasMedicalReport;
	}
	public AppointmentHistoryDTO(long appointmentID, String datetime, String appointmentType, String doctor,
			long clinicID, long doctorID, String clinic, boolean approved) {
		super();
		this.appointmentID = appointmentID;
		this.datetime = datetime;
		this.appointmentType = appointmentType;
		this.doctor = doctor;
		this.clinicID = clinicID;
		this.doctorID = doctorID;
		this.clinic = clinic;
		this.approved = approved;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public long getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}

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

	

public AppointmentHistoryDTO(long appointmentID, String datetime, String appointmentType, String doctor,
			long clinicID, long doctorID, String clinic) {
		super();
		this.appointmentID = appointmentID;
		this.datetime = datetime;
		this.appointmentType = appointmentType;
		this.doctor = doctor;
		this.clinicID = clinicID;
		this.doctorID = doctorID;
		this.clinic = clinic;
	}
public long getClinicID() {
		return clinicID;
	}
	public void setClinicID(long clinicID) {
		this.clinicID = clinicID;
	}
	public long getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}
public AppointmentHistoryDTO() {
}
}

