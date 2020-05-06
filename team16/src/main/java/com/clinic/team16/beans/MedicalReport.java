package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class MedicalReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MedicalReport_ID", nullable = false)
	private long medicalReportId;
	
	@Column(name="Details", nullable = false)
	private String details;

	@Column(name = "Approved", nullable = false)
	private Boolean approved;
    
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "medicalReport_diagnosis", joinColumns = @JoinColumn(name = "medicalReport_Diagnosis_id"))
	public List<Diagnosis> diagnosis;
	
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "medicalReport_medication", joinColumns = @JoinColumn(name = "medicalReport_Medication_id"))
	public List<Medication> medication;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name="MedicalReport_Nurse_ID")
	public Nurse nurse;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(ArrayList<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public void setMedication(ArrayList<Medication> medication) {
		this.medication = medication;
	}

	public MedicalReport(String details, Boolean approved, ArrayList<Diagnosis> diagnosis,
			ArrayList<Medication> medication, Nurse nurse) {
		super();
		this.details = details;
		this.approved = approved;
		this.diagnosis = diagnosis;
		this.medication = medication;
		this.nurse = nurse;
	}

	public MedicalReport() {
		super();
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse newNurse) {
		if (this.nurse == null || !this.nurse.equals(newNurse)) {
			if (this.nurse != null) {
				Nurse oldNurse = this.nurse;
				this.nurse = null;
				oldNurse.removeMedicalReport(this);
			}
			if (newNurse != null) {
				this.nurse = newNurse;
				this.nurse.addMedicalReport(this);
			}
		}
	}

}