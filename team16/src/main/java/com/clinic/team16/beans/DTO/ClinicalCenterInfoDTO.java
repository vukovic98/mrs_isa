package com.clinic.team16.beans.DTO;

public class ClinicalCenterInfoDTO {
	private long id;
	private String name;

	public ClinicalCenterInfoDTO() {
	}

	public ClinicalCenterInfoDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
