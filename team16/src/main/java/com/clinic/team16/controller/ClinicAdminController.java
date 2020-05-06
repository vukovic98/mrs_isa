package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicAdminService;

@RestController
@RequestMapping("/clinicAdminApi")
public class ClinicAdminController {

	@Autowired
	private ClinicAdminService clinicAdminService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<ClinicAdministrator>> findAll() {
		List<ClinicAdministrator> list = this.clinicAdminService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<ClinicAdministrator>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
