package com.clinic.team16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.service.AppointmentRequestService;
import com.clinic.team16.service.AppointmentService;

@RestController
@RequestMapping("/appointmentRequestApi")
public class AppointmentRequestController {

	@Autowired
	private AppointmentRequestService appointmentRequestService;
	
	
}
