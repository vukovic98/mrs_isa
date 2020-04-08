package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository; 
	
	public Patient create(Patient patient) throws Exception{
		
		Patient savedPatient = patientRepository.create(patient);
		
		return savedPatient;
	}

}
