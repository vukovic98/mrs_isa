package com.clinic.team16.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.DTO.DoctorDTO;
import com.clinic.team16.beans.DTO.DoctorLeaveDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicAdminService;
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.GradeService;
import com.clinic.team16.service.PatientService;

@RestController
@RequestMapping("/doctorApi")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@Autowired
	PatientService patientService;

	@Autowired
	GradeService gradeService;

	@Autowired
	ClinicAdminService adminService;

	@Autowired
	ClinicService clinicService;

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) throws Exception {
		System.out.println("Pozvana metoda kontrolera za dodavanje doktora.");
		Doctor newDoctor = doctorService.create(doctor);
		return new ResponseEntity<Doctor>(newDoctor, HttpStatus.CREATED);
	}

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Doctor>> findAll() {
		List<Doctor> list = this.doctorService.findAll();

		if (list != null)
			return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findAllDoctorsDTOByClinic/{clinicID}")
	public ResponseEntity<ArrayList<DoctorDTO>> findAllDoctorsDTO(@PathVariable long clinicID) {
		List<Doctor> list = this.doctorService.findAllByClinic(clinicID);

		if (list != null) {
			ArrayList<DoctorDTO> dtoList = new ArrayList<>();

			for (Doctor d : list)
				dtoList.add(
						new DoctorDTO(d.getId(), d.getFirstName(), d.getLastName(), d.getAverageGrade(), d.getEmail()));

			return new ResponseEntity<ArrayList<DoctorDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findAllDoctorsDTOCurrentClinic")
	public ResponseEntity<ArrayList<DoctorDTO>> findAllDoctorsDTOCurrentClinic() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		List<Doctor> list = this.doctorService.findAllByClinic(ca.getClinic().getClinicID());

		if (list != null) {
			ArrayList<DoctorDTO> dtoList = new ArrayList<>();

			for (Doctor d : list)
				dtoList.add(
						new DoctorDTO(d.getId(), d.getFirstName(), d.getLastName(), d.getAverageGrade(), d.getEmail()));

			return new ResponseEntity<ArrayList<DoctorDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findOneByEmail")
	public ResponseEntity<Doctor> findOneByEmail(@RequestParam String email) {

		Doctor found = this.doctorService.findOneByEmail(email);
		System.out.println("PROSLO");
		if (found != null)
			return new ResponseEntity<Doctor>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/rateDoctor/{doctorID}&{grade}", consumes = "application/json")
	public ResponseEntity<HttpStatus> rateDoctor(@PathVariable("doctorID") long doctorID,
			@PathVariable("grade") String grade) {
		long id = doctorID;
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor found = this.doctorService.findOneByDoctorID(id);
		Patient p = this.patientService.findOneByEmail(currentUser);
		if (found != null && p != null) {
			Grade g = found.addGrade(p, Integer.parseInt(grade));
			gradeService.save(g);
			doctorService.save(found);
			patientService.save(p);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/findAllForClinic/{clinicID}")
	public ResponseEntity<List<Doctor>> findAllForClinic(@PathVariable("clinicID") long clinicID) {
		List<Doctor> list = this.doctorService.findAll();

		if (list != null)
			return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findCurrent")
	public ResponseEntity<DoctorLeaveDTO> findCurrent() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor found = this.doctorService.findOneByEmail(currentUser);
		System.out.println("PROSLO");
		if (found != null) {
			return new ResponseEntity<DoctorLeaveDTO>(
					new DoctorLeaveDTO(found.getFirstName() + " " + found.getLastName(), found.getEmail(),
							found.getCity(), found.getCountry(), found.getAddress(), found.getPhoneNumber(),
							found.getClinic().getName()),
					HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/getFreeTermsForCurrent/{date}")
	public ResponseEntity<ArrayList<String>> getFreeTermsForCurrent(@PathVariable String date) {
		ArrayList<String> terms = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		Doctor d = this.doctorService.findOneByEmail(currentUser);

		ArrayList<Appointment> apps = this.appointmentService.findByDoctorAndDate(date, d.getId());
		System.out.println(apps.size());
		if (apps.size() < 15) {

			// slobdni termini
			ArrayList<String> possTerms = new ArrayList<>();

			possTerms.add("08:00");
			possTerms.add("08:30");
			possTerms.add("09:00");
			possTerms.add("09:30");
			possTerms.add("10:00");
			possTerms.add("10:30");
			possTerms.add("11:00");
			possTerms.add("11:30");
			possTerms.add("12:00");
			possTerms.add("12:30");
			possTerms.add("13:00");
			possTerms.add("14:00");
			possTerms.add("14:30");
			possTerms.add("15:00");
			possTerms.add("15:30");

			// 2020-06-18 06:05:00.000000
			for (Appointment a : apps) {
				String time = a.getDateTime().toString().substring(11, 16);
				System.out.println(time + "****************************");
				if (possTerms.contains(time)) {
					System.out.println("UDJE" + time);
					possTerms.remove(time);
				}
			}

			for (String p : possTerms) {
				String finalDate = date + " " + p;
				terms.add(finalDate);
			}

			return new ResponseEntity<ArrayList<String>>(terms, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
