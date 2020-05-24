package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Pricelist;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long>{

	public Pricelist findOneByPricelistId(long pricelistId);
}
