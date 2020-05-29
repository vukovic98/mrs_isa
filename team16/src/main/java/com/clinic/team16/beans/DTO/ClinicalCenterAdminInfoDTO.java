package com.clinic.team16.beans.DTO;

public class ClinicalCenterAdminInfoDTO {
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String city;
	private String country;
	private String insuranceNumber;
	private String email;
	private String password;
	private String clinicCenter;

	public ClinicalCenterAdminInfoDTO() {
	}

	public ClinicalCenterAdminInfoDTO(String firstName, String lastName, String address, String phoneNumber,
			String city, String country, String insuranceNumber, String email, String password, String clinicCenter) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.country = country;
		this.insuranceNumber = insuranceNumber;
		this.email = email;
		this.password = password;
		this.clinicCenter = clinicCenter;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClinicCenter() {
		return clinicCenter;
	}

	public void setClinicCenter(String clinicCenter) {
		this.clinicCenter = clinicCenter;
	}

}
