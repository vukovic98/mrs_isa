package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.ClinicalCenter;

@Repository
public interface ClinicalCenterRepository extends JpaRepository<ClinicalCenter, Long> {

	@Query(
			value = "SELECT * FROM clinical_center WHERE clinic_id = ?1",
			nativeQuery = true)
	public ClinicalCenter findByClinicId(long id);
}
