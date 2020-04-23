package com.clinic.team16.beans;

import java.util.*;

public class AppointmentRequest {

	private Boolean approved;

	private Date requestDate;

	public ClinicalCenterAdministrator clinicalCenterAdministrator;

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