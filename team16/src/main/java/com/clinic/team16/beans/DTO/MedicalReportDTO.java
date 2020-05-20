package com.clinic.team16.beans.DTO;

import java.util.ArrayList;
import java.util.List;

import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.beans.Medication;

public class MedicalReportDTO {
	private long id;
	private String details;
	private String doctor;
	private String patient;
	private String clinic;
	private ArrayList<String> medications = new ArrayList<>();
	private ArrayList<String> diagnosis = new ArrayList<>();

	private long nurseId;

	public MedicalReportDTO() {
	}

	public MedicalReportDTO(long id, String details, String doctor, String patient, String clinic, List<Medication> med,
			List<Diagnosis> diag, long nurseId) {
		this.id = id;
		this.details = details;
		this.doctor = doctor;
		this.patient = patient;
		this.clinic = clinic;
		this.addMedications(med);
		this.addDiagnosis(diag);
		this.nurseId = nurseId;
	}

	public long getNurseId() {
		return nurseId;
	}

	public void setNurseId(long nurseId) {
		this.nurseId = nurseId;
	}

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public ArrayList<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}

	public ArrayList<String> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(ArrayList<String> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void addMedications(List<Medication> medications) {
		for (Medication m : medications)
			this.medications.add(m.getName());
	}

	public void addDiagnosis(List<Diagnosis> diagnosis) {
		for (Diagnosis d : diagnosis)
			this.diagnosis.add(d.getName());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

}
