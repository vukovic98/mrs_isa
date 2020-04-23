package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
    
	@ElementCollection
	@CollectionTable(name = "medicalReport_diagnosis", joinColumns = @JoinColumn(name = "medicalReport_id"))
	public ArrayList<Diagnosis> diagnosis;
	
	@ElementCollection
	@CollectionTable(name = "medicalReport_medication", joinColumns = @JoinColumn(name = "medicalReport_id"))
	public ArrayList<Medication> medication;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name="MedicalReport_ID")
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

	public ArrayList<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(ArrayList<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public ArrayList<Medication> getMedication() {
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