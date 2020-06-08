package com.clinic.team16.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Ordination;

@Repository
public interface OrdinationRepository extends JpaRepository<Ordination, Integer>{

	public Ordination findOneByName(String name);

	@Query(value = "SELECT * FROM ordination WHERE ordination.name = ?1 AND ordination.clinic_id = ?2",
			nativeQuery = true)
	public Ordination findOneByNameInClinic(String name, Long clinicId);

	

}
