package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Doctor;

@Repository
public class DoctorRepository {
 
	private final ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	
	public Doctor create(Doctor doc) {
		System.out.println("Pozvana metoda repozitorijuma za dodavanje doktora.");
		this.doctors.add(doc);
		return doc;
	}
}
