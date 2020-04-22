
/***********************************************************************
 * Module:  RegistrationRequest.java
 * Author:  Vladimir
 * Purpose: Defines the Class RegistrationRequest
 ***********************************************************************/

import java.util.*;

public class RegistrationRequest {

	private boolean approved;

	public User user;

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