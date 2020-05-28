package com.clinic.team16.beans.DTO;

public class ClinicInfoDTO {
	private long clinicID;
	private String name;
	private String address;
	private String description;
	private double averageGrade;

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public ClinicInfoDTO() {
	}

	

	public ClinicInfoDTO(long clinicID, String name, String address, String description, double averageGrade) {
		super();
		this.clinicID = clinicID;
		this.name = name;
		this.address = address;
		this.description = description;
		this.averageGrade = averageGrade;
	}

	public long getClinicID() {
		return clinicID;
	}

	public void setClinicID(long clinicID) {
		this.clinicID = clinicID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
