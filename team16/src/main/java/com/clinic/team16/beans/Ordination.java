
/***********************************************************************
 * Module:  Ordination.java
 * Author:  Vladimir
 * Purpose: Defines the Class Ordination
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
public class Ordination {

	@Id
	private int number;

	private OrdinationType type;

	private String name;

	public Clinic clinic;

	@ElementCollection
	@CollectionTable(name = "ordination_appointments",joinColumns = @JoinColumn(name = "ordination_id"))
	public ArrayList<Appointment> appointments;

	public Ordination() {
		super();
	}

	public Ordination(int number, OrdinationType type, String name, Clinic clinic,
			ArrayList<Appointment> appointments) {
		super();
		this.number = number;
		this.type = type;
		this.name = name;
		this.clinic = clinic;
		this.appointments = appointments;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public OrdinationType getType() {
		return type;
	}

	public void setType(OrdinationType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic newClinic) {
		if (this.clinic == null || !this.clinic.equals(newClinic)) {
			if (this.clinic != null) {
				Clinic oldClinic = this.clinic;
				this.clinic = null;
				oldClinic.removeOrdination(this);
			}
			if (newClinic != null) {
				this.clinic = newClinic;
				this.clinic.addOrdination(this);
			}
		}
	}

}