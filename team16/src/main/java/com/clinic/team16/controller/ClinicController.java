package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Clinic;

import com.clinic.team16.beans.DTO.ClinicInfoDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ClinicInfoDTO>> findAll() {
		List<Clinic> list = this.clinicService.findAll();
		List<ClinicInfoDTO> daoList = new ArrayList<ClinicInfoDTO>();
		
		if(list != null) {
			for(Clinic c : list) {
				daoList.add(new ClinicInfoDTO(c.getClinicID() ,c.getName(), c.getAddress(), c.getDescription()));
			}
			return new ResponseEntity<List<ClinicInfoDTO>>(daoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
