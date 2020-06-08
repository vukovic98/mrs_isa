package com.clinic.team16.controller; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.beans.DTO.AppointmentRequestDTO;
import com.clinic.team16.service.AppointmentRequestService;

@RestController
@RequestMapping("/appointmentRequestApi")
public class AppointmentRequestController {

	@Autowired
	private AppointmentRequestService appointmentRequestService;
	
	
	@GetMapping(path = "/findAll")
	public ResponseEntity<List<AppointmentRequestDTO>> findAll(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<AppointmentRequest> list = appointmentRequestService.findAll();
		
		if(list != null) {
			List<AppointmentRequestDTO> dtoList = new ArrayList<AppointmentRequestDTO>();
			
			for (AppointmentRequest areq : list) {
				dtoList.add(new AppointmentRequestDTO(areq.getAppointment().getPatient().getEmail(), areq.getAppointment().getDoctor().getEmail(), formatter.format(areq.getAppointment().getDateTime()), areq.getAppointment().getPricelistItems().getName().name(),areq.getAppointment().getPatient().getFirstName() + " " + areq.getAppointment().getPatient().getLastName(),areq.getAppointment().getDoctor().getFirstName() + " " + areq.getAppointment().getDoctor().getLastName() ));
				
			}
			return new ResponseEntity<List<AppointmentRequestDTO>>(dtoList,HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
