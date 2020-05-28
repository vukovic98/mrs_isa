package com.clinic.team16.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.repository.PricelistRepository;

@Service
public class PricelistService {

	@Autowired
	private PricelistRepository pricelistRepository;
	
	public ArrayList<Pricelist> findAll() {
		return (ArrayList<Pricelist>) this.pricelistRepository.findAll();
	}
	
	public Pricelist findById(long id) {
		return this.pricelistRepository.findById(id);
	}
	
	public void save(Pricelist t) {
		this.pricelistRepository.save(t);
	}
}
