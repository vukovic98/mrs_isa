package com.clinic.team16.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.Allergies;
import com.clinic.team16.service.MedicalRecordService;
import com.clinic.team16.service.PatientService;

@RestController
@RequestMapping("/medicalRecordApi")
public class MedicalRecordController {
	@Autowired
	MedicalRecordService medicalRecordService;
	@Autowired
	PatientService patientService;
	
	
	@GetMapping(path = "/add")
	public String addMedicalRecord(){
		Patient p = patientService.findOneByEmail("p@p");
		java.util.List<Allergies> allergies = new java.util.ArrayList();
	    java.util.List<Medication> perscriptions = new ArrayList<>();
	    Date d = new Date(1234567833);
		MedicalRecord medicalRecord = new MedicalRecord((long)1, "female",d, 169, 59, "B", 111, allergies, perscriptions, "istorija", p);
		p.setMedicalRecord(medicalRecord);
		medicalRecordService.save(medicalRecord);
	
		
		return "SAVED";
	}
}
