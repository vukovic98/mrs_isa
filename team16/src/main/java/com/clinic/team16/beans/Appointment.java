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
	
	@ElementCollection
	@CollectionTable(name = "clinicCenterAdministrator_pricelistItems",joinColumns = @JoinColumn(name = "pricelistItem_Id"))
	public List<PricelistItem> pricelistItems;

	public Appointment() {

	}

	public Appointment(long appointmentId, Date dateTime, double duration, Ordination ordination,
			MedicalReport medicalReport, AppointmentRequest appointmentRequest, Doctor doctor, Patient patient,
			List<PricelistItem> pricelistItems) {
		super();
		this.appointmentId = appointmentId;
		this.dateTime = dateTime;
		this.duration = duration;
		this.ordination = ordination;
		this.medicalReport = medicalReport;
		this.appointmentRequest = appointmentRequest;
		this.doctor = doctor;
		this.patient = patient;
		this.pricelistItems = pricelistItems;
	}


	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public List<PricelistItem> getPricelistItems() {
		return pricelistItems;
	}

	public void setPricelistItems(List<PricelistItem> pricelistItems) {
		this.pricelistItems = pricelistItems;
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