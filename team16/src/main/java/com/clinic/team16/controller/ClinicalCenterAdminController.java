package com.clinic.team16.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.beans.ClinicalCenterAdministrator;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.ClinicalCenterAdminInfoDTO;
import com.clinic.team16.service.ClinicalCenterAdminService;
import com.clinic.team16.service.ClinicalCenterService;

@RestController
@RequestMapping("/centerAdminApi")
public class ClinicalCenterAdminController {

	@Autowired
	private ClinicalCenterAdminService clinicalCenterAdminService;

	@Autowired
	private ClinicalCenterService clinicalCenterService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<ArrayList<ClinicalCenterAdminInfoDTO>> findAll() {

		ArrayList<ClinicalCenterAdministrator> admins = this.clinicalCenterAdminService.findAll();

		if (admins != null) {
			ArrayList<ClinicalCenterAdminInfoDTO> dtoAdmins = new ArrayList<>();
			for (ClinicalCenterAdministrator a : admins) {
				dtoAdmins.add(new ClinicalCenterAdminInfoDTO(a.getFirstName(), a.getLastName(), a.getAddress(),
						a.getPhoneNumber(), a.getCity(), a.getCountry(), a.getInsuranceNumber(), a.getEmail(),
						a.getPassword(), a.getClinicalCenter().getName()));
			}

			return new ResponseEntity<ArrayList<ClinicalCenterAdminInfoDTO>>(dtoAdmins, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = "/addAdmin", consumes = "application/json")
	public ResponseEntity<HttpStatus> addAdmin(@RequestBody ClinicalCenterAdminInfoDTO admin) {
		ClinicalCenterAdministrator found = this.clinicalCenterAdminService.findOneByEmail(admin.getEmail());

		if (found == null) {
			ClinicalCenter center = this.clinicalCenterService.findOneByName(admin.getClinicCenter());

			if (center != null) {
				ClinicalCenterAdministrator cA = new ClinicalCenterAdministrator(admin.getEmail(), admin.getPassword(),
						admin.getFirstName(), admin.getLastName(), admin.getAddress(), admin.getCity(),
						admin.getCountry(), admin.getPhoneNumber(), admin.getInsuranceNumber(), Role.USER, center, null,
						null);

				this.clinicalCenterAdminService.save(cA);

				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/changePassword", consumes = "application/json")
	public ResponseEntity<HttpStatus> changePassword(@RequestBody User u) {
		System.out.println(u.getPassword());

		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		System.out.println(currentUser);

		ClinicalCenterAdministrator cA = this.clinicalCenterAdminService.findOneByEmail(currentUser);

		if (cA != null) {
			cA.setPassword(u.getPassword());
			cA.setRole(Role.CLINICAL_CENTER_ADMINISTRATOR);
			this.clinicalCenterAdminService.save(cA);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
