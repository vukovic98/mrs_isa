package com.clinic.team16.controller;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Allergies;
import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.DiagnosisService;

@RestController
@RequestMapping("/diagnosisApi")
public class DiagnosisController {
	
	@Autowired
	DiagnosisService diagnosisService;
	
	@Autowired
	ClinicalCenterService clinicalCenterService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Diagnosis>> findAll() {
		List<Diagnosis> list = this.diagnosisService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Diagnosis>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/addDiagnosis", consumes = "application/json")
	public ResponseEntity<HttpStatus> addDiagnosis(@RequestBody Diagnosis d) {
		Diagnosis found = this.diagnosisService.findOneByCode(d.getCode());
		Diagnosis f2 = this.diagnosisService.findOneByName(d.getName());
		
		if(found == null && f2 == null) {
			ClinicalCenter c = this.clinicalCenterService.findOneById(1);
			d.setClinicalCenter(c);
			
			Diagnosis ok = this.diagnosisService.save(d);
			
			if(ok != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/findAllAllergies")
	public ResponseEntity<Allergies[]> findAllAlergies() {
		
		return new ResponseEntity<Allergies[]>(Allergies.values(), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/deleteDiagnosis/{code}")
	public ResponseEntity<HttpStatus> deleteDiagnosis(@PathVariable("code") String code) {
		Diagnosis d = this.diagnosisService.findOneByCode(code);
		
		if(d != null) {
			d.setClinicalCenter(null);
			
			this.diagnosisService.delete(d);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
