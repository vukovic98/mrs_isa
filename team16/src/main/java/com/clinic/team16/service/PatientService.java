package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository; 
	
	
	public Patient save(Patient p) {
		return this.patientRepository.save(p);
	}
	
	public Patient findOneByEmail(String email) {
		return this.patientRepository.findOneByEmail(email);
	}

}
