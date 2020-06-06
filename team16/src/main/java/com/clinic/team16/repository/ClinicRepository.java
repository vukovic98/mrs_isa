package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
	
	@Query(
			value = "SELECT * FROM clinic where clinic.name = ?1",
			nativeQuery = true)
	public Clinic findOneByName(String name);
	public Clinic findOneByClinicID(long id);

	@Query(
			value = "SELECT * FROM mrs_isa.clinic as c WHERE  ?1 in (SELECT name FROM mrs_isa.pricelist_item WHERE mrs_isa.pricelist_item.pricelist_id=c.pricelist_id)",
			nativeQuery = true)
	public List<Clinic> filterClinics(AppointmentType appType);
	

}
