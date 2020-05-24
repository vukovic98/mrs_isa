package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.ClinicAdminInfoDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicAdminService;

@RestController
@RequestMapping("/clinicAdminApi")
public class ClinicAdminController {

	@Autowired
	private ClinicAdminService clinicAdminService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<ClinicAdminInfoDTO>> findAll() {
		List<ClinicAdministrator> list = this.clinicAdminService.findAll();
		List<ClinicAdminInfoDTO> daoList = new ArrayList<ClinicAdminInfoDTO>();
		
		
		if(list != null) {
			for(ClinicAdministrator c : list) {
				String name = c.getFirstName() + " " + c.getLastName();
				daoList.add(new ClinicAdminInfoDTO(name, c.getEmail(), c.getCity(), c.getCountry(), c.getClinic().getName()));
			}
			return new ResponseEntity<List<ClinicAdminInfoDTO>>(daoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<ClinicAdministrator> findOneByEmail() {
		
		ClinicAdministrator found = this.clinicAdminService.findOneByEmail("s@s");
		System.out.println("PROSLO");
		if(found != null) 
			return new ResponseEntity<ClinicAdministrator>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/updateAdmin", consumes = "application/json")
	public ResponseEntity<ClinicAdministrator> updatePatient(@RequestBody User p) {
		
		ClinicAdministrator found = this.clinicAdminService.findOneByEmail("s@s");

		if (found != null) {
			found.setFirstName(p.getFirstName());
			found.setLastName(p.getLastName());
			found.setAddress(p.getAddress());
			found.setCity(p.getCity());
			found.setCountry(p.getCountry());
			found.setPhoneNumber(p.getPhoneNumber());
			final ClinicAdministrator updatedAdmin = clinicAdminService.save(found);
		    return ResponseEntity.ok(updatedAdmin);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping(path = "/findPassword")
	public ResponseEntity<String> findPassword() {

		ClinicAdministrator found = this.clinicAdminService.findOneByEmail("s@s");
		 
		if (found != null) {
			String p = found.getPassword();
			return new ResponseEntity<String>(p, HttpStatus.OK);

		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/changePassword", consumes = "application/json")
	public ResponseEntity<ClinicAdministrator> changePassword(@RequestBody String p) {
		
		ClinicAdministrator found = this.clinicAdminService.findOneByEmail("s@s");

		if (found != null) {
			
			found.setPassword(p.substring(1, p.length()-1));
			final ClinicAdministrator updatedAdmin = clinicAdminService.save(found);
		    return ResponseEntity.ok(updatedAdmin);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
