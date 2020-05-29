package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.ClinicalCenter;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	public Clinic findOneByClinicID(long id);

	@Query(
			value = "SELECT * FROM mrs_isa.clinic as c WHERE  ?1 in "
					+ "(SELECT name FROM mrs_isa.pricelist_item WHERE mrs_isa.pricelist_item.pricelist_id=c.pricelist_id);"
			)
	public List<Clinic> filterClinics(String appType);
	

}
