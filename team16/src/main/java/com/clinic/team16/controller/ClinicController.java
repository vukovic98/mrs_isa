package com.clinic.team16.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;
<<<<<<< HEAD
=======
import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Doctor;
>>>>>>> ec0a9f3aa9445bc823f3055524202a1a29127774
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.ClinicAddDTO;
import com.clinic.team16.beans.DTO.ClinicFilterDTO;
import com.clinic.team16.beans.DTO.ClinicInfoDTO;
<<<<<<< HEAD
import com.clinic.team16.beans.DTO.DoctorDTO;
=======
import com.clinic.team16.service.ClinicAdminService;
>>>>>>> ec0a9f3aa9445bc823f3055524202a1a29127774
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.GradeService;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.PricelistService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private PricelistService pricelistService;

	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private ClinicAdminService adminService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<ArrayList<ClinicInfoDTO>> findAll() {
		ArrayList<Clinic> list = this.clinicService.findAll();
		if (list != null) {
			ArrayList<ClinicInfoDTO> daoList = new ArrayList<ClinicInfoDTO>();
			for (Clinic c : list) {
				daoList.add(new ClinicInfoDTO(c.getClinicID(), c.getName(), c.getAddress(), c.getDescription(),
						c.getAverageGrade(), c.getCity()));
			}
			return new ResponseEntity<ArrayList<ClinicInfoDTO>>(daoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(path = "/addClinic", consumes = "application/json")
	public ResponseEntity<HttpStatus> addClinic(@RequestBody ClinicAddDTO clinic) {
		Clinic found = this.clinicService.findOneByName(clinic.getName());

		if (found == null) {
			Pricelist p = this.pricelistService.findById(clinic.getPricelist());

			if (p != null) {
				Clinic c = new Clinic(clinic.getName(), clinic.getAddress(), clinic.getDescription(), null, p, null,
						null, null, null);

				p.addClinic(c);
				this.clinicService.save(c);

				this.pricelistService.save(p);

				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAppointments/{appType}&{date}&{avgGrade}&{location}")
	public ResponseEntity<List<ClinicFilterDTO>> findAppointments(@PathVariable AppointmentType appType,
			@PathVariable String date, @PathVariable String avgGrade, @PathVariable String location)
			throws ParseException {
		List<Clinic> list = this.clinicService.filterClinics(appType.toString());
		List<ClinicFilterDTO> dtoList = new ArrayList<ClinicFilterDTO>();
		double price = 0.0;
		if (list != null) {
			for (Clinic c : list) {
				System.out.println("ima");
				for (PricelistItem pli : c.getPricelist().getPricelistItems()) {
					if (pli.getName() == appType) {
						price = pli.getPrice();
						break;
					}
				}

				boolean rightGrade = false;

				if (!avgGrade.equalsIgnoreCase("null")) {
					if (Math.ceil(c.getAverageGrade()) >= Double.parseDouble(avgGrade)) {
						rightGrade = true;
					}
				} else {
					rightGrade = true;
				}

				boolean rightLocation = false;

				if (!location.equalsIgnoreCase("null")) {
					if (c.getCity().equalsIgnoreCase(location)) {
						rightLocation = true;
					}
				} else {
					rightLocation = true;
				}

				if (rightGrade && rightLocation) {
					ArrayList<DoctorDTO> doctors = this.clinicService.filterDoctors(c, appType, date);
					if (doctors.size() != 0) {
						dtoList.add(new ClinicFilterDTO(c.getClinicID(), c.getName(), c.getAddress(),
								c.getAverageGrade(), appType, price));
					}
				}
			}
			return new ResponseEntity<List<ClinicFilterDTO>>(dtoList, HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PutMapping(path = "/rateClinic/{clinicID}&{grade}", consumes = "application/json")
	public ResponseEntity<HttpStatus> rateClinic(@PathVariable("clinicID") long clinicID,
			@PathVariable("grade") String grade) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		long id = clinicID;
		Clinic c = this.clinicService.findOneByClinicID(id);
		Patient p = this.patientService.findOneByEmail(currentUser);
		if (c != null && p != null) {
			Grade g = c.addGrade(p, Integer.parseInt(grade));
			gradeService.save(g);
			clinicService.save(c);
			patientService.save(p);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
<<<<<<< HEAD
	@GetMapping(path = "/findAllAppointmentDoctors/{clinicID}&{appType}&{date}")
	public ResponseEntity<ArrayList<DoctorDTO>> findAllAppointmentDoctors(@PathVariable long clinicID,
			@PathVariable AppointmentType appType, @PathVariable String date) {
		Clinic c = this.clinicService.findOneByClinicID(clinicID);
		
		ArrayList<DoctorDTO> doctors = this.clinicService.filterDoctors(c, appType, date);
		
		if(doctors != null)
			return new ResponseEntity<ArrayList<DoctorDTO>>(doctors, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

=======
	@GetMapping(path = "/getClinicGrade/{clinicID}")
	public ResponseEntity<ClinicInfoDTO> clinicGrade(@PathVariable("clinicID") long clinicID){
		Clinic c = this.clinicService.findOneByClinicID(clinicID);
		if(c != null) {
			ClinicInfoDTO cdt = new ClinicInfoDTO(clinicID, c.getName(), c.getAddress(), c.getDescription(), c.getAverageGrade());
			
			return new ResponseEntity<ClinicInfoDTO>(cdt,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/getCurrentClinic")
	public ResponseEntity<Long> currentClinic(){
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		
		if( ca != null) {
			return new ResponseEntity<Long>(ca.getClinic().getClinicID(),HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping(path = "/getCurrentClinicInfo")
	public ResponseEntity<ClinicInfoDTO> currentClinicInfo(){
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		ClinicInfoDTO cl;
		Clinic currCl = clinicService.findOneByClinicID(ca.getClinic().getClinicID());
		cl = new ClinicInfoDTO(currCl.getClinicID(), currCl.getName(), currCl.getAddress(), currCl.getDescription(), currCl.getAverageGrade());
		if( currCl != null) {
			return new ResponseEntity<ClinicInfoDTO>(cl,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
>>>>>>> ec0a9f3aa9445bc823f3055524202a1a29127774
}
