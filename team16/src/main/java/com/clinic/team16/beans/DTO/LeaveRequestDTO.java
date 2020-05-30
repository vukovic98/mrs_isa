package com.clinic.team16.beans.DTO;


public class LeaveRequestDTO {
	private String dateFrom;
	private String dateTo;

	public LeaveRequestDTO() {
	}

	public LeaveRequestDTO(String dateFrom, String dateTo) {
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

}
