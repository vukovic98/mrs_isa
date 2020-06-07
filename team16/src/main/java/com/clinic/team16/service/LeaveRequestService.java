package com.clinic.team16.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {
	
	@Autowired
    private LeaveRequestRepository leaveRequestRepositroy;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public ArrayList<LeaveRequest> findAllApprovedLeavesForUser(long id) {
		return (ArrayList<LeaveRequest>) this.leaveRequestRepositroy.findAllApprovedLeavesByUser(id);
	}
	
	public LeaveRequest findOneById(long id) {
		return leaveRequestRepositroy.findOneByLeaveRequestId(id);
	}
	
	public LeaveRequest save(LeaveRequest l) {
		return this.leaveRequestRepositroy.save(l);
	}
	
	public List<LeaveRequest> findAllUnapproved() {
		return this.leaveRequestRepositroy.findAllUnapproved();
	}
	
	@Async
	public void sendAcceptedMail(String email, String start, String end) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo("mrs.isa2020@gmail.com");
			helper.setSubject("DIV Clinical Center");

			StringBuffer sb = new StringBuffer();

			sb.append("<h2>Your leave request has been accepted!</h2><br>");
			sb.append(String.format("<h3>Start date: <b>%s</b>, end date: <b>%s</b></h3> <br><br>",start,end));

			helper.setText(sb.toString(), true);
			this.javaMailSender.send(msg);

			System.out.println("SENT ACCEPTED MAIL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
