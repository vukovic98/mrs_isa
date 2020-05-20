package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class RegistrationRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RegistrationRequest_ID", nullable = false)
	private long registrationRequestId;

	@Column(name = "approved", nullable = false)
	private boolean approved;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "User_ID")
	public Patient patient;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ClinicalCenterAdministrator_ID")
	public ClinicalCenterAdministrator clinicalCenterAdministrator;

	public RegistrationRequest() {
		super();
	}

	public RegistrationRequest(boolean approved, Patient patient,
			ClinicalCenterAdministrator clinicalCenterAdministrator) {
		super();
		this.approved = approved;
		this.patient = patient;
		this.clinicalCenterAdministrator = clinicalCenterAdministrator;
	}

	public long getRegistrationRequestId() {
		return registrationRequestId;
	}

	public void setRegistrationRequestId(long registrationRequestId) {
		this.registrationRequestId = registrationRequestId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Patient getUser() {
		return patient;
	}

	public void setUser(Patient patient) {
		this.patient = patient;
	}

	public ClinicalCenterAdministrator getClinicalCenterAdministrator() {
		return clinicalCenterAdministrator;
	}

	public void setClinicalCenterAdministrator(ClinicalCenterAdministrator newClinicalCenterAdministrator) {
		if (this.clinicalCenterAdministrator == null
				|| !this.clinicalCenterAdministrator.equals(newClinicalCenterAdministrator)) {
			if (this.clinicalCenterAdministrator != null) {
				ClinicalCenterAdministrator oldClinicalCenterAdministrator = this.clinicalCenterAdministrator;
				this.clinicalCenterAdministrator = null;
				oldClinicalCenterAdministrator.removeRegistrationRequest(this);
			}
			if (newClinicalCenterAdministrator != null) {
				this.clinicalCenterAdministrator = newClinicalCenterAdministrator;
				this.clinicalCenterAdministrator.addRegistrationRequest(this);
			}
		}
	}

}