package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Patient;

@Repository
public class PatientRepository {
	
	private final ArrayList<Patient> patients = new ArrayList<Patient>();
	
	public Patient create(Patient patient) {
		
		this.patients.add(patient);
		
		return patient;
	}
}
