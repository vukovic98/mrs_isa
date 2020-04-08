package com.clinic.team16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Nurse;
import com.clinic.team16.service.NurseService;

@RestController
@RequestMapping("/nurseApi")
public class NurseController {
	
	@Autowired
	private NurseService nurseService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
		
		Nurse savedNurse = nurseService.create(nurse);
		
		return new ResponseEntity<Nurse>(savedNurse, HttpStatus.OK);
	}
}
