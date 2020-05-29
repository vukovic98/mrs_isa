package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

	@Query(
			value = "SELECT * FROM leave_request WHERE leave_request.approved = true",
			nativeQuery = true)
	public List<LeaveRequest> findAllApprovedLeaves();
}
