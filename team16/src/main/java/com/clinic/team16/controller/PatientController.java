package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.clinic.team16.beans.Allergies;
import com.clinic.team16.beans.ClinicalCenterAdministrator;
import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.PatientPasswordDTO;
import com.clinic.team16.beans.DTO.PatientInfoDTO;
import com.clinic.team16.beans.DTO.PatientMedicalRecordDTO;
import com.clinic.team16.beans.DTO.UserDTO;
import com.clinic.team16.service.ClinicalCenterAdminService;
import com.clinic.team16.service.MedicalRecordService;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.RegistrationRequestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@RestController
@RequestMapping("/patientApi")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private RegistrationRequestService registrationRequestService;

	@Autowired
	private ClinicalCenterAdminService clinicalCenterAdminService;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<PatientInfoDTO>> findAll() {
		List<Patient> list = this.patientService.findAll();
		List<PatientInfoDTO> dtoList = new ArrayList<PatientInfoDTO>();

		if (list != null) {
			for (Patient p : list) {
				if (p.getMedicalRecord() != null) {
					String name = p.getFirstName() + " " + p.getLastName();
					dtoList.add(new PatientInfoDTO(name, p.getEmail(), p.getCity(), p.getCountry(), p.getAddress(),
							p.getPhoneNumber(), p.getInsuranceNumber(), p.getMedicalRecord().getMedicalRecordId()));
				}
			}
			return new ResponseEntity<List<PatientInfoDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<Patient> findOneByEmail() {

		Patient found = this.patientService.findOneByEmail("p@p");
		System.out.println("PROSLO");
		if (found != null)
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findPatientsPassword")
	public ResponseEntity<String> findPatientsPassword() {

		Patient found = this.patientService.findOneByEmail("p@p");
		 
		if (found != null) {
			String p = found.getPassword();
			return new ResponseEntity<String>(p, HttpStatus.OK);

		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PostMapping(path = "/findModalByEmail", consumes = "application/json")
	public ResponseEntity<UserDTO> findModalByEmail(@RequestBody User u) {

		Patient found = this.patientService.findOneByEmail(u.getEmail());

		if (found != null) {
			String name = found.getFirstName() + " " + found.getLastName();
			UserDTO daoPatient = new UserDTO(name, found.getEmail(), found.getCity(), found.getCountry(),
					found.getAddress(), found.getPhoneNumber(), found.getInsuranceNumber());
			return new ResponseEntity<UserDTO>(daoPatient, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PutMapping(path = "/updatePatient", consumes = "application/json")
	public ResponseEntity<Patient> updatePatient(@RequestBody User p) {
		
		Patient found = this.patientService.findOneByEmail("p@p");

		if (found != null) {
			found.setFirstName(p.getFirstName());
			found.setLastName(p.getLastName());
			found.setAddress(p.getAddress());
			found.setCity(p.getCity());
			found.setCountry(p.getCountry());
			found.setPhoneNumber(p.getPhoneNumber());
			final Patient updatedPatient = patientService.save(found);
		    return ResponseEntity.ok(updatedPatient);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PutMapping(path = "/changePassword", consumes = "application/json")
	public ResponseEntity<Patient> changePassword(@RequestBody String p) {
		
		Patient found = this.patientService.findOneByEmail("p@p");

		if (found != null) {
			
			found.setPassword(p.substring(1, p.length()-1));
			final Patient updatedPatient = patientService.save(found);
		    return ResponseEntity.ok(updatedPatient);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/signUpUser", consumes = "application/json")
	public ResponseEntity<HttpStatus> signUpUser(@RequestBody User u) {
		System.out.println("UDJE");
		Patient p = new Patient(u.getEmail(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getAddress(),
				u.getCity(), u.getCountry(), u.getPhoneNumber(), u.getInsuranceNumber(), Role.PATIENT);
		p.setMedicalRecord(null);

		Patient same = this.patientService.findOneByEmail(u.getEmail());

		if (same != null) {
			System.out.println("IMA");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Patient ok = this.patientService.save(p);

		if (ok != null) {
			ClinicalCenterAdministrator a = this.clinicalCenterAdminService.finOneById(1);
			RegistrationRequest r = new RegistrationRequest(false, p, a);
			a.addRegistrationRequest(r);

			this.clinicalCenterAdminService.save(a);
			this.registrationRequestService.save(r);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			System.out.println("Nece da ga snimi");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/activateAccount")
	public RedirectView activateAccount(@RequestParam String email, RedirectAttributes attributes) {
		Patient p = this.patientService.findOneByEmail(email);

		if (p != null) {
			if (p.getMedicalRecord() == null) {
				MedicalRecord m = new MedicalRecord("", null, 0, 0, "", 0, null, null, "", p);
				this.medicalRecordService.save(m);

				p.setMedicalRecord(m);
				this.patientService.save(p);
			}

			attributes.addFlashAttribute("flashAttribute", "activateAccount");
			attributes.addAttribute("attribute", "activateAccount");
			return new RedirectView("/");

		} else {
			return new RedirectView("/");
		}

	}
	@GetMapping(path = "/medicalRecord")
	public ResponseEntity<PatientMedicalRecordDTO> medicalRecord() {

		Patient found = this.patientService.findOneByEmail("p@p");

		if (found != null) {
			String name = found.getFirstName() + " " + found.getLastName();
			
			PatientMedicalRecordDTO record = new PatientMedicalRecordDTO(name,found.getMedicalRecord());
			 
			return new ResponseEntity<PatientMedicalRecordDTO>(record, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
	}
	

}
