package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.ClinicFilterDTO;
import com.clinic.team16.beans.DTO.ClinicInfoDTO;
import com.clinic.team16.beans.DTO.RateDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.GradeService;
import com.clinic.team16.service.PatientService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ClinicInfoDTO>> findAll() {
		List<Clinic> list = this.clinicService.findAll();
		List<ClinicInfoDTO> daoList = new ArrayList<ClinicInfoDTO>();
		
		if(list != null) {
			for(Clinic c : list) {
				daoList.add(new ClinicInfoDTO(c.getClinicID() ,c.getName(), c.getAddress(), c.getDescription(),c.getAverageGrade()));
			}
			return new ResponseEntity<List<ClinicInfoDTO>>(daoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping(path="/findAppointments/{appType}&{date}&{avgGrade}", consumes = "application/json")
	public ResponseEntity<List<ClinicFilterDTO>> findAppointments(@PathVariable String appType, @PathVariable String date, @PathVariable String avgGrade) {
		List<Clinic> list = this.clinicService.filterClinics(appType);
		List<ClinicFilterDTO> dtoList = new ArrayList<ClinicFilterDTO>();
		double price = 0.0;
		boolean hasAvailableDoctor = false;
		if(list != null) {
			for(Clinic c : list) {
				for(PricelistItem pli: c.getPricelist().getPricelistItems()) {
					if(pli.getName().equalsIgnoreCase(appType)) {
						price = pli.getPrice();
						break;
					}
				}
				if(Math.ceil(c.getAverageGrade())==Double.parseDouble(avgGrade)) {
					for(Doctor d:c.getDoctors()) {
						
					}
					if(hasAvailableDoctor) {
						dtoList.add(new ClinicFilterDTO(c.getClinicID(),c.getName(),c.getAddress(),c.getAverageGrade(),appType,price));
					}
				}
			}
			return new ResponseEntity<List<ClinicFilterDTO>>(dtoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	@PutMapping(path="/rateClinic/{clinicID}&{grade}", consumes = "application/json")
	public ResponseEntity<HttpStatus> rateClinic(@PathVariable("clinicID") long clinicID, @PathVariable("grade") String grade) {
	
		long id = clinicID;
		Clinic c = this.clinicService.findOneByClinicID(id);
		Patient p = this.patientService.findOneByEmail("p@p");
		if(c!= null && p != null) {
			Grade g = c.addGrade(p, Integer.parseInt(grade));
			gradeService.save(g);
			clinicService.save(c);
			patientService.save(p);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
