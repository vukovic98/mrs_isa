package com.clinic.team16.beans.DTO;

import java.util.List;

public class ApproveRequestSurgeryDTO {
	private int ordId;
	private long requestId;
	private List<String> doctorEmails;
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
	public List<String> getDoctorEmails() {
		return doctorEmails;
	}
	public void setDoctorEmails(List<String> doctorEmails) {
		this.doctorEmails = doctorEmails;
	}
	public ApproveRequestSurgeryDTO() {
		super();
	}
	public ApproveRequestSurgeryDTO(int ordId, long requestId, List<String> doctorEmails) {
		super();
		this.ordId = ordId;
		this.requestId = requestId;
		this.doctorEmails = doctorEmails;
	}
	
	
}
