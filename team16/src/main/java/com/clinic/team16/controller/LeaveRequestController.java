package com.clinic.team16.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.Nurse;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.LeaveRequestDTO;
import com.clinic.team16.beans.DTO.LeaveRequestIdDTO;
import com.clinic.team16.beans.DTO.RegistrationRequestDTO;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.LeaveRequestService;
import com.clinic.team16.service.NurseService;
import com.clinic.team16.service.UserService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<LeaveRequestDTO>> findAll(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<LeaveRequest> list = leaveRequestService.findAllUnapproved();
		List<LeaveRequestDTO> dtoList = new ArrayList<LeaveRequestDTO>();
		
		if (!list.isEmpty()) {
			for (LeaveRequest r : list) {
				String name = r.getUser().getFirstName() + " " + r.getUser().getLastName();
				dtoList.add(new LeaveRequestDTO(r.getUser().getEmail(), name, formatter.format(r.getDateFrom()), formatter.format(r.getDateTo()), r.isApproved(),r.getLeaveRequestId()));
			}
			return new ResponseEntity<List<LeaveRequestDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Status 204
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
	
	@PostMapping(path = "/acceptRequest")
	public ResponseEntity<HttpStatus> acceptUser(@RequestBody LeaveRequestIdDTO reqId) throws MessagingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		LeaveRequest p = this.leaveRequestService.findOneById(Long.valueOf(reqId.getId()));

		if (p != null) {
			p.setApproved(true);

			this.leaveRequestService.save(p);

			this.leaveRequestService.sendAcceptedMail(p.getUser().getEmail(),formatter.format(p.getDateFrom()),formatter.format(p.getDateTo()));

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/findOneById/{id}")
	public ResponseEntity<LeaveRequestDTO> findOneById(@PathVariable("id") long leaveId)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		LeaveRequest p = this.leaveRequestService.findOneById(leaveId);

		if (p != null) {
			
			return new ResponseEntity<LeaveRequestDTO>(new LeaveRequestDTO(formatter.format(p.getDateFrom()), formatter.format(p.getDateTo())),HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
}
