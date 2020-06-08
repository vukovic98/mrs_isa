package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;


@Entity
@Embeddable
public class AppointmentRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AppointmentRequest_ID",nullable = false)
	private long appointmentRequestId;
	
	@Column(name = "Approved",nullable = false)
	private Boolean approved;

	@Column(name = "RequestDate",nullable = false)
	private Date requestDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "ClinicalCenterAdmin_ID")
	public ClinicalCenterAdministrator clinicalCenterAdministrator;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Appointment_ID")
	public Appointment appointment;

	public AppointmentRequest() {

	}

	public AppointmentRequest(Boolean approved, Date requestDate,
			ClinicalCenterAdministrator clinicalCenterAdministrator, Appointment appointment) {
		super();
		this.approved = approved;
		this.requestDate = requestDate;
		this.clinicalCenterAdministrator = clinicalCenterAdministrator;
		this.appointment = appointment;
	}

	public Boolean getApproved() {
		return approved;
	}

	public long getAppointmentRequestId() {
		return appointmentRequestId;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public ClinicalCenterAdministrator getClinicalCenterAdministrator() {
		return clinicalCenterAdministrator;
	}

	public void setClinicalCenterAdministrator(ClinicalCenterAdministrator clinicalCenterAdministrator) {
		this.clinicalCenterAdministrator = clinicalCenterAdministrator;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}