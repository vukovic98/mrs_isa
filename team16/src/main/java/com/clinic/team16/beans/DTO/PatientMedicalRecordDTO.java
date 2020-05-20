package com.clinic.team16.beans.DTO;

import java.util.List;

import com.clinic.team16.beans.Allergies;
import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.Medication;

public class PatientMedicalRecordDTO {
	private String fullName;
	private MedicalRecord medicalRecord;
	public PatientMedicalRecordDTO() {
		
	}
	public PatientMedicalRecordDTO(String fullName, MedicalRecord medicalRecord) {
		super();
		this.fullName = fullName;
		this.medicalRecord = medicalRecord;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

}