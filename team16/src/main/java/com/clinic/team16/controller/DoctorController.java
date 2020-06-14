package com.clinic.team16.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.ClinicAdminFullInfo;
import com.clinic.team16.beans.DTO.DoctorAddDTO;
import com.clinic.team16.beans.DTO.DoctorDTO;
import com.clinic.team16.beans.DTO.DoctorLeaveDTO;
import com.clinic.team16.beans.DTO.UserDTO;
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

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<Doctor>> findAll() {
		List<Doctor> list = this.doctorService.findAll();

		if (list != null)
			return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findCurrentDoc")
	public ResponseEntity<UserDTO> findOneByEmail() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor found = this.doctorService.findOneByEmail(currentUser);

		System.out.println("PROSLO");
		if (found != null)
			return new ResponseEntity<UserDTO>(new UserDTO(found.getEmail(), found.getCity(), found.getCountry(),
					found.getAddress(), found.getPhoneNumber(), found.getInsuranceNumber(), found.getFirstName(),
					found.getLastName()), HttpStatus.OK);
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
						new DoctorDTO(d.getId(), d.getFirstName(), d.getLastName(), d.getAverageGrade(), d.getEmail(),d.getSpecialty()));

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

	@GetMapping(path = "/findAllFreeForDateTime", params = { "date", "type" })
	public ResponseEntity<List<DoctorDTO>> findAllFreeForDateTime(@RequestParam("date") String date,
			@RequestParam("type") String type) throws ParseException {

		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);

		List<DoctorDTO> dtoList = clinicService.filterDoctorsPredef(ca.getClinic(), type, date);

		if (dtoList != null) {
			return new ResponseEntity<List<DoctorDTO>>(dtoList, HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping("/addDoctor")
	@Transactional
	public ResponseEntity<HttpStatus> addDoctor(@RequestBody DoctorAddDTO doctor) throws Exception {
		System.out.println("Pozvana metoda kontrolera za dodavanje doktora.");
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);

		Doctor find = doctorService.findOneByEmail(doctor.getEmail());

		if (find == null) {

			Doctor d = new Doctor();
			d.setFirstName(doctor.getFirstName());
			d.setLastName(doctor.getLastName());
			d.setAddress(doctor.getAddress());
			d.setCity(doctor.getCity());
			d.setCountry(doctor.getCountry());
			d.setInsuranceNumber(doctor.getInsuranceNumber());
			d.setPassword(doctor.getPassword());
			d.setEmail(doctor.getEmail());
			d.setPhoneNumber(doctor.getPhoneNumber());
			d.setRole(Role.DOCTOR);
			d.setSpecialty(doctor.getSpecialty());
			d.setClinic(ca.getClinic());
			ca.getClinic().getDoctors().add(d);

			doctorService.save(d);
			// clinicService.save(ca.getClinic());

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteDoctor/{id}")
	public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("id") long doctorId) {

		Doctor del = doctorService.findOneByDoctorID(doctorId);
		Clinic cl = del.getClinic();
		if (del.getAppointments().size() == 0) {
			cl.getDoctors().remove(del);
			del.setClinic(null);

			doctorService.delete(del);
			clinicService.save(cl);

			return new ResponseEntity<>(HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/getFreeTermsForCurrent")
	public ResponseEntity<ArrayList<String>> getFreeTermsForCurrent(@RequestParam("date") String date) {
		ArrayList<String> terms = new ArrayList<>();
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

			return new ResponseEntity<>(terms, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/updateDoctorMyself", consumes = "application/json")
	public ResponseEntity<HttpStatus> updateDoctorMyself(@RequestBody ClinicAdminFullInfo p) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor found = this.doctorService.findOneByEmail(currentUser);

		if (found != null) {
			found.setFirstName(p.getFirstName());
			found.setLastName(p.getLastName());
			found.setAddress(p.getAddress());
			found.setCity(p.getCity());
			found.setCountry(p.getCountry());
			found.setPhoneNumber(p.getPhoneNumber());
			Doctor updatedDoc = doctorService.save(found);

			if (updatedDoc != null)
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			else
				return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findAllFreeForDateTimeSurgery", params = { "date", "type" })
	public ResponseEntity<List<DoctorDTO>> findAllFreeForDateTimeSurgery(@RequestParam("date") String date,
			@RequestParam("type") String type) throws ParseException {

		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);

		List<DoctorDTO> dtoList = clinicService.filterDoctorsForSurgery(ca.getClinic(), date);

		if (dtoList != null) {
			return new ResponseEntity<List<DoctorDTO>>(dtoList, HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
