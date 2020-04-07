package com.clinic.team16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Doctor;
import com.clinic.team16.service.DoctorService;

@RestController
@RequestMapping("/doctorApi")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) throws Exception{
		System.out.println("Pozvana metoda kontrolera za dodavanje doktora.");
		Doctor newDoctor = doctorService.create(doctor);
		return new ResponseEntity<Doctor>(newDoctor,HttpStatus.CREATED);
	}
}
