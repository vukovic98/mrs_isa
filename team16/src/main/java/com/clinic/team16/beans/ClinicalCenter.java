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
	public List<Clinic> clinics;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_medication",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<Medication> medications;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_diagnosis",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<Diagnosis> diagnosis;
	
	@ElementCollection
	@CollectionTable(name = "clinicCenter_administrators",joinColumns = @JoinColumn(name = "clinicCenter_id"))
	public List<ClinicalCenterAdministrator> clinicalCenterAdministrators;

	public ClinicalCenter() {
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

	public java.util.Iterator getIteratorClinic() {
		if (clinics == null)
			clinics = new ArrayList<Clinic>();
		return clinics.iterator();
	}

	public void setClinic(ArrayList<Clinic> newClinic) {
		removeAllClinic();
		for (java.util.Iterator iter = newClinic.iterator(); iter.hasNext();)
			addClinic((Clinic) iter.next());
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

	public java.util.Iterator getIteratorMedication() {
		if (medications == null)
			medications = new ArrayList<Medication>();
		return medications.iterator();
	}

	public void setMedication(ArrayList<Medication> newMedication) {
		removeAllMedication();
		for (java.util.Iterator iter = newMedication.iterator(); iter.hasNext();)
			addMedication((Medication) iter.next());
	}

	public void addMedication(Medication newMedication) {
		if (newMedication == null)
			return;
		if (this.medications == null)
			this.medications = new ArrayList<Medication>();
		if (!this.medications.contains(newMedication)) {
			this.medications.add(newMedication);
			newMedication.setClinicalCenter(this);
		}
	}

	public void removeMedication(Medication oldMedication) {
		if (oldMedication == null)
			return;
		if (this.medications != null)
			if (this.medications.contains(oldMedication)) {
				this.medications.remove(oldMedication);
				oldMedication.setClinicalCenter((ClinicalCenter) null);
			}
	}

	public void removeAllMedication() {
		if (medications != null) {
			Medication oldMedication;
			for (java.util.Iterator iter = getIteratorMedication(); iter.hasNext();) {
				oldMedication = (Medication) iter.next();
				iter.remove();
				oldMedication.setClinicalCenter((ClinicalCenter) null);
			}
		}
	}

	public List<Diagnosis> getDiagnosis() {
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