package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.User;
import com.clinic.team16.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/patientApi")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	
	@PostMapping(path = "/findOneByEmail")
	public ResponseEntity<Patient> findOneByEmail(@RequestBody Patient p) {
		System.out.println(p.getEmail());
		Patient found = this.patientService.findOneByEmail(p.getEmail());
		System.out.println("PROSLO");
		if(found != null) 
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
