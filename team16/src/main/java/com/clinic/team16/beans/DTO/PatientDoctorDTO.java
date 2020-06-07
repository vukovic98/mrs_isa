package com.clinic.team16.beans.DTO;

public class PatientDoctorDTO {
private String insurance;
private String name;
private String phone;
public String getInsurance() {
	return insurance;
}
public void setInsurance(String insurance) {
	this.insurance = insurance;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public PatientDoctorDTO() {
	super();
}
public PatientDoctorDTO(String insurance, String name, String phone) {
	super();
	this.insurance = insurance;
	this.name = name;
	this.phone = phone;
}


}
