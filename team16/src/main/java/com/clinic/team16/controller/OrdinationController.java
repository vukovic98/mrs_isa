package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Ordination;
import com.clinic.team16.beans.OrdinationType;
import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.OrdinationAddDTO;
import com.clinic.team16.beans.DTO.OrdinationDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.OrdinationService;

@RestController
@RequestMapping("/ordinationApi")
public class OrdinationController {

	@Autowired
	private OrdinationService ordinationService;
	
	@Autowired
	private ClinicService clinicService;
	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<OrdinationDTO>> findAll() {
		List<Ordination> list = this.ordinationService.findAll();
		List<OrdinationDTO> dtoList = new ArrayList<OrdinationDTO>();
		if(list != null) {
			for (Ordination ordination : list) {
				dtoList.add(new OrdinationDTO(ordination.getName(), ordination.getType()));
			}
			return new ResponseEntity<List<OrdinationDTO>>(dtoList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/addOrdination", consumes = "application/json")
	public ResponseEntity<HttpStatus> addPricelistItem(@RequestBody OrdinationAddDTO ord) {
		Ordination o = ordinationService.findOneByName(ord.getName());
		System.out.println("OVDE SAM");
		System.out.println(ord.getName());
		if (o == null) {

			Clinic cl = clinicService.findOneByClinicID(1);
			Ordination newOrd = new Ordination();
			newOrd.setName(ord.getName());
			newOrd.setType(OrdinationType.valueOf(ord.getType()));
			newOrd.setClinic(cl);
			Ordination or = ordinationService.save(newOrd);

			if (or != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping(path = "/deleteOrdination/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") String id) {
		System.out.println(id);
		Ordination or = ordinationService.findOneByName(id);
		
		if (or != null) {
			
			or.setClinic(null);
			this.ordinationService.delete(or);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
