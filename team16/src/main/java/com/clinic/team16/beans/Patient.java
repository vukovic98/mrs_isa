package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
@Embeddable
public class Patient extends User {

	@ElementCollection
	@CollectionTable(name = "patient_appointments", joinColumns = @JoinColumn(name = "patient_id"))
	public List<Appointment> appointments;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "MedicalRecord_ID")
	public MedicalRecord medicalRecord;

	public Patient() {
		super();
	}
	
	

	public Patient(String email, String password, String firstName, String lastName, String address, String city,
			String country, String phoneNumber, String insuranceNumber, Role role) {
		this.setEmail(email);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setCity(city);
		this.setCountry(country);
		this.setPhoneNumber(phoneNumber);
		this.setInsuranceNumber(insuranceNumber);
		this.setRole(role);
		this.appointments = null;
		this.medicalRecord = null;
		
	}


	public Patient(ArrayList<Appointment> appointments, MedicalRecord medicalRecord) {
		super();
		this.appointments = appointments;
		this.medicalRecord = medicalRecord;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public List<Appointment> getAppointments() {
		if (appointments == null)
			appointments = new ArrayList<Appointment>();
		return this.appointments;
	}

	public java.util.Iterator getIteratorAppointment() {
		if (appointments == null)
			appointments = new ArrayList<Appointment>();
		return appointments.iterator();
	}

	public void setAppointment(ArrayList<Appointment> newAppointment) {
		removeAllAppointment();
		for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
			addAppointment((Appointment) iter.next());
	}

	public void addAppointment(Appointment newAppointment) {
		if (newAppointment == null)
			return;
		if (this.appointments == null)
			this.appointments = new ArrayList<Appointment>();
		if (!this.appointments.contains(newAppointment)) {
			this.appointments.add(newAppointment);

		}
	}

	public void removeAppointment(Appointment oldAppointment) {
		if (oldAppointment == null)
			return;
		if (this.appointments != null)
			if (this.appointments.contains(oldAppointment)) {
				this.appointments.remove(oldAppointment);

			}
	}

	public void removeAllAppointment() {
		if (appointments != null) {
			Appointment oldAppointment;
			for (java.util.Iterator iter = getIteratorAppointment(); iter.hasNext();) {
				oldAppointment = (Appointment) iter.next();
				iter.remove();
			}
		}
	}
}