package com.clinic.team16.beans.DTO;

public class ClinicFilterDTO {
	private long clinicID;
	private String name;
	private String address;
	private double averageGrade;
	private String appointmentType;
	private double price;
	public ClinicFilterDTO() {
		
	}
	public ClinicFilterDTO(long clinicID, String name, String address, double averageGrade, String appointmentType,
			double price) {
		super();
		this.clinicID = clinicID;
		this.name = name;
		this.address = address;
		this.averageGrade = averageGrade;
		this.appointmentType = appointmentType;
		this.price = price;
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
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
