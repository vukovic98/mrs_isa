package com.clinic.team16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.clinic.team16.beans.Allergies;
import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.ClinicalCenterAdministrator;
import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.AllergiestEditDTO;
import com.clinic.team16.beans.DTO.AppointmentHistoryDTO;
import com.clinic.team16.beans.DTO.ClinicAdminFullInfo;
import com.clinic.team16.beans.DTO.MedicationsEditDTO;
import com.clinic.team16.beans.DTO.PatientDTO;
import com.clinic.team16.beans.DTO.PatientDoctorDTO;
import com.clinic.team16.beans.DTO.PatientInfoDTO;
import com.clinic.team16.beans.DTO.PatientMedicalRecordDTO;
import com.clinic.team16.beans.DTO.UserAuthDTO;
import com.clinic.team16.beans.DTO.UserDTO;
import com.clinic.team16.beans.DTO.UserFullInfoDTO;
import com.clinic.team16.service.ClinicalCenterAdminService;
import com.clinic.team16.service.MedicalRecordService;
import com.clinic.team16.service.MedicationService;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.RegistrationRequestService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;

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
	
	@Autowired
	private MedicationService medicationService;


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
	
	@GetMapping(path = "/findAllForList")
	public ResponseEntity<List<PatientDoctorDTO>> findAllForList() {
		List<Patient> list = this.patientService.findAll();
		List<PatientDoctorDTO> dtoList = new ArrayList<PatientDoctorDTO>();

		if (list != null) {
			for (Patient p : list) {
				if (p.getMedicalRecord() != null) {
					String name = p.getFirstName() + " " + p.getLastName();
					dtoList.add(new PatientDoctorDTO(p.getInsuranceNumber(), name, p.getPhoneNumber()));
				}
			}
			return new ResponseEntity<List<PatientDoctorDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<Patient> findOneByEmail() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);
		System.out.println("PROSLO");
		if (found != null)
			return new ResponseEntity<Patient>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findPatientsPassword")
	public ResponseEntity<String> findPatientsPassword() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			String p = found.getPassword();
			return new ResponseEntity<String>(p, HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/findModalByEmail", consumes = "application/json")
	public ResponseEntity<UserDTO> findModalByEmail(@RequestBody UserAuthDTO u) {

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
	public ResponseEntity<HttpStatus> updatePatient(@RequestBody UserFullInfoDTO p) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			found.setFirstName(p.getFirstName());
			found.setLastName(p.getLastName());
			found.setAddress(p.getAddress());
			found.setCity(p.getCity());
			found.setCountry(p.getCountry());
			found.setPhoneNumber(p.getPhoneNumber());
			Patient updatedPatient = patientService.save(found);

			if (updatedPatient != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/changePassword", consumes = "application/json")
	public ResponseEntity<HttpStatus> changePassword(@RequestBody String p) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {

			found.setPassword(p.substring(1, p.length() - 1));
			Patient updatedPatient = patientService.save(found);

			if (updatedPatient != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/signUpUser", consumes = "application/json")
	public ResponseEntity<HttpStatus> signUpUser(@RequestBody UserFullInfoDTO u) {
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
			ClinicalCenterAdministrator a = this.clinicalCenterAdminService.findMainClinicalCenterAdmin();
			RegistrationRequest r = new RegistrationRequest(false, p, a);
			a.addRegistrationRequest(r);

			this.registrationRequestService.save(r);
			this.clinicalCenterAdminService.save(a);

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

			return new RedirectView("/");

		} else {
			return new RedirectView("/");
		}

	}

	@GetMapping(path = "/medicalRecord")
	public ResponseEntity<PatientMedicalRecordDTO> medicalRecord() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			String name = found.getFirstName() + " " + found.getLastName();

			PatientMedicalRecordDTO record = new PatientMedicalRecordDTO(name, found.getMedicalRecord().getGender(),
					found.getMedicalRecord().getBirthday().toString(), found.getMedicalRecord().getHeight(),
					found.getMedicalRecord().getWeight(), found.getMedicalRecord().getBloodType(),
					found.getMedicalRecord().getAllergies(), found.getMedicalRecord().getPerscriptions());

			return new ResponseEntity<>(record, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(path = "/medicalRecordForPatient/{patientEmail}")
	public ResponseEntity<PatientMedicalRecordDTO> medicalRecordForPatient(@PathVariable String patientEmail) {
		Patient found = this.patientService.findOneByEmail(patientEmail);

		if (found != null) {
			String name = found.getFirstName() + " " + found.getLastName();

			PatientMedicalRecordDTO record = new PatientMedicalRecordDTO(name, found.getMedicalRecord().getGender(),
					found.getMedicalRecord().getBirthday().toString(), found.getMedicalRecord().getHeight(),
					found.getMedicalRecord().getWeight(), found.getMedicalRecord().getBloodType(),
					found.getMedicalRecord().getAllergies(), found.getMedicalRecord().getPerscriptions());

			return new ResponseEntity<>(record, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = "/patientInfo")
	public ResponseEntity<PatientDTO> patientInfo() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			PatientDTO patient = new PatientDTO();
			patient.setAddress(found.getAddress());
			patient.setCity(found.getCity());
			patient.setCountry(found.getCountry());
			patient.setEmail(found.getEmail());
			patient.setFirstName(found.getFirstName());
			patient.setLastName(found.getLastName());
			patient.setInsurance(found.getInsuranceNumber());
			patient.setPassword(found.getPassword());
			patient.setPhone(found.getPhoneNumber());

			return new ResponseEntity<>(patient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(path = "/appointmentHistory")
	public ResponseEntity<List<AppointmentHistoryDTO>> appointmentHistory() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			List<Appointment> apps = new ArrayList<>();
			apps = found.getAppointments();

			List<AppointmentHistoryDTO> appHistory = new ArrayList<AppointmentHistoryDTO>();
			if (apps != null && apps.size() > 0) {
				for (Appointment a : apps) {
					AppointmentHistoryDTO ah = new AppointmentHistoryDTO();
					ah.setAppointmentID(a.getAppointmentId());
					ah.setClinicID(a.getDoctor().getClinic().getClinicID());
					ah.setDoctorID(a.getDoctor().getId());
					ah.setDatetime(a.getDateTime().toString());
					ah.setAppointmentType(a.getPricelistItems().getName());
					ah.setClinic(a.getDoctor().getClinic().getName());
					ah.setDoctor(a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName());
					ah.setApproved(a.appointmentRequest.getApproved());
					appHistory.add(ah);
				}
			}
			return new ResponseEntity<>(appHistory, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@PutMapping(path = "/changeWeight/{patientEmail}&{weight}")
	public ResponseEntity<HttpStatus> changeWeight(@PathVariable String patientEmail, @PathVariable int weight) {
		Patient p = this.patientService.findOneByEmail(patientEmail);
		
		if(p != null) {
			p.getMedicalRecord().setWeight(weight);
			
			this.patientService.save(p);
			
			return new ResponseEntity<>(HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(path = "/changeHeight/{patientEmail}&{height}")
	public ResponseEntity<HttpStatus> changeHeight(@PathVariable String patientEmail, @PathVariable int height) {
		Patient p = this.patientService.findOneByEmail(patientEmail);
		
		if(p != null) {
			p.getMedicalRecord().setHeight(height);
			
			this.patientService.save(p);
			
			return new ResponseEntity<>(HttpStatus.OK); 
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(path = "/editMedicationInRecord/{patientEmail}", consumes = "application/json")
	public ResponseEntity<HttpStatus> addMedicationInRecord(@PathVariable String patientEmail, @RequestBody MedicationsEditDTO obj) {
		Patient p = this.patientService.findOneByEmail(patientEmail);
		
		if(p != null) {
			ArrayList<Medication> medsAdd = new ArrayList<>();
			ArrayList<Medication> medsRem = new ArrayList<>();
			
			for(String m : obj.getMedicationsRemove()) {
				medsRem.add(this.medicationService.findOneByName(m));
			}
			
			for(String m : obj.getMedicationsAdd()) {
				medsAdd.add(this.medicationService.findOneByName(m));
			}
			
			p.getMedicalRecord().getPerscriptions().removeAll(medsRem);
			p.getMedicalRecord().getPerscriptions().addAll(medsAdd);
			
			this.patientService.save(p);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping(path = "/editAllergiesInRecord/{patientEmail}", consumes = "application/json")
	public ResponseEntity<HttpStatus> addDiagnosisInRecord(@PathVariable String patientEmail, @RequestBody AllergiestEditDTO obj) {
		Patient p = this.patientService.findOneByEmail(patientEmail);
		
		if(p != null) {
			p.getMedicalRecord().getAllergies().removeAll(obj.getAllergiesRemove());
			p.getMedicalRecord().getAllergies().addAll(obj.getAllergiesAdd());
			
			this.patientService.save(p);
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = "/findModalByID", consumes = "application/json")
	public ResponseEntity<AppointmentHistoryDTO> findModalByID(@RequestBody Appointment a) {
		long id = a.getAppointmentId();
		AppointmentHistoryDTO app = new AppointmentHistoryDTO();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient found = this.patientService.findOneByEmail(currentUser);

		if (found != null) {
			if (found.getAppointments().size() > 0) {
				for (Appointment ap : found.getAppointments()) {
					if (ap.getAppointmentId() == id) {
						app.setClinicID(ap.getDoctor().getClinic().getClinicID());
						app.setDoctorID(ap.getDoctor().getId());
						app.setClinic(ap.getDoctor().getClinic().getName());
						app.setDoctor(ap.getDoctor().getFirstName() + " " + ap.getDoctor().getLastName());
					}
				}
			}
			return new ResponseEntity<>(app, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}

}
