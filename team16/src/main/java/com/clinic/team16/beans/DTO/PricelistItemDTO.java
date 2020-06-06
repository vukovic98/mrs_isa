package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.AppointmentType;

public class PricelistItemDTO {
	private AppointmentType appointmentType;
	private String price;
	private String id;
	

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public PricelistItemDTO(AppointmentType appointmentType, String price, String id) {
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
