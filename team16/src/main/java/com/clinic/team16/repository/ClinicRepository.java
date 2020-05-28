package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.ClinicalCenter;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
	
	@Query(
			value = "SELECT * FROM clinic where clinic.name = ?1",
			nativeQuery = true)
	public Clinic findOneByName(String name);
}
