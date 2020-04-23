package com.clinic.team16.beans;
import java.util.*;

public class MedicalRecord {
   private int height;
   private int weight;
   private String bloodType;
   private int bloodPressure;
   private Collection<String> allergies;
   private Collection<String> perscriptions;
   private String medicalHistory;
   
   public Patient patient;

public MedicalRecord() {
	super();
}

public MedicalRecord(int height, int weight, String bloodType, int bloodPressure, Collection<String> allergies,
		Collection<String> perscriptions, String medicalHistory, Patient patient) {
	super();
	this.height = height;
	this.weight = weight;
	this.bloodType = bloodType;
	this.bloodPressure = bloodPressure;
	this.allergies = allergies;
	this.perscriptions = perscriptions;
	this.medicalHistory = medicalHistory;
	this.patient = patient;
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

public int getBloodPressure() {
	return bloodPressure;
}

public void setBloodPressure(int bloodPressure) {
	this.bloodPressure = bloodPressure;
}

public Collection<String> getAllergies() {
	return allergies;
}

public void setAllergies(Collection<String> allergies) {
	this.allergies = allergies;
}

public Collection<String> getPerscriptions() {
	return perscriptions;
}

public void setPerscriptions(Collection<String> perscriptions) {
	this.perscriptions = perscriptions;
}

public String getMedicalHistory() {
	return medicalHistory;
}

public void setMedicalHistory(String medicalHistory) {
	this.medicalHistory = medicalHistory;
}

public Patient getPatient() {
	return patient;
}

public void setPatient(Patient patient) {
	this.patient = patient;
}

   
   
}