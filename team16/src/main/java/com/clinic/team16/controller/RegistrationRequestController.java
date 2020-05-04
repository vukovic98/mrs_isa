package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.beans.Role;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.RegistrationRequestService;

@RestController
@RequestMapping("/registrationRequestApi")
public class RegistrationRequestController {

	@Autowired
	private RegistrationRequestService registrationRequestService;
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping(path = "/addReq")
	public void add() {
		Patient p = new Patient("o@o", "123", "Pera", "Peric", "BB", "Beograd", "Srbija", "066545454", "12345678", Role.PATIENT);
		RegistrationRequest r = new RegistrationRequest(false, p, null);
		
		
		this.patientService.save(p);
		this.registrationRequestService.save(r);
		
		Patient k = new Patient("g@g", "123", "Pera", "Miric", "BB", "Beograd", "Srbija", "066545454", "12345678", Role.PATIENT);
		RegistrationRequest s = new RegistrationRequest(false, k, null);
		
		this.patientService.save(k);
		this.registrationRequestService.save(s);
	}
	
	@GetMapping(path = "/findAll", produces = "application/json")
	public ResponseEntity<List<RegistrationRequest>> getAllRegistrationRequests() {
		List<RegistrationRequest> list = this.registrationRequestService.findAll();
		
		if(!list.isEmpty()) 
			return new ResponseEntity<>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); //Status 204
	}
}
