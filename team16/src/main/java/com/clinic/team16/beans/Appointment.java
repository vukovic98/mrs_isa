package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Appointment_ID", nullable = false)
	private long appointmentId;

	@Column(name = "DateTime", nullable = false)
	private Date dateTime;

	@Column(name = "Duration", nullable = false)
	private double duration;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Ordination_ID")
	public Ordination ordination;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MedicalReport_ID")
	public MedicalReport medicalReport;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AppointmentRequest_ID")
	public AppointmentRequest appointmentRequest;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Doctor_ID")
	public Doctor doctor;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Patient_ID")
	public Patient patient;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PricelisItem_ID")
	public PricelistItem pricelistItems;

	@Column(name = "Price")
	public Double price;


	
	@Column(name = "Discount")
	private Double discount;
	
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Appointment() {

	}

	public Appointment(long appointmentId, Date dateTime, double duration, Ordination ordination,
			MedicalReport medicalReport, AppointmentRequest appointmentRequest, Doctor doctor, Patient patient,
			PricelistItem pricelistItems) {
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
		this.price = this.pricelistItems.getPrice();
		this.discount = null;
	}
	
	public Appointment(long appointmentId, Date dateTime, double duration, Ordination ordination,
			MedicalReport medicalReport, AppointmentRequest appointmentRequest, Doctor doctor, Patient patient,
			PricelistItem pricelistItems, double disc) {
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
		this.price = this.pricelistItems.getPrice();
		this.discount = disc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public PricelistItem getPricelistItems() {
		return pricelistItems;
	}

	public void setPricelistItems(PricelistItem pricelistItems) {
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

		this.doctor = newDoctor;

	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient newPatient) {

		this.patient = newPatient;

	}

}