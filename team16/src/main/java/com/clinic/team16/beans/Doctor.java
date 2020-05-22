package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

@Entity
@Embeddable
public class Doctor extends User {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Clinic_ID")
	public Clinic clinic;


	@ElementCollection
	@CollectionTable(name = "doctor_appointments", joinColumns = @JoinColumn(name = "doctor_id"))
	public List<Appointment> appointments;


	@ElementCollection
	@CollectionTable(name = "doctor_leaveRequests", joinColumns = @JoinColumn(name = "doctor_id"))
	public List<LeaveRequest> leaveRequests;
	
	@ElementCollection
	@CollectionTable(name = "doctor_grades", joinColumns = @JoinColumn(name = "doctor_id"))
	public List<Grade> grades;

	public Doctor() {
		super();
	}

	public Doctor(Clinic clinic, ArrayList<Appointment> appointment, ArrayList<LeaveRequest> leaveRequests, ArrayList<Grade> grades) {
		super();
		this.clinic = clinic;
		this.appointments = appointment;
		this.leaveRequests = leaveRequests;
		this.grades = grades;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public void setLeaveRequests(ArrayList<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		if (appointments == null)
			appointments = new ArrayList<Appointment>();
		return appointments;
	}

	

	public void addAppointment(Appointment newAppointment) {
		if (newAppointment == null)
			return;
		if (this.appointments == null)
			this.appointments = new ArrayList<Appointment>();
		if (!this.appointments.contains(newAppointment)) {
			this.appointments.add(newAppointment);
			newAppointment.setDoctor(this);
		}
	}

	public void removeAppointment(Appointment oldAppointment) {
		if (oldAppointment == null)
			return;
		if (this.appointments != null)
			if (this.appointments.contains(oldAppointment)) {
				this.appointments.remove(oldAppointment);
				oldAppointment.setDoctor((Doctor) null);
			}
	}

	

	public List<LeaveRequest> getLeaveRequests() {
		if (leaveRequests == null)
			leaveRequests = new ArrayList<LeaveRequest>();
		return leaveRequests;
	}

	

	public void addLeaveRequest(LeaveRequest newLeaveRequest) {
		if (newLeaveRequest == null)
			return;
		if (this.leaveRequests == null)
			this.leaveRequests = new ArrayList<LeaveRequest>();
		if (!this.leaveRequests.contains(newLeaveRequest)) {
			this.leaveRequests.add(newLeaveRequest);
		}
	}

	public void removeLeaveRequest(LeaveRequest oldLeaveRequest) {
		if (oldLeaveRequest == null)
			return;
		if (this.leaveRequests != null)
			if (this.leaveRequests.contains(oldLeaveRequest)) {
				this.leaveRequests.remove(oldLeaveRequest);
			}
	}

	
	
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Grade> grades) {
		this.grades = grades;
	}

	public void addGrade(int number, Patient p) {
		boolean exists = false;
		
		for(Grade g : this.grades) {
			if(g.getPatient().getEmail().equalsIgnoreCase(p.getEmail())) {
				g.setGradeNumber(number);
				exists = true;
			}
		}
		
		if(!exists) {
			Grade g = new Grade(0, number, p);
			this.grades.add(g);
		}
	}

}