package com.clinic.team16.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.service.MedicationService;

@RestController
@RequestMapping("/medicationApi")
public class MedicationController {
	@Autowired
	MedicationService medicationService;
}
