package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

@Entity
@Embeddable
public class Doctor extends User {

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "Clinic_ID")
	public Clinic clinic;

	@ElementCollection
	@CollectionTable(name = "doctor_appointments", joinColumns = @JoinColumn(name = "doctor_id"))
	public List<Appointment> appointments;

	@ElementCollection
	@CollectionTable(name = "doctor_leaveRequests", joinColumns = @JoinColumn(name = "doctor_id"))
	public List<LeaveRequest> leaveRequests;

	public Doctor() {
		super();
	}

	public Doctor(Clinic clinic, ArrayList<Appointment> appointment, ArrayList<LeaveRequest> leaveRequests) {
		super();
		this.clinic = clinic;
		this.appointments = appointment;
		this.leaveRequests = leaveRequests;
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

	public void setAppointment(ArrayList<Appointment> newAppointment) {
		removeAllAppointment();
		for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
			addAppointment((Appointment) iter.next());
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

	public void removeAllAppointment() {
		if (appointments != null) {
			Appointment oldAppointment;
			for (java.util.Iterator iter = getIteratorAppointments(); iter.hasNext();) {
				oldAppointment = (Appointment) iter.next();
				iter.remove();
				oldAppointment.setDoctor((Doctor) null);
			}
		}
	}

	public List<LeaveRequest> getLeaveRequests() {
		if (leaveRequests == null)
			leaveRequests = new ArrayList<LeaveRequest>();
		return leaveRequests;
	}

	public java.util.Iterator getIteratorLeaveRequest() {
		if (leaveRequests == null)
			leaveRequests = new ArrayList<LeaveRequest>();
		return leaveRequests.iterator();
	}

	public java.util.Iterator getIteratorAppointments() {
		if (appointments == null)
			appointments = new ArrayList<Appointment>();
		return appointments.iterator();
	}

	public void setLeaveRequest(java.util.Collection<LeaveRequest> newLeaveRequest) {
		removeAllLeaveRequest();
		for (java.util.Iterator iter = newLeaveRequest.iterator(); iter.hasNext();)
			addLeaveRequest((LeaveRequest) iter.next());
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

	public void removeAllLeaveRequest() {
		if (leaveRequests != null) {
			LeaveRequest oldLeaveRequest;
			for (java.util.Iterator iter = getIteratorLeaveRequest(); iter.hasNext();) {
				oldLeaveRequest = (LeaveRequest) iter.next();
				iter.remove();
			}
		}
	}

}