package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Ordination;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.OrdinationService;

@RestController
@RequestMapping("/ordinationApi")
public class OrdinationController {

	@Autowired
	private OrdinationService ordinationService;
	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<Ordination>> findAll() {
		List<Ordination> list = this.ordinationService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Ordination>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
