package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Patient extends User {

	@ElementCollection
	@CollectionTable(name = "patient_appointments", joinColumns = @JoinColumn(name = "patient_id"))
	public List<Appointment> appointments;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
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
		this.appointments = new ArrayList<Appointment>();
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

	public void addAppointment(Appointment newAppointment) {

		this.appointments.add(newAppointment);

	}

	public void removeAppointment(Appointment oldAppointment) {
		if (oldAppointment == null)
			return;
		if (this.appointments != null)
			if (this.appointments.contains(oldAppointment)) {
				this.appointments.remove(oldAppointment);

			}
	}

}