package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;
@Entity
public class ClinicalCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Clinic_ID", nullable = false)
	private long id;

	@Column(name = "Name",nullable = false)
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_clinics",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public ArrayList<Clinic> clinic;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_medication",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public ArrayList<Medication> medication;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_diagnosis",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public ArrayList<Diagnosis> diagnosis;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_administrators",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public ArrayList<ClinicalCenterAdministrator> clinicalCenterAdministrator;

	public ClinicalCenter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ClinicalCenterAdministrator> getClinicalCenterAdministrator() {
		return clinicalCenterAdministrator;
	}

	public void setClinicalCenterAdministrator(ArrayList<ClinicalCenterAdministrator> clinicalCenterAdministrator) {
		this.clinicalCenterAdministrator = clinicalCenterAdministrator;
	}

	public ArrayList<Clinic> getClinic() {
		if (clinic == null)
			clinic = new ArrayList<Clinic>();
		return clinic;
	}

	public java.util.Iterator getIteratorClinic() {
		if (clinic == null)
			clinic = new ArrayList<Clinic>();
		return clinic.iterator();
	}

	public void setClinic(ArrayList<Clinic> newClinic) {
		removeAllClinic();
		for (java.util.Iterator iter = newClinic.iterator(); iter.hasNext();)
			addClinic((Clinic) iter.next());
	}

	public void addClinic(Clinic newClinic) {
		if (newClinic == null)
			return;
		if (this.clinic == null)
			this.clinic = new ArrayList<Clinic>();
		if (!this.clinic.contains(newClinic))
			this.clinic.add(newClinic);
	}

	public void removeClinic(Clinic oldClinic) {
		if (oldClinic == null)
			return;
		if (this.clinic != null)
			if (this.clinic.contains(oldClinic))
				this.clinic.remove(oldClinic);
	}

	public void removeAllClinic() {
		if (clinic != null)
			clinic.clear();
	}

	public ArrayList<Medication> getMedication() {
		if (medication == null)
			medication = new ArrayList<Medication>();
		return medication;
	}

	public java.util.Iterator getIteratorMedication() {
		if (medication == null)
			medication = new ArrayList<Medication>();
		return medication.iterator();
	}

	public void setMedication(ArrayList<Medication> newMedication) {
		removeAllMedication();
		for (java.util.Iterator iter = newMedication.iterator(); iter.hasNext();)
			addMedication((Medication) iter.next());
	}

	public void addMedication(Medication newMedication) {
		if (newMedication == null)
			return;
		if (this.medication == null)
			this.medication = new ArrayList<Medication>();
		if (!this.medication.contains(newMedication)) {
			this.medication.add(newMedication);
			newMedication.setClinicalCenter(this);
		}
	}

	public void removeMedication(Medication oldMedication) {
		if (oldMedication == null)
			return;
		if (this.medication != null)
			if (this.medication.contains(oldMedication)) {
				this.medication.remove(oldMedication);
				oldMedication.setClinicalCenter((ClinicalCenter) null);
			}
	}

	public void removeAllMedication() {
		if (medication != null) {
			Medication oldMedication;
			for (java.util.Iterator iter = getIteratorMedication(); iter.hasNext();) {
				oldMedication = (Medication) iter.next();
				iter.remove();
				oldMedication.setClinicalCenter((ClinicalCenter) null);
			}
		}
	}

	public ArrayList<Diagnosis> getDiagnosis() {
		if (diagnosis == null)
			diagnosis = new ArrayList<Diagnosis>();
		return diagnosis;
	}

	public java.util.Iterator getIteratorDiagnosis() {
		if (diagnosis == null)
			diagnosis = new ArrayList<Diagnosis>();
		return diagnosis.iterator();
	}

	public void setDiagnosis(ArrayList<Diagnosis> newDiagnosis) {
		removeAllDiagnosis();
		for (java.util.Iterator iter = newDiagnosis.iterator(); iter.hasNext();)
			addDiagnosis((Diagnosis) iter.next());
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

	public void removeAllDiagnosis() {
		if (diagnosis != null) {
			Diagnosis oldDiagnosis;
			for (java.util.Iterator iter = getIteratorDiagnosis(); iter.hasNext();) {
				oldDiagnosis = (Diagnosis) iter.next();
				iter.remove();
				oldDiagnosis.setClinicalCenter((ClinicalCenter) null);
			}
		}
	}

}