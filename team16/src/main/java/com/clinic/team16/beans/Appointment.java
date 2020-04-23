
package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;


@Entity
@Embeddable
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Appointment_ID",nullable = false)
	private long appointmentId;
	
	@Column(name = "DateTime", nullable = false)
	private Date dateTime;
	
	@Column(name = "Duration", nullable = false)
	private double duration;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Ordination_ID")
	public Ordination ordination;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "AppointmentType_ID")
	public AppointmentType appointmentType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MedicalReport_ID")
	public MedicalReport medicalReport;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AppointmentRequest_ID")
	public AppointmentRequest appointmentRequest;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Doctor_ID")
	public Doctor doctor;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Patient_ID")
	public Patient patient;

	public Appointment() {

	}

	public Appointment(Date dateTime, double duration, Ordination ordination, AppointmentType appointmentType,
			MedicalReport medicalReport, AppointmentRequest appointmentRequest, Doctor doctor, Patient patient) {
		super();
		this.dateTime = dateTime;
		this.duration = duration;
		this.ordination = ordination;
		this.appointmentType = appointmentType;
		this.medicalReport = medicalReport;
		this.appointmentRequest = appointmentRequest;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Ordination getOrdination() {
		return ordination;
	}

	public void setOrdination(Ordination ordination) {
		this.ordination = ordination;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public MedicalReport getMedicalReport() {
		return medicalReport;
	}

	public void setMedicalReport(MedicalReport medicalReport) {
		this.medicalReport = medicalReport;
	}

	public AppointmentRequest getAppointmentRequest() {
		return appointmentRequest;
	}

	public void setAppointmentRequest(AppointmentRequest appointmentRequest) {
		this.appointmentRequest = appointmentRequest;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor newDoctor) {
		if (this.doctor == null || !this.doctor.equals(newDoctor)) {
			if (this.doctor != null) {
				Doctor oldDoctor = this.doctor;
				this.doctor = null;
				oldDoctor.removeAppointment(this);
			}
			if (newDoctor != null) {
				this.doctor = newDoctor;
				this.doctor.addAppointment(this);
			}
		}
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient newPatient) {
		if (this.patient == null || !this.patient.equals(newPatient)) {
			if (this.patient != null) {
				Patient oldPatient = this.patient;
				this.patient = null;
				oldPatient.removeAppointment(this);
			}
			if (newPatient != null) {
				this.patient = newPatient;
				this.patient.addAppointment(this);
			}
		}
	}

}