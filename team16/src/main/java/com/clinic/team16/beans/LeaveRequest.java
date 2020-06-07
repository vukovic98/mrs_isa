package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LeaveRequest_ID", nullable = false)
	private long leaveRequestId;
	
	@Column(name = "DateForm", nullable = false)
	private Date dateFrom;
	
	@Column(name = "DateTo", nullable = false)
	private Date dateTo;
	
	@Column(name = "Approved", nullable = false)
	private boolean approved;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "User_ID")
	private User user;

	public LeaveRequest() {
		super();
	}

	public LeaveRequest(Date dateFrom, Date dateTo, boolean approved, User user) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.approved = approved;
		this.user = user;
	}

	public long getLeaveRequestId() {
		return leaveRequestId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}