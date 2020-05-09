package com.clinic.team16.beans.DAO;

public class ClinicInfoDAO {
	private long clinicID;
	private String name;
	private String address;
	private String description;

	public ClinicInfoDAO() {
	}

	public ClinicInfoDAO(long clinicID, String name, String address, String description) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.clinicID = clinicID;
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
