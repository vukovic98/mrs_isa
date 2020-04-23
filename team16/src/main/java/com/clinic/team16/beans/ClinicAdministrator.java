package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;
@Entity
@Embeddable
public class ClinicAdministrator extends User {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Clinic_ID")
	public Clinic clinic;
	
	@ElementCollection
	@CollectionTable(name = "clinicAdministrator_leaveRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public ArrayList<LeaveRequest> leaveRequests;

	public ClinicAdministrator() {

	}

	public ClinicAdministrator(Clinic clinic, ArrayList<LeaveRequest> leaveRequests) {
		super();
		this.clinic = clinic;
		this.leaveRequests = leaveRequests;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public ArrayList<LeaveRequest> getLeaveRequests() {
		return leaveRequests;
	}

	public void setLeaveRequests(ArrayList<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}

	public ArrayList<LeaveRequest> getLeaveRequest() {
		if (leaveRequests == null)
			leaveRequests = new ArrayList<LeaveRequest>();
		return leaveRequests;
	}

	public java.util.Iterator getIteratorLeaveRequest() {
		if (leaveRequests == null)
			leaveRequests = new ArrayList<LeaveRequest>();
		return leaveRequests.iterator();
	}

	public void setLeaveRequest(ArrayList<LeaveRequest> newLeaveRequest) {
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