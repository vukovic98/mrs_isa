package com.clinic.team16.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.beans.DTO.ClinicalCenterInfoDTO;
import com.clinic.team16.service.ClinicalCenterService;

@RestController
@RequestMapping("/clincalCenterApi")
public class ClinicalCenterController {

	@Autowired
	private ClinicalCenterService clinicalCenterService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<ArrayList<ClinicalCenterInfoDTO>> findAll() {
		ArrayList<ClinicalCenter> centers = this.clinicalCenterService.findAll();
		
		if(centers != null) {
			ArrayList<ClinicalCenterInfoDTO> dtoCenters = new ArrayList<>();
			
			for(ClinicalCenter c : centers) {
				dtoCenters.add(new ClinicalCenterInfoDTO(c.getId(), c.getName()));
			}
			
			return new ResponseEntity<ArrayList<ClinicalCenterInfoDTO>>(dtoCenters, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
