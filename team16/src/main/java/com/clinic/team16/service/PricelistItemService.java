package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.PricelistItemDTO;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.PricelistItemRepository;

@Service
public class PricelistItemService {

	@Autowired
	private PricelistItemRepository pricelistItemRepository;

	public List<PricelistItem> findAll() {
		return pricelistItemRepository.findAll();
	}
	
	public PricelistItem findOneByAppointmentType(AppointmentType appointmentType) {
		return pricelistItemRepository.findOneByAppointmentType(appointmentType);
	}
	
	public PricelistItem findOneByPricelistItemId(long pricelistItemId) {
		return pricelistItemRepository.findOneByPricelistItemId(pricelistItemId);
	}
	
	public PricelistItem save(PricelistItem p) {
		return this.pricelistItemRepository.save(p);
	}
	
	public void delete(PricelistItem p) {
		this.pricelistItemRepository.delete(p);
	}
}
