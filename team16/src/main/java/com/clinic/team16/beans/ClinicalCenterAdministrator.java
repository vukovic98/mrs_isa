package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

@Entity
@Embeddable
public class ClinicalCenterAdministrator extends User {

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "ClinicalCenter_ID")
	public ClinicalCenter clinicalCenter;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenterAdministrator_registrationRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public ArrayList<RegistrationRequest> registrationRequest;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenterAdministrator_appointmentRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public ArrayList<AppointmentRequest> appointmentRequest;

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

	public ArrayList<AppointmentRequest> getAppointmentRequest() {
		return appointmentRequest;
	}

	public void setAppointmentRequest(ArrayList<AppointmentRequest> appointmentRequest) {
		this.appointmentRequest = appointmentRequest;
	}

	public ArrayList<RegistrationRequest> getRegistrationRequest() {
		if (registrationRequest == null)
			registrationRequest = new ArrayList<RegistrationRequest>();
		return registrationRequest;
	}

	public java.util.Iterator getIteratorRegistrationRequest() {
		if (registrationRequest == null)
			registrationRequest = new ArrayList<RegistrationRequest>();
		return registrationRequest.iterator();
	}

	public void setRegistrationRequest(ArrayList<RegistrationRequest> newRegistrationRequest) {
		removeAllRegistrationRequest();
		for (java.util.Iterator iter = newRegistrationRequest.iterator(); iter.hasNext();)
			addRegistrationRequest((RegistrationRequest) iter.next());
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

	public void removeAllRegistrationRequest() {
		if (registrationRequest != null) {
			RegistrationRequest oldRegistrationRequest;
			for (java.util.Iterator iter = getIteratorRegistrationRequest(); iter.hasNext();) {
				oldRegistrationRequest = (RegistrationRequest) iter.next();
				iter.remove();
				oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator) null);
			}
		}
	}

}