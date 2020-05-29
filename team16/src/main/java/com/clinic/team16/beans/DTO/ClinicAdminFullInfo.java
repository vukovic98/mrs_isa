package com.clinic.team16.beans.DTO;

public class ClinicAdminFullInfo {
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String country;
	private String address;
	private String phoneNumber;
	private String insuranceNumber;
	private String clinic;
	private String password;

	public ClinicAdminFullInfo() {
	}

	public ClinicAdminFullInfo(String firstName, String lastName, String email, String password, String city,
			String country, String address, String phoneNumber, String insuranceNumber, String clinic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.country = country;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		this.clinic = clinic;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

}
