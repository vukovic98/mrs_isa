package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.MetaValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class Nurse extends User {

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "nurse_leaveRequests", joinColumns = @JoinColumn(name = "nurse_id"))
	public List<LeaveRequest> leaveRequests;
    
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "nurse_medicalReports", joinColumns = @JoinColumn(name = "nurse_id"))
	public List<MedicalReport> medicalReports;
    
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Nurse_Clinic_ID")
	public Clinic clinic;

	public Nurse() {
		super();
	}

	public Nurse(ArrayList<LeaveRequest> leaveRequest, ArrayList<MedicalReport> medicalReport, Clinic clinic) {
		super();
		this.leaveRequests = leaveRequest;
		this.medicalReports = medicalReport;
		this.clinic = clinic;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
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

	

	public List<MedicalReport> getMedicalReports() {
		if (medicalReports == null)
			medicalReports = new ArrayList<MedicalReport>();
		return medicalReports;
	}

	

	public void addMedicalReport(MedicalReport newMedicalReport) {
		if (newMedicalReport == null)
			return;
		if (this.medicalReports == null)
			this.medicalReports = new ArrayList<MedicalReport>();
		if (!this.medicalReports.contains(newMedicalReport)) {
			this.medicalReports.add(newMedicalReport);
			newMedicalReport.setNurse(this);
		}
	}

	public void removeMedicalReport(MedicalReport oldMedicalReport) {
		if (oldMedicalReport == null)
			return;
		if (this.medicalReports != null)
			if (this.medicalReports.contains(oldMedicalReport)) {
				this.medicalReports.remove(oldMedicalReport);
				oldMedicalReport.setNurse((Nurse) null);
			}
	}

	

}