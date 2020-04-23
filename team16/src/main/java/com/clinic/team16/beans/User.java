package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
@Embeddable
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_ID", nullable = false)
	private long id;
	
	@Column(name = "Email", nullable = false)
	private String email;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "FirstName", nullable = false)
	private String firstName;
	
	@Column(name = "LastName", nullable = false)
	private String lastName;
	
	@Column(name = "Address", nullable = false)
	private String address;
	
	@Column(name = "City", nullable = false)
	private String city;
	
	@Column(name = "Country", nullable = false)
	private String country;
	
	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;
	
	@Column(name = "InsuranceNumber", nullable = false)
	private String insuranceNumber;
	

	public User() {
		super();
	}

	public User(String email, String password, String firstName, String lastName, String address, String city,
			String country, String phoneNumber, String insuranceNumber) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
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

}