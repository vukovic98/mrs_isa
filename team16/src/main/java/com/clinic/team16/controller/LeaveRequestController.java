package com.clinic.team16.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.Nurse;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.LeaveRequestDTO;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.LeaveRequestService;
import com.clinic.team16.service.NurseService;
import com.clinic.team16.service.UserService;

@RestController
@RequestMapping("/leaveRequestApi")
public class LeaveRequestController {

	@Autowired
	LeaveRequestService leaveRequestService;

	@Autowired
	private UserService userService;

	@Autowired
	private NurseService nurseService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping(path = "/findAllApprovedLeavesForUser")
	public ResponseEntity<ArrayList<LeaveRequestDTO>> findAllApprovedLeaves() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User u = this.userService.findOneByEmail(currentUser);

		if (u != null) {
			ArrayList<LeaveRequest> list = this.leaveRequestService.findAllApprovedLeavesForUser(u.getId());
			
			if (list != null) {
				System.out.println("IMA");
				ArrayList<LeaveRequestDTO> dtoList = new ArrayList<>();

				for (LeaveRequest l : list) {
					dtoList.add(new LeaveRequestDTO(formatter.format(l.getDateFrom()),
							formatter.format(l.getDateTo())));
				}

				return new ResponseEntity<ArrayList<LeaveRequestDTO>>(dtoList, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/addLeaveRequest/{email}", consumes = "application/json")
	public ResponseEntity<HttpStatus> addLeaveRequest(@PathVariable("email") String email,
			@RequestBody LeaveRequest request) {
		User u = this.userService.findOneByEmail(email);
		System.out.println(email);

		if (u != null) {
			if (u instanceof Nurse) {
				Nurse n = this.nurseService.findOneByEmail(u.getEmail());
				LeaveRequest l = new LeaveRequest(request.getDateFrom(), request.getDateTo(), false, n);
				n.addLeaveRequest(l);

				LeaveRequest ok = this.leaveRequestService.save(l);
				this.nurseService.save(n);

				if (ok != null)
					return new ResponseEntity<>(HttpStatus.OK);
				else
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (u instanceof Doctor) {
				Doctor d = this.doctorService.findOneByEmail(u.getEmail());

				LeaveRequest l = new LeaveRequest(request.getDateFrom(), request.getDateTo(), false, d);
				d.addLeaveRequest(l);

				LeaveRequest ok = this.leaveRequestService.save(l);
				this.doctorService.save(d);

				if (ok != null)
					return new ResponseEntity<>(HttpStatus.OK);
				else
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
