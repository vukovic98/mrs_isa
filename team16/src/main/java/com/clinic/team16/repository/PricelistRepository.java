package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Pricelist;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long>{
	
	@Query(
			value = "SELECT * FROM pricelist WHERE pricelist.pricelist_id = ?1",
			nativeQuery = true)
	public Pricelist findById(long id);
}
