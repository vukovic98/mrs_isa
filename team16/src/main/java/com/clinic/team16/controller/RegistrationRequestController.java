package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.RegistrationRequestDTO;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.RegistrationRequestService;

@RestController
@RequestMapping("/registrationRequestApi")
public class RegistrationRequestController {

	@Autowired
	private RegistrationRequestService registrationRequestService;

	@Autowired
	private PatientService patientService;

	@GetMapping(path = "/findAll", produces = "application/json")
	public ResponseEntity<List<RegistrationRequestDTO>> getAllRegistrationRequests() {
		List<RegistrationRequest> list = this.registrationRequestService.findAll();
		List<RegistrationRequestDTO> daoList = new ArrayList<RegistrationRequestDTO>();

		if (!list.isEmpty()) {
			for (RegistrationRequest r : list) {
				String name = r.getUser().getFirstName() + " " + r.getUser().getLastName();
				daoList.add(new RegistrationRequestDTO(r.isApproved(), name, r.getUser().getEmail()));
			}
			return new ResponseEntity<>(daoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status 204
	}

	@PostMapping(path = "/acceptUser")
	public ResponseEntity<HttpStatus> acceptUser(@RequestBody User u) throws MessagingException {
		String email = u.getEmail();

		Patient p = this.patientService.findOneByEmail(email);

		if (p != null) {
			RegistrationRequest r = this.registrationRequestService.findOneByUserId(p.getId());
			r.setApproved(true);

			this.registrationRequestService.save(r);

			this.registrationRequestService.sendAcceptedMail(p.getEmail());

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/declineUser")
	public ResponseEntity<HttpStatus> declineUser(@RequestBody User u) throws MessagingException {
		String email = u.getEmail();
		String reason = u.getAddress(); // smart work

		Patient p = this.patientService.findOneByEmail(email);

		if (p != null) {
			RegistrationRequest r = this.registrationRequestService.findOneByUserId(p.getId());
			r.setUser(null);
			r.setClinicalCenterAdministrator(null);
			this.registrationRequestService.save(r);

			boolean ok = this.registrationRequestService.delete(r);
			this.patientService.delete(p);
			if (ok) {
				this.registrationRequestService.sendDeclinedMail(reason);

				return new ResponseEntity<>(HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
