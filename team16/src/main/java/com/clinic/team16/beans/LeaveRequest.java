/***********************************************************************
 * Module:  LeaveRequest.java
 * Author:  Vladimir
 * Purpose: Defines the Class LeaveRequest
 ***********************************************************************/

import java.util.*;

public class LeaveRequest {
   private Date dateFrom;
   private Date dateTo;
   private boolean approved;
   
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