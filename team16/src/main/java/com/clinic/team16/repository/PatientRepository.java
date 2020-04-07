package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Patient;

@Repository
public class PatientRepository {
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	
	public Patient create(Patient patient) {
		patients.add(patient);
		return patient;
	}
}
