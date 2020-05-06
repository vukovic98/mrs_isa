package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class ClinicalCenterAdministrator extends User {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "ClinicalCenter_ID")
	public ClinicalCenter clinicalCenter;
	
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenterAdministrator_registrationRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public List<RegistrationRequest> registrationRequest;
	
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenterAdministrator_appointmentRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public List<AppointmentRequest> appointmentRequest;

	public ClinicalCenterAdministrator() {
		super();
	}

	public ClinicalCenterAdministrator(ClinicalCenter clinicalCenter,
			ArrayList<RegistrationRequest> registrationRequest, ArrayList<AppointmentRequest> appointmentRequest) {
		super();
		this.clinicalCenter = clinicalCenter;
		this.registrationRequest = registrationRequest;
		this.appointmentRequest = appointmentRequest;
	}

	public ClinicalCenter getClinicalCenter() {
		return clinicalCenter;
	}

	public void setClinicalCenter(ClinicalCenter clinicalCenter) {
		this.clinicalCenter = clinicalCenter;
	}

	public List<AppointmentRequest> getAppointmentRequests() {
		return appointmentRequest;
	}

	public void setAppointmentRequest(ArrayList<AppointmentRequest> appointmentRequest) {
		this.appointmentRequest = appointmentRequest;
	}

	public List<RegistrationRequest> getRegistrationRequests() {
		if (registrationRequest == null)
			registrationRequest = new ArrayList<RegistrationRequest>();
		return registrationRequest;
	}

	

	public void addRegistrationRequest(RegistrationRequest newRegistrationRequest) {
		if (newRegistrationRequest == null)
			return;
		if (this.registrationRequest == null)
			this.registrationRequest = new ArrayList<RegistrationRequest>();
		if (!this.registrationRequest.contains(newRegistrationRequest)) {
			this.registrationRequest.add(newRegistrationRequest);
			newRegistrationRequest.setClinicalCenterAdministrator(this);
		}
	}

	public void removeRegistrationRequest(RegistrationRequest oldRegistrationRequest) {
		if (oldRegistrationRequest == null)
			return;
		if (this.registrationRequest != null)
			if (this.registrationRequest.contains(oldRegistrationRequest)) {
				this.registrationRequest.remove(oldRegistrationRequest);
				oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator) null);
			}
	}

	

}