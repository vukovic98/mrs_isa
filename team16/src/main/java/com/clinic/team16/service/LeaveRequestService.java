package com.clinic.team16.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {
	
	@Autowired
    private LeaveRequestRepository leaveRequestRepositroy;
	
	public ArrayList<LeaveRequest> findAllApprovedLeaves() {
		return (ArrayList<LeaveRequest>) this.leaveRequestRepositroy.findAllApprovedLeaves();
	}
}
