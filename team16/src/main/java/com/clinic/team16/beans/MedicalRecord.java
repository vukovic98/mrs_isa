package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MedicalRecord_ID", nullable = false)
	private long medicalRecordId;

	@Column(name = "Height", nullable = false)
	private int height;

	@Column(name = "Weight", nullable = false)
	private int weight;

	@Column(name = "BloodType", nullable = false)
	private String bloodType;

	@Column(name = "BloodPressure", nullable = false)
	private int bloodPressure;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "medicalRecord_allergies", joinColumns = @JoinColumn(name = "medicalRedord_id"))
	private List<Allergies> allergies;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "medicalRecord_perscriptions", joinColumns = @JoinColumn(name = "medicalRedord_id"))
	private List<Medication> perscriptions;

	@Column(name = "MedicalHistory", nullable = false)
	private String medicalHistory;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Patient_ID")
	public Patient patient;

	public MedicalRecord() {
		super();
	}

	public MedicalRecord(int height, int weight, String bloodType, int bloodPressure, ArrayList<Allergies> allergies,
			ArrayList<Medication> perscriptions, String medicalHistory, Patient patient) {
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

	public List<Allergies> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<Allergies> allergies) {
		this.allergies = allergies;
	}

	public List<Medication> getPerscriptions() {
		return perscriptions;
	}

	public void setPerscriptions(ArrayList<Medication> perscriptions) {
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