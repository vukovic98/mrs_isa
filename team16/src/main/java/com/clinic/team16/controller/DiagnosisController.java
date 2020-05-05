package com.clinic.team16.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.service.DiagnosisService;

@RestController
@RequestMapping("/diagnosisApi")
public class DiagnosisController {
	
	@Autowired
	DiagnosisService diagnosisService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Diagnosis>> findAll() {
		List<Diagnosis> list = this.diagnosisService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Diagnosis>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
