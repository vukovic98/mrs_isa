package com.clinic.team16.beans.DTO;

public class PricelistItemDTO {
	private String appointmentType;
	private String price;
	private String id;
	

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public PricelistItemDTO(String appointmentType, String price, String id) {
		super();
		this.appointmentType = appointmentType;
		this.price = price;
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PricelistItemDTO() {}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
