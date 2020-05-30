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

import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.PricelistItemDTO;
import com.clinic.team16.service.PricelistItemService;
import com.clinic.team16.service.PricelistService;

@RestController
@RequestMapping("/pricelistItemApi")
public class PricelistItemController {

	@Autowired
	private PricelistItemService pricelistItemService;

	@Autowired
	private PricelistService pricelistService;


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
	public ResponseEntity<HttpStatus> addPricelistItem(@RequestBody PricelistItem pri) {
		PricelistItem p = this.pricelistItemService.findOneByAppointmentType(pri.getName());
		
		if (p == null) {

			Pricelist prList = pricelistService.findOneByPricelistId(1);

			pri.setPricelist(prList);
			PricelistItem k = this.pricelistItemService.save(pri);

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

		if (pr != null) {
			pr.setPricelist(null);
			this.pricelistItemService.delete(pr);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
