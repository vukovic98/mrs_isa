package com.clinic.team16.controller;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.beans.MedicalReport;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Nurse;
import com.clinic.team16.beans.DTO.MedicalReportDTO;
import com.clinic.team16.service.DiagnosisService;
import com.clinic.team16.service.MedicalReportService;
import com.clinic.team16.service.MedicationService;
import com.clinic.team16.service.NurseService;

@RestController
@RequestMapping("/medicalReportApi")
public class MedicalReportController {
	@Autowired
	private MedicalReportService medicalReportService;
	
	@Autowired
	private NurseService nurseService;
	
	@Autowired
	private DiagnosisService diagnosisService;
	
	@Autowired
	private MedicationService medicationService;
	
	@PostMapping(path = "/addReport", consumes = "application/json")
	public ResponseEntity<HttpStatus> addReport(@RequestBody MedicalReportDTO report) {
		System.out.println(report.getId());
		MedicalReport m = this.medicalReportService.findOneById(report.getId());
		
		if(m != null) {
			m.setApproved(true);
			m.setDetails(report.getDetails());
			
			boolean ok = this.medicalReportService.save(m);
			
			if(ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
