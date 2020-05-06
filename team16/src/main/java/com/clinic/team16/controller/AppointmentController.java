package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.service.AppointmentService;

@RestController
@RequestMapping("/appointmentApi")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<Appointment>> findAll() {
		List<Appointment> list = this.appointmentService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Appointment>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
