package com.clinic.team16.beans.DTO;

public class DoctorAvgDTO {
	private String name;
	private Double average;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public DoctorAvgDTO() {
		super();
	}
	public DoctorAvgDTO(String name, Double average) {
		super();
		this.name = name;
		this.average = average;
	}
	
	
}
