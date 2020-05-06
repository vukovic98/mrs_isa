package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.PricelistItemService;

@RestController
@RequestMapping("/pricelistItemApi")
public class PricelistItemController {

	@Autowired
	private PricelistItemService pricelistItemService;

	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<PricelistItem>> findAll() {
		List<PricelistItem> list = this.pricelistItemService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<PricelistItem>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
