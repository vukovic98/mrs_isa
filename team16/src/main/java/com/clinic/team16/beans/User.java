package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
@Embeddable
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends org.springframework.security.core.userdetails.User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
	
	@Enumerated(EnumType.STRING)
	private Role role;
	

	public User() {
		super("foo", "foo", new ArrayList<>());
	}

	public User(String email, String password) {
		super(email,password,new ArrayList<>());
	}
	
	public User(String email, String password, String firstName, String lastName, String address, String city,
			String country, String phoneNumber, String insuranceNumber, Role role) {
		super(email, password, new ArrayList<>());
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		this.role = role;
	}

	public User(User u) {
		super(u.email,u.password,new ArrayList<>());
		this.address = u.address;
		this.city = u.city;
		this.country = u.country;
		this.email = u.email;
		this.firstName = u.firstName;
		this.id = u.id;
		this.insuranceNumber = u.insuranceNumber;
		this.lastName = u.lastName;
		this.password = u.password;
		this.phoneNumber = u.phoneNumber;
		this.role = u.role;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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