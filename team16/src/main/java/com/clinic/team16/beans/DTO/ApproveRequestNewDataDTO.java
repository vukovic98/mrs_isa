package com.clinic.team16.beans.DTO;

public class ApproveRequestNewDataDTO {
private int ordId;
private long requestId;
private String doctorEmail;
private String newDate;
public int getOrdId() {
	return ordId;
}
public void setOrdId(int ordId) {
	this.ordId = ordId;
}
public long getRequestId() {
	return requestId;
}
public void setRequestId(long requestId) {
	this.requestId = requestId;
}
public String getDoctorEmail() {
	return doctorEmail;
}
public void setDoctorEmail(String doctorEmail) {
	this.doctorEmail = doctorEmail;
}
public String getNewDate() {
	return newDate;
}
public void setNewDate(String newDate) {
	this.newDate = newDate;
}
public ApproveRequestNewDataDTO() {
	super();
}
public ApproveRequestNewDataDTO(int ordId, long requestId, String doctorEmail, String newDate) {
	super();
	this.ordId = ordId;
	this.requestId = requestId;
	this.doctorEmail = doctorEmail;
	this.newDate = newDate;
}


}
