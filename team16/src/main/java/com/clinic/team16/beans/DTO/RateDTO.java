package com.clinic.team16.beans.DTO;

public class RateDTO {
	private String grade;
	private long ID;
	public RateDTO() {
		
	}
	public RateDTO(String grade, long ID) {
		super();
		this.grade = grade;
		this.ID = ID;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public long getID() {
		return ID;
	}
	public void setClinicID(long ID) {
		this.ID = ID;
	}

}
