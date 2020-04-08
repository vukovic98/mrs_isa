package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/patientApi")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws Exception {
		
		Patient newPatient = patientService.create(patient);
		
		return new ResponseEntity<Patient>(newPatient, HttpStatus.CREATED);
		
		 
	}

}
