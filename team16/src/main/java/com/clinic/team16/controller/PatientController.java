package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.PatientInfoDTO;
import com.clinic.team16.beans.DTO.UserDTO;
import com.clinic.team16.service.PatientService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/patientApi")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<PatientInfoDTO>> findAll() {
		List<Patient> list = this.patientService.findAll();
		List<PatientInfoDTO> dtoList = new ArrayList<PatientInfoDTO>();

		if (list != null) {
			for (Patient p : list) {
				if (p.getMedicalRecord() != null) {
					String name = p.getFirstName() + " " + p.getLastName();
					dtoList.add(new PatientInfoDTO(name, p.getEmail(), p.getCity(), p.getCountry(), p.getAddress(),
							p.getPhoneNumber(), p.getInsuranceNumber(), p.getMedicalRecord().getMedicalRecordId()));
				}
			}
			return new ResponseEntity<List<PatientInfoDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<Patient> findOneByEmail() {

		Patient found = this.patientService.findOneByEmail("p@p");
		System.out.println("PROSLO");
		if (found != null)
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/findModalByEmail", consumes = "application/json")
	public ResponseEntity<UserDTO> findModalByEmail(@RequestBody User u) {

		Patient found = this.patientService.findOneByEmail(u.getEmail());

		if (found != null) {
			String name = found.getFirstName() + " " + found.getLastName();
			UserDTO daoPatient = new UserDTO(name, found.getEmail(), found.getCity(), found.getCountry(),
					found.getAddress(), found.getPhoneNumber(), found.getInsuranceNumber());
			return new ResponseEntity<UserDTO>(daoPatient, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
