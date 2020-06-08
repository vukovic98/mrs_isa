package com.clinic.team16.beans.DTO;

public class LeaveRequestIdDTO {
	private String id;
	private String reason;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LeaveRequestIdDTO() {
		super();
	}

	public LeaveRequestIdDTO(String id) {
		super();
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveRequestIdDTO(String id, String reason) {
		super();
		this.id = id;
		this.reason = reason;
	}
	
	
}
