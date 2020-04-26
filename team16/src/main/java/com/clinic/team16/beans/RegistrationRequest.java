package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
@Embeddable
public class RegistrationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RegistrationRequest_ID", nullable = false)
	private long registrationRequestId;

	@Column(name = "approved", nullable = false)
	private boolean approved;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "User_ID")
	public User user;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "ClinicalCenterAdministrator_ID")
	public ClinicalCenterAdministrator clinicalCenterAdministrator;

	public RegistrationRequest() {
		super();
	}

	public RegistrationRequest(boolean approved, User user, ClinicalCenterAdministrator clinicalCenterAdministrator) {
		super();
		this.approved = approved;
		this.user = user;
		this.clinicalCenterAdministrator = clinicalCenterAdministrator;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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