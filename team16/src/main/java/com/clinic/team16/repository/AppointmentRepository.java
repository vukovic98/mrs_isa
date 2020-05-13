package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long > {

	@Query(
			value = "SELECT * FROM appointment WHERE appointment.medical_report_id = ?1",
			nativeQuery = true)
	public Appointment findByMedicalReport(long id);
}
