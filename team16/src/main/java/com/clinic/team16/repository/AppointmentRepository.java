package com.clinic.team16.repository;

import java.util.List;

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

	@Query(
			value = "SELECT * FROM appointment WHERE appointment.doctor_id = ?1",
			nativeQuery = true
			)
	public List<Appointment> findByDoctor(long id);


	@Query(
			value = "SELECT * FROM appointment WHERE appointment.appointment_id = ?1",
			nativeQuery = true)
	public Appointment findOneById(long id);
	
	@Query(
			value = "SELECT * FROM appointment WHERE substring(appointment.date_time,1,10) = ?1 AND appointment.doctor_id = ?2",
			nativeQuery = true)
	public List<Appointment> findByDoctorAndDate(String date, Long doctor_id);
}
