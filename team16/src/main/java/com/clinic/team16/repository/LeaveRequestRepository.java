package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.RegistrationRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

	@Query(
			value = "SELECT * FROM leave_request WHERE leave_request.approved = true AND leave_request.user_id = ?1",
			nativeQuery = true)
	public List<LeaveRequest> findAllApprovedLeavesByUser(long id);
	
	@Query(
			value = "SELECT * FROM leave_request WHERE leave_request.approved = 0",
			nativeQuery = true)
	public List<LeaveRequest> findAllUnapproved();

	public LeaveRequest findOneByLeaveRequestId(long id);
}
