package com.clinic.team16.beans.DTO;

public class ClinicAddDTO {
	private String name;
	private String address;
	private String description;
	private long pricelist;

	public ClinicAddDTO() {
	}

	public ClinicAddDTO(String name, String address, String description, long pricelist) {
		this.name = name;
		this.address = address;
		this.description = description;
		this.pricelist = pricelist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPricelist() {
		return pricelist;
	}

	public void setPricelist(long pricelist) {
		this.pricelist = pricelist;
	}

}
