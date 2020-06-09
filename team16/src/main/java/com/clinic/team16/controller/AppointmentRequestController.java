package com.clinic.team16.controller; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.beans.Ordination;
import com.clinic.team16.beans.DTO.AppointmentRequestDTO;
import com.clinic.team16.beans.DTO.ApproveRequestDTO;
import com.clinic.team16.service.AppointmentRequestService;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.OrdinationService;

@RestController
@RequestMapping("/appointmentRequestApi")
public class AppointmentRequestController {

	@Autowired
	private AppointmentRequestService appointmentRequestService;
	@Autowired
	private OrdinationService ordinationService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<AppointmentRequestDTO>> findAll(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<AppointmentRequest> list = appointmentRequestService.findAll();
		
		if(list != null) {
			List<AppointmentRequestDTO> dtoList = new ArrayList<AppointmentRequestDTO>();
			
			for (AppointmentRequest areq : list) {
				dtoList.add(new AppointmentRequestDTO(areq.getAppointment().getPatient().getEmail(), areq.getAppointment().getDoctor().getEmail(), formatter.format(areq.getAppointment().getDateTime()), areq.getAppointment().getPricelistItems().getName(),areq.getAppointmentRequestId(),areq.getAppointment().getPatient().getFirstName() + " " + areq.getAppointment().getPatient().getLastName(),areq.getAppointment().getDoctor().getFirstName() + " " + areq.getAppointment().getDoctor().getLastName() ));
				
			}
			return new ResponseEntity<List<AppointmentRequestDTO>>(dtoList,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping(path = "/findAllUnapproved")
	public ResponseEntity<List<AppointmentRequestDTO>> findAllUnapproved(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<AppointmentRequest> list = appointmentRequestService.findAllUnapproved();
		if(list != null) {
			List<AppointmentRequestDTO> dtoList = new ArrayList<AppointmentRequestDTO>();
			
			for (AppointmentRequest areq : list) {
				dtoList.add(new AppointmentRequestDTO(areq.getAppointment().getPatient().getEmail(), areq.getAppointment().getDoctor().getEmail(), formatter.format(areq.getAppointment().getDateTime()), areq.getAppointment().getPricelistItems().getName(),areq.getAppointmentRequestId(),areq.getAppointment().getPatient().getFirstName() + " " + areq.getAppointment().getPatient().getLastName(),areq.getAppointment().getDoctor().getFirstName() + " " + areq.getAppointment().getDoctor().getLastName() ));
				
			}
			return new ResponseEntity<List<AppointmentRequestDTO>>(dtoList,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	@GetMapping(path = "/findInfoForRequest/{reqId}")
	public ResponseEntity<AppointmentRequestDTO> findAll(@PathVariable("reqId") long requestId){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		AppointmentRequest areq = appointmentRequestService.findOneByAppointmentRequestId(requestId);
		
		if(areq != null) {
			AppointmentRequestDTO dto = new AppointmentRequestDTO(areq.getAppointment().getPatient().getEmail(), areq.getAppointment().getDoctor().getFirstName() + " " + areq.getAppointment().getDoctor().getLastName(), formatter.format(areq.getAppointment().getDateTime()), areq.getAppointment().getPricelistItems().getName(),areq.getAppointmentRequestId(),areq.getAppointment().getPatient().getFirstName() + " " + areq.getAppointment().getPatient().getLastName(),areq.getAppointment().getDoctor().getFirstName() + " " + areq.getAppointment().getDoctor().getLastName() );
			dto.setOnlyTime(formatterTime.format(areq.getAppointment().getDateTime()));
			return new ResponseEntity<AppointmentRequestDTO>(dto,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping(path = "/approveRequest")
	@Transactional
	public ResponseEntity<HttpStatus> approveRequest(@RequestBody ApproveRequestDTO body){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		AppointmentRequest approve = appointmentRequestService.findOneByAppointmentRequestId(body.getRequestId());
		
		if(approve != null) {
			approve.setApproved(true);
			Ordination or = ordinationService.findOneByNumber(body.getOrdId());
			approve.getAppointment().setOrdination(or);
			
			appointmentRequestService.save(approve);
			appointmentService.save(approve.getAppointment());
			appointmentRequestService.sendAcceptedMail(or.getName(), approve.getAppointment().getDoctor().getFirstName() + " "+ approve.getAppointment().getDoctor().getLastName(), sdf.format(approve.getAppointment().getDateTime()));
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
