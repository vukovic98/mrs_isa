package com.clinic.team16.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.RegistrationRequest;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long>{
	
	
	public RegistrationRequest findOneByRegistrationRequestId(long id);
	
	@Modifying(clearAutomatically = true)
	@Query(
			value = "DELETE FROM clinic_center_administrator_registration_requests WHERE registration_request_registration_request_id=?1",
			nativeQuery = true)
	public boolean deleteBonds(long id);
	
	@Query(
			value = "SELECT * FROM registration_request WHERE registration_request.approved = 0",
			nativeQuery = true)
	public List<RegistrationRequest> findAllUnapproved();
	
	
	@Query(
			value = "SELECT * FROM registration_request WHERE registration_request.user_id = ?1",
			nativeQuery = true)
	public RegistrationRequest findOneByUserId(long id);
}
