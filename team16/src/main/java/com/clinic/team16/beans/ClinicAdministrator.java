package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Embeddable
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class ClinicAdministrator extends User {
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Clinic_ID")
	public Clinic clinic;
	
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "clinicAdministrator_leaveRequests",joinColumns = @JoinColumn(name = "administrator_id"))
	public List<LeaveRequest> leaveRequests;

	public ClinicAdministrator() {

	}
	
	

	public ClinicAdministrator(String email, String password, String firstName, String lastName, String address,
			String city, String country, String phoneNumber, String insuranceNumber, Role role, Clinic clinic) {
		super(email, password, firstName, lastName, address, city, country, phoneNumber, insuranceNumber, role);
		this.clinic = clinic;
		this.leaveRequests = null;
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

	public void setLeaveRequests(ArrayList<LeaveRequest> leaveRequests) {
		this.leaveRequests = leaveRequests;
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

	

}