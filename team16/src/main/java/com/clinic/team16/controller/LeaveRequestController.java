package com.clinic.team16.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.DTO.LeaveRequestDTO;
import com.clinic.team16.service.LeaveRequestService;

@RestController
@RequestMapping("/leaveRequestApi")
public class LeaveRequestController {

	@Autowired
	LeaveRequestService leaveRequestService;

	@GetMapping(path = "/findAllApprovedLeaves")
	public ResponseEntity<ArrayList<LeaveRequestDTO>> findAllApprovedLeaves() {
		ArrayList<LeaveRequest> list = this.leaveRequestService.findAllApprovedLeaves();

		if (list != null) {
			ArrayList<LeaveRequestDTO> dtoList = new ArrayList<>();

			for (LeaveRequest l : list) {
				dtoList.add(new LeaveRequestDTO(l.getDateFrom(), l.getDateTo()));
			}

			return new ResponseEntity<ArrayList<LeaveRequestDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}}
