package com.clinic.team16.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.service.PricelistService;

@RestController
@RequestMapping("/pricelistApi")
public class PricelistController {

	@Autowired
	private PricelistService pricelistService;
	
	@GetMapping(path = "/getNames")
	public ResponseEntity<ArrayList<Long>> findAll() {
		ArrayList<Pricelist> list = this.pricelistService.findAll();
		
		if(list != null) {
			ArrayList<Long> ids = new ArrayList<>();
			for (Pricelist p : list) {
				ids.add(p.getPricelistId());
			}
			
			return new ResponseEntity<ArrayList<Long>>(ids, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
