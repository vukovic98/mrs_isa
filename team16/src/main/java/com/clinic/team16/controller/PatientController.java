package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.User;
import com.clinic.team16.service.PatientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/patientApi")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<Patient>> findAll() {
		List<Patient> list = this.patientService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<Patient> findOneByEmail() {
		
		Patient found = this.patientService.findOneByEmail("p@p");
		System.out.println("PROSLO");
		if(found != null) 
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/findModalByEmail", consumes = "application/json")
	public ResponseEntity<Patient> findModalByEmail(@RequestBody User u) {
		
		Patient found = this.patientService.findOneByEmail(u.getEmail());
		
		
		if(found != null) 
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
