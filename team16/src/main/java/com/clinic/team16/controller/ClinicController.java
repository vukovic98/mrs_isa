package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Clinic>> findAll() {
		List<Clinic> list = this.clinicService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Clinic>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
