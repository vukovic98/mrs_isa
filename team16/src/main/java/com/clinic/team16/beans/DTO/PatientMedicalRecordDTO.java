package com.clinic.team16.beans.DTO;


import com.clinic.team16.beans.MedicalRecord;

import com.clinic.team16.beans.Medication;

import java.util.List;

import com.clinic.team16.beans.Allergies;


public class PatientMedicalRecordDTO {
	private String fullName;
	private String gender;
	private String birthday;
	private int height;
	private int weight;
	private String bloodType;
	private List<Allergies> allergies;
	private List<Medication> perscription;

	
	public PatientMedicalRecordDTO() {
		
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public List<Allergies> getAllergies() {
		return allergies;
	}


	public void setAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
	}


	public List<Medication> getPerscription() {
		return perscription;
	}


	public void setPerscription(List<Medication> perscription) {
		this.perscription = perscription;
	}


	public PatientMedicalRecordDTO(String fullName, String gender, String birthday, int height, int weight,
			String bloodType, List<Allergies> allergies, List<Medication> perscription) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.bloodType = bloodType;
		this.allergies = allergies;
		this.perscription = perscription;
	}
	
}