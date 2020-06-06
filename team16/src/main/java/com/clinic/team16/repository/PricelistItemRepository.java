package com.clinic.team16.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.PricelistItem;

@Repository
public interface PricelistItemRepository extends JpaRepository<PricelistItem, Long>{

	public PricelistItem findOneByAppointmentType(AppointmentType appointmentType);
	public PricelistItem findOneByPricelistItemId(long pricelistItemId);
}
