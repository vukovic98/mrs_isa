package com.clinic.team16.beans.DTO;

public class RegistrationRequestDTO {

	private boolean approved;
	private String patientName;
	private String patientEmail;

	public RegistrationRequestDTO() {
	}

	public RegistrationRequestDTO(boolean approved, String patientName, String patientEmail) {
		this.approved = approved;
		this.patientName = patientName;
		this.patientEmail = patientEmail;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

}
