package com.clinic.team16.beans.DTO;

import java.util.Date;

public class LeaveRequestDTO {
	private Date dateFrom;
	private Date dateTo;

	public LeaveRequestDTO() {
	}

	public LeaveRequestDTO(Date dateFrom, Date dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}
