package com.clinic.team16.beans.DTO;


public class LeaveRequestDTO {
	private String email;
	private String name;
	private String dateFrom;
	private String dateTo;
	private boolean approved;
	public long getRequestID() {
		return requestID;
	}

	public void setRequestID(long requestID) {
		this.requestID = requestID;
	}

	private long requestID;
	public LeaveRequestDTO() {
	}

	public LeaveRequestDTO(String dateFrom, String dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	
	public LeaveRequestDTO(String name, String email, String dateFrom, String dateTo) {
		super();
		this.name = name;
		this.email = email;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public LeaveRequestDTO(String email, String name, String dateFrom, String dateTo, boolean approved,
			long requestID) {
		super();
		this.email = email;
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.approved = approved;
		this.requestID = requestID;
	}



}
