package com.clinic.team16.beans.DTO;

public class ClinicAdminInfoDTO {
	private String name;
	private String email;
	private String city;
	private String country;
	private String clinicName;

	public ClinicAdminInfoDTO() {
	}

	public ClinicAdminInfoDTO(String name, String email, String city, String country, String clinicName) {
		this.name = name;
		this.email = email;
		this.city = city;
		this.country = country;
		this.clinicName = clinicName;
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

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

}
