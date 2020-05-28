package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClinicalCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Clinic_ID", nullable = false)
	private long id;

	@Column(name = "Name", nullable = false)
	private String name;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenter_clinics", joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<Clinic> clinics;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenter_medication", joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<Medication> medications;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenter_diagnosis", joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<Diagnosis> diagnosis;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicCenter_administrators", joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<ClinicalCenterAdministrator> clinicalCenterAdministrators;

	public ClinicalCenter() {
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

	public List<ClinicalCenterAdministrator> getClinicalCenterAdministrators() {
		return clinicalCenterAdministrators;
	}

	public void setClinicalCenterAdministrator(ArrayList<ClinicalCenterAdministrator> clinicalCenterAdministrator) {
		this.clinicalCenterAdministrators = clinicalCenterAdministrator;
	}

	public List<Clinic> getClinic() {
		if (clinics == null)
			clinics = new ArrayList<Clinic>();
		return clinics;
	}

	public void addClinic(Clinic newClinic) {
		if (newClinic == null)
			return;
		if (this.clinics == null)
			this.clinics = new ArrayList<Clinic>();
		if (!this.clinics.contains(newClinic))
			this.clinics.add(newClinic);
	}

	public void removeClinic(Clinic oldClinic) {
		if (oldClinic == null)
			return;
		if (this.clinics != null)
			if (this.clinics.contains(oldClinic))
				this.clinics.remove(oldClinic);
	}

	public void removeAllClinic() {
		if (clinics != null)
			clinics.clear();
	}

	public List<Medication> getMedication() {
		if (medications == null)
			medications = new ArrayList<Medication>();
		return medications;
	}

	public void addMedication(Medication newMedication) {

		this.medications.add(newMedication);

	}

	public void removeMedication(Medication oldMedication) {

		this.medications.remove(oldMedication);

	}

	public List<Diagnosis> getDiagnosis() {
		if (diagnosis == null)
			diagnosis = new ArrayList<Diagnosis>();
		return diagnosis;
	}

	public void addDiagnosis(Diagnosis newDiagnosis) {
		if (newDiagnosis == null)
			return;
		if (this.diagnosis == null)
			this.diagnosis = new ArrayList<Diagnosis>();
		if (!this.diagnosis.contains(newDiagnosis)) {
			this.diagnosis.add(newDiagnosis);
			newDiagnosis.setClinicalCenter(this);
		}
	}

	public void removeDiagnosis(Diagnosis oldDiagnosis) {
		if (oldDiagnosis == null)
			return;
		if (this.diagnosis != null)
			if (this.diagnosis.contains(oldDiagnosis)) {
				this.diagnosis.remove(oldDiagnosis);
				oldDiagnosis.setClinicalCenter((ClinicalCenter) null);
			}
	}

}