package com.clinic.team16.beans.DTO;

public class PricelistItemDTO {
	private String appointmentType;

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public PricelistItemDTO(String appointmentType) {
		super();
		this.appointmentType = appointmentType;
	}
	public PricelistItemDTO() {}
}
