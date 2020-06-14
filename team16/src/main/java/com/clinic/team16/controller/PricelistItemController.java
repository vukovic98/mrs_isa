package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.PricelistItemDTO;
import com.clinic.team16.service.ClinicAdminService;
import com.clinic.team16.service.PricelistItemService;
import com.clinic.team16.service.PricelistService;

@RestController
@RequestMapping("/pricelistItemApi")
public class PricelistItemController {

	@Autowired
	private PricelistItemService pricelistItemService;

	@Autowired
	private PricelistService pricelistService;

	@Autowired
	private ClinicAdminService adminService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<PricelistItem>> findAll() {
		List<PricelistItem> list = this.pricelistItemService.findAll();

		if (list != null)
			return new ResponseEntity<List<PricelistItem>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findAllAppointmentTypes")
	public ResponseEntity<List<PricelistItemDTO>> findAllAppointmentTypes() {
		List<PricelistItem> list = this.pricelistItemService.findAll();
		List<PricelistItemDTO> listDTO = new ArrayList<PricelistItemDTO>();
		if (list != null) {
			for (PricelistItem p : list) {
				PricelistItemDTO item = new PricelistItemDTO(p.getName(), String.valueOf(p.getPrice()),
						String.valueOf(p.getPricelistItemId()));
				listDTO.add(item);
			}
			return new ResponseEntity<List<PricelistItemDTO>>(listDTO, HttpStatus.OK);
		}

		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(path = "/addPricelistItem", consumes = "application/json")
	public ResponseEntity<HttpStatus> addPricelistItem(@RequestBody PricelistItemDTO p) {
		PricelistItem found = this.pricelistItemService.findOneByAppointmentType(p.getAppointmentType());
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		if (found == null) {
			PricelistItem pri = new PricelistItem(p.getAppointmentType().toUpperCase(), Double.parseDouble(p.getPrice()), null);
			Pricelist prList = pricelistService.findOneByPricelistId(ca.getClinic().getPricelist().getPricelistId());

			pri.setPricelist(prList);
			PricelistItem k = this.pricelistItemService.save(pri);

			prList.getPricelistItems().add(k);
			pricelistService.save(prList);
			
			
			if (k != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/deletePricelistItem/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") String id) {
		System.out.println("id je " + id);
		PricelistItem pr = this.pricelistItemService.findOneByPricelistItemId(Long.parseLong(id));
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		boolean allowed = true;
		
		for (Doctor d : ca.getClinic().getDoctors()) {
			for (Appointment apt : d.getAppointments()) {
				if(apt.getPricelistItems().getName().equalsIgnoreCase(pr.getName())) {
					allowed = false;
					break;
				}
			}
		}
		
		if (pr != null && allowed) {
			Pricelist prList = pr.getPricelist();
			prList.getPricelistItems().remove(pr);
			
			
			pr.setPricelist(null);
			this.pricelistItemService.delete(pr);
			this.pricelistService.save(prList);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = "/editPricelistItem", consumes = "application/json")
	public ResponseEntity<HttpStatus> editPricelistItem(@RequestBody PricelistItemDTO p) {
		System.out.println("id je " + p.getId());
		PricelistItem pr = this.pricelistItemService.findOneByPricelistItemId(Long.parseLong(p.getId()));
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		
		boolean allowed = true;
		for (Doctor d : ca.getClinic().getDoctors()) {
			for (Appointment apt : d.getAppointments()) {
				if(apt.getPricelistItems().getName().equalsIgnoreCase(pr.getName())) {
					allowed = false;
					break;
				}
			}
		}
		
		if (pr != null && allowed) {
			pr.setName(p.getAppointmentType().toUpperCase());
			pr.setPrice(Double.valueOf(p.getPrice().replace("$", "")));

			this.pricelistItemService.save(pr);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/findAllAppointmentTypesCurrentClinic")
	public ResponseEntity<List<PricelistItemDTO>> findAllAppointmentTypesCurrentClinic() {
		//List<PricelistItem> list = this.pricelistItemService.findAll();
		List<PricelistItemDTO> listDTO = new ArrayList<PricelistItemDTO>();
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		if (ca != null) {
			for (PricelistItem p : ca.getClinic().getPricelist().getPricelistItems()) {
				PricelistItemDTO item = new PricelistItemDTO(p.getName(), String.valueOf(p.getPrice()),
						String.valueOf(p.getPricelistItemId()));
				listDTO.add(item);
			}
			return new ResponseEntity<List<PricelistItemDTO>>(listDTO, HttpStatus.OK);
		}

		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
