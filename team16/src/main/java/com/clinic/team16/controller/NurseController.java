package com.clinic.team16.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Nurse;
import com.clinic.team16.beans.DTO.NurseDTO;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.NurseService;

@RestController
@RequestMapping("/nurseApi")
public class NurseController {
	
	@Autowired
	private NurseService nurseService;
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<NurseDTO> findOneByEmail() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Nurse n = this.nurseService.findOneByEmail(currentUser);
		
		if(n != null) {
			String name = n.getFirstName() + " " + n.getLastName();
			NurseDTO nDTO = new NurseDTO(name, n.getEmail(), n.getCity(), n.getCountry(),
					n.getAddress(), n.getPhoneNumber(), n.getClinic().getName());
			
			return new ResponseEntity<NurseDTO>(nDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path = "/findAllByClinicForCurrentDoctor")
	public ResponseEntity<ArrayList<NurseDTO>> findAllByClinic() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor d = this.doctorService.findOneByEmail(currentUser);
		
		if(d != null) {
			ArrayList<Nurse> list = this.nurseService.findAllByClinic(d.getClinic().getClinicID());
			
			if(list != null) {
				ArrayList<NurseDTO> dtoList = new ArrayList<>();
				
				for(Nurse n : list) {
					String name = n.getFirstName() + " " + n.getLastName();
					dtoList.add(new NurseDTO(name, n.getEmail(), n.getCity(), n.getCountry(), n.getAddress(),
							n.getPhoneNumber(), n.getClinic().getName()));
				}
				
				return new ResponseEntity<ArrayList<NurseDTO>>(dtoList, HttpStatus.OK);
			}else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
