package com.clinic.team16.beans.DTO;

public class DoctorDTO {
	private long id;
	private String firstName;
	private String lastName;
	private double averageGrade;

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
