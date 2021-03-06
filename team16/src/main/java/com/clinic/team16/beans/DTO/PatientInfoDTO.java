package com.clinic.team16.beans.DTO;

public class PatientInfoDTO {
	private String name;
	private String email;
	private String city;
	private String country;
	private String address;
	private String phone;
	private String insurance;
	private long medicalRecordID;

	public PatientInfoDTO() {
	}

	public PatientInfoDTO(String name, String email, String city, String country, String address, String phone,
			String insurance, long medicalRecordID) {
		this.name = name;
		this.email = email;
		this.city = city;
		this.country = country;
		this.address = address;
		this.phone = phone;
		this.insurance = insurance;
		this.medicalRecordID = medicalRecordID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public long getMedicalRecordID() {
		return medicalRecordID;
	}

	public void setMedicalRecordID(long medicalRecordID) {
		this.medicalRecordID = medicalRecordID;
	}

}
