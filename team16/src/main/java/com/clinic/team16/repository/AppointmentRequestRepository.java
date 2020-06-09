package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.AppointmentRequest;

@Repository
public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long>{

	AppointmentRequest findOneByAppointmentRequestId(long id);

	@Query(value = "SELECT * FROM appointment_request WHERE appointment_request.approved = 0",
			nativeQuery = true)
	List<AppointmentRequest> findAllUnapproved();

}
