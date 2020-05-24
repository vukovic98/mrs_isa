package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.PricelistItemDTO;

@Repository
public interface PricelistItemRepository extends JpaRepository<PricelistItem, Long>{

	public PricelistItem findOneByAppointmentType(String appointmentType);
	public PricelistItem findOneByPricelistItemId(long pricelistItemId);
}
