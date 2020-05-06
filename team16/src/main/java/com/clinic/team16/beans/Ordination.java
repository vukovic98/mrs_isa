
/***********************************************************************
 * Module:  Ordination.java
 * Author:  Vladimir
 * Purpose: Defines the Class Ordination
 ***********************************************************************/
package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class Ordination {

	@Id
	@Column(name = "Ordination_Number", nullable = false)
	private int number;

	@Enumerated(EnumType.STRING)
	private OrdinationType type;

	@Column(name = "Name", nullable = false)
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Clinic_ID")
	public Clinic clinic;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "ordination_appointments", joinColumns = @JoinColumn(name = "ordination_id"))
	public List<Appointment> appointments;

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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic newClinic) {

		this.clinic = newClinic;

	}

}