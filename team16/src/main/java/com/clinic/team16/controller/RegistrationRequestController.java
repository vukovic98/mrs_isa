package com.clinic.team16.controller;

import java.util.ArrayList;
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
import com.clinic.team16.beans.DAO.RegistrationRequestDAO;
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
	
	@GetMapping(path = "/findAll", produces = "application/json")
	public ResponseEntity<List<RegistrationRequestDAO>> getAllRegistrationRequests() {
		List<RegistrationRequest> list = this.registrationRequestService.findAll();
		List<RegistrationRequestDAO> daoList = new ArrayList<RegistrationRequestDAO>();
		
		
		
		if(!list.isEmpty()) {
			for(RegistrationRequest r : list) {
				String name = r.getUser().getFirstName() + " " + r.getUser().getLastName();
				daoList.add(new RegistrationRequestDAO(r.isApproved(), name, r.getUser().getEmail()));
			}
			return new ResponseEntity<>(daoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); //Status 204
	}
}
