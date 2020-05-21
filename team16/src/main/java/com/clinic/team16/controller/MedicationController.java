package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.MedicationService;

@RestController
@RequestMapping("/medicationApi")
public class MedicationController {

	@Autowired
	MedicationService medicationService;

	@Autowired
	ClinicalCenterService clinicalCenterService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Medication>> findAll() {
		List<Medication> list = this.medicationService.findAll();

		if (list != null)
			return new ResponseEntity<List<Medication>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/addMedication", consumes = "application/json")
	public ResponseEntity<HttpStatus> addMedication(@RequestBody Medication med) {
		Medication f = this.medicationService.findOneByCode(med.getCode());

		if (f == null) {

			ClinicalCenter c = this.clinicalCenterService.findOneById(1);

			med.setClinicalCenter(c);
			Medication k = this.medicationService.save(med);

			if (k != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/deleteMedication/{code}", consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("code") String code) {
		Medication med = this.medicationService.findOneByCode(code);

		if (med != null) {
			med.setClinicalCenter(null);
			this.medicationService.delete(med);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
