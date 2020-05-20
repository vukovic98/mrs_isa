package com.clinic.team16.beans.DTO;

public class PricelistItemDTO {
	private String appointmentType;
	private String price;
	

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public PricelistItemDTO(String appointmentType, String price) {
		super();
		this.appointmentType = appointmentType;
		this.price = price;
	}
	public PricelistItemDTO() {}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
