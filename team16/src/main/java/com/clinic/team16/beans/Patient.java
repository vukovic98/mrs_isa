
/***********************************************************************
 * Module:  Pacient.java
 * Author:  Vladimir
 * Purpose: Defines the Class Pacient
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

public class Patient extends User {

	public ArrayList<Appointment> appointments;

	public MedicalRecord medicalRecord;

	public Patient() {
		super();
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

	public ArrayList<Appointment> getAppointment() {
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