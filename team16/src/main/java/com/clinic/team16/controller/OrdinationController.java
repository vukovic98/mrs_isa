package com.clinic.team16.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Ordination;
import com.clinic.team16.beans.OrdinationType;
import com.clinic.team16.beans.DTO.AppointmentRequestDTO;
import com.clinic.team16.beans.DTO.OrdinationAddDTO;
import com.clinic.team16.beans.DTO.OrdinationDTO;
import com.clinic.team16.service.AppointmentRequestService;
import com.clinic.team16.service.ClinicAdminService;
import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.OrdinationService;

@RestController
@RequestMapping("/ordinationApi")
public class OrdinationController {

	@Autowired
	private OrdinationService ordinationService;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private ClinicAdminService adminService;
	
	@Autowired
	private AppointmentRequestService requestService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<OrdinationDTO>> findAll() {
		List<Ordination> list = this.ordinationService.findAll();
		List<OrdinationDTO> dtoList = new ArrayList<OrdinationDTO>();
		if (list != null) {
			for (Ordination ordination : list) {
				dtoList.add(new OrdinationDTO(ordination.getName(), ordination.getType()));
			}
			return new ResponseEntity<List<OrdinationDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/addOrdination", consumes = "application/json")
	@Transactional
	public ResponseEntity<HttpStatus> addPricelistItem(@RequestBody OrdinationAddDTO ord) {
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		Ordination o = ordinationService.findOneByNameInClinic(ord.getName(),ca.getClinic().getClinicID());
		
		System.out.println("OVDE SAM");
		System.out.println(ord.getName());
		if (o == null) {

			Clinic cl = clinicService.findOneByClinicID(ca.getClinic().getClinicID());
			Ordination newOrd = new Ordination();
			newOrd.setName(ord.getName());
			newOrd.setType(OrdinationType.valueOf(ord.getType()));
			newOrd.setClinic(cl);
			Ordination or = ordinationService.save(newOrd);
			cl.ordinations.add(newOrd);
			clinicService.save(cl);
			if (or != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/deleteOrdination/{id}", consumes = "application/json")
	@Transactional
	public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") String id) {
		System.out.println(id);
		Ordination or = ordinationService.findOneByNumber(Integer.valueOf(id));
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		
		if (or != null) {
			Clinic cl = clinicService.findOneByClinicID(ca.getClinic().getClinicID());
			
			cl.getOrdinations().remove(or);
			this.clinicService.save(cl);
			or.setClinic(null);
			this.ordinationService.save(or);
			
			this.ordinationService.delete(or);
			
			
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = "/findAllFree")
	public ResponseEntity<List<OrdinationDTO>> findAllFree(@RequestBody AppointmentRequestDTO req) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Ordination> list = ordinationService.findAll();
		List<OrdinationDTO> finalList = new ArrayList<OrdinationDTO>();
		if(list != null) {
			boolean available = true;
			for (Ordination ordination : list) {
				for (Appointment a : ordination.getAppointments()) {
					if(a.getDateTime().equals(sdf.parse(req.getDateTime()))) {
						available = false;
						break;
					}
				}
				if(available) {
					finalList.add(new OrdinationDTO(ordination.getName(), ordination.getType(),ordination.getNumber()));
				}
			}
			return new ResponseEntity<List<OrdinationDTO>>(finalList,HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findAllFreeForRequest/{reqId}")
	public ResponseEntity<List<OrdinationDTO>> findAllFree(@PathVariable("reqId") long reqId) throws ParseException{
		///SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		System.out.println(ca.getClinic().getName());
		List<Ordination> list = ca.getClinic().getOrdinations();
		
		List<OrdinationDTO> finalList = new ArrayList<OrdinationDTO>();
		AppointmentRequest ar = requestService.findOneByAppointmentRequestId(reqId);
		if(list != null) {
			boolean available = true;
			for (Ordination ordination : list) {
				for (Appointment a : ordination.getAppointments()) {
					if(a.getDateTime().equals(ar.getAppointment().getDateTime())) {
						available = false;
						break;
					}
				}
				if(available) {
					if(ordination.getType().equals(OrdinationType.OPERATION)) {
						if(ar.getAppointment().getPricelistItems().getName().equals(AppointmentType.SURGERY))
							finalList.add(new OrdinationDTO(ordination.getName(), ordination.getType(),ordination.getNumber()));
					}else {
						if(!ar.getAppointment().getPricelistItems().getName().equals(AppointmentType.SURGERY))
							finalList.add(new OrdinationDTO(ordination.getName(), ordination.getType(),ordination.getNumber()));
					}
				}
			}
			return new ResponseEntity<List<OrdinationDTO>>(finalList,HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping(path = "/findAllCurrentClinicRooms")
	public ResponseEntity<List<OrdinationDTO>> findAllCurrentClinicRooms(){
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		System.out.println(ca.getClinic().getName());
		List<Ordination> list = ca.getClinic().getOrdinations();
		
		if(list != null) {
			List<OrdinationDTO> dtoList = new ArrayList<OrdinationDTO>();
			for (Ordination ord : list) {
				dtoList.add(new OrdinationDTO(ord.getName(), ord.getType(), ord.getNumber()));
			}
			return new ResponseEntity<List<OrdinationDTO>>(dtoList,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping(path = "/findOneByNumber/{number}")
	public ResponseEntity<OrdinationDTO> findOneByNumber(@PathVariable int number){
		
		Ordination o = ordinationService.findOneByNumber(number);
		if(o != null) {
			OrdinationDTO ret = new OrdinationDTO(o.getName(), o.getType(), o.getNumber());
			ret.setHasAppointments(!o.getAppointments().isEmpty());
			return new ResponseEntity<OrdinationDTO>(ret,HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
