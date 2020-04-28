package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.beans.ClinicalCenterAdministrator;

@Repository
public interface ClinicalCenterAdminRepository extends JpaRepository<ClinicalCenterAdministrator, Long>{

}
