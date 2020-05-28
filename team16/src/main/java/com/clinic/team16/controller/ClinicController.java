package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.DTO.ClinicInfoDTO;
import com.clinic.team16.beans.DTO.RateDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.GradeService;
import com.clinic.team16.service.PatientService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ClinicInfoDTO>> findAll() {
		List<Clinic> list = this.clinicService.findAll();
		List<ClinicInfoDTO> daoList = new ArrayList<ClinicInfoDTO>();
		
		if(list != null) {
			for(Clinic c : list) {
				daoList.add(new ClinicInfoDTO(c.getClinicID() ,c.getName(), c.getAddress(), c.getDescription(),c.getAverageGrade()));
			}
			return new ResponseEntity<List<ClinicInfoDTO>>(daoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path="/rateClinic/{clinicID}&{grade}", consumes = "application/json")
	public ResponseEntity<HttpStatus> rateClinic(@PathVariable("clinicID") long clinicID, @PathVariable("grade") String grade) {
	
		long id = clinicID;
		Clinic c = this.clinicService.findOneByClinicID(id);
		Patient p = this.patientService.findOneByEmail("p@p");
		if(c!= null && p != null) {
			Grade g = new Grade(Integer.parseInt(grade), p);
			c.addGrade(g);
			gradeService.save(g);
			clinicService.save(c);
			patientService.save(p);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			System.out.println("LOSINAAAAAA");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
