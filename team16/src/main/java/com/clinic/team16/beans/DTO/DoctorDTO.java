package com.clinic.team16.beans.DTO;

import java.util.ArrayList;

public class DoctorDTO {
	private long id;
	private String email;
	private String firstName;
	private String lastName;
	private double averageGrade;
	private ArrayList<String> terms;

	public DoctorDTO() {
		super();
	}

	public DoctorDTO(long id, String firstName, String lastName, double averageGrade) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.averageGrade = averageGrade;
	}

	public DoctorDTO(long id, String firstName, String lastName, double averageGrade, String email) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.averageGrade = averageGrade;
	}

	public DoctorDTO(long id, String firstName, String lastName, double averageGrade, String email,
			ArrayList<String> terms) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.averageGrade = averageGrade;
		this.terms = terms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<String> terms) {
		this.terms = terms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

}
