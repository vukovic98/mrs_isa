package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.PricelistRepository;

@Service
public class PricelistService {

	@Autowired
	private PricelistRepository pricelistRepository;
}
