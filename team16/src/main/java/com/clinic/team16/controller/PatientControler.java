package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Patient;

import org.springframework.http.*;
@RestController
@RequestMapping(value = "/patient")
public class PatientControler {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws Exception {
		Patient savedPatient = new Patient();
		return new ResponseEntity<Patient>(savedPatient, HttpStatus.OK);
		
		 
	}

}
