package com.clinic.team16.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Medication;
import com.clinic.team16.service.MedicationService;

@RestController
@RequestMapping("/medicationApi")
public class MedicationController {
	
	@Autowired
	MedicationService medicationService;
	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<Medication>> findAll() {
		List<Medication> list = this.medicationService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Medication>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
