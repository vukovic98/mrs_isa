/***********************************************************************
 * Module:  Doctor.java
 * Author:  Vladimir
 * Purpose: Defines the Class Doctor
 ***********************************************************************/

import java.util.*;

/** @pdOid e417e9f4-c2b3-48ed-8573-998ffd23bf80 */
public class Doctor extends User {

   public Clinic clinic;
   public ArrayList<Appointment> appointments;
   public ArrayList<LeaveRequest> leaveRequests;
   
   
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


public ArrayList<LeaveRequest> getLeaveRequests() {
	return leaveRequests;
}


public void setLeaveRequests(ArrayList<LeaveRequest> leaveRequests) {
	this.leaveRequests = leaveRequests;
}


public void setAppointments(ArrayList<Appointment> appointments) {
	this.appointments = appointments;
}


public ArrayList<Appointment> getAppointments() {
      if (appointment == null)
         appointments = new ArrayList<Appointment>();
      return appointment;
   }

   
   public void setAppointment(ArrayList<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new ArrayList<Appointment>();
      if (!this.appointment.contains(newAppointment))
      {
         this.appointment.add(newAppointment);
         newAppointment.setDoctor(this);      
      }
   }
   
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
         {
            this.appointment.remove(oldAppointment);
            oldAppointment.setDoctor((Doctor)null);
         }
   }
   
   public void removeAllAppointment() {
      if (appointment != null)
      {
         Appointment oldAppointment;
         for (java.util.Iterator iter = getIteratorAppointment(); iter.hasNext();)
         {
            oldAppointment = (Appointment)iter.next();
            iter.remove();
            oldAppointment.setDoctor((Doctor)null);
         }
      }
   }
   public java.util.Collection<LeaveRequest> getLeaveRequest() {
      if (leaveRequest == null)
         leaveRequest = new ArrayList<LeaveRequest>();
      return leaveRequest;
   }
   
   public java.util.Iterator getIteratorLeaveRequest() {
      if (leaveRequest == null)
         leaveRequest = new ArrayList<LeaveRequest>();
      return leaveRequest.iterator();
   }
   
   public void setLeaveRequest(java.util.Collection<LeaveRequest> newLeaveRequest) {
      removeAllLeaveRequest();
      for (java.util.Iterator iter = newLeaveRequest.iterator(); iter.hasNext();)
         addLeaveRequest((LeaveRequest)iter.next());
   }
   
   public void addLeaveRequest(LeaveRequest newLeaveRequest) {
      if (newLeaveRequest == null)
         return;
      if (this.leaveRequest == null)
         this.leaveRequest = new java.util.HashSet<LeaveRequest>();
      if (!this.leaveRequest.contains(newLeaveRequest))
      {
         this.leaveRequest.add(newLeaveRequest);
         newLeaveRequest.setDoctor(this);      
      }
   }
   
   public void removeLeaveRequest(LeaveRequest oldLeaveRequest) {
      if (oldLeaveRequest == null)
         return;
      if (this.leaveRequest != null)
         if (this.leaveRequest.contains(oldLeaveRequest))
         {
            this.leaveRequest.remove(oldLeaveRequest);
            oldLeaveRequest.setDoctor((Doctor)null);
         }
   }
   
   public void removeAllLeaveRequest() {
      if (leaveRequest != null)
      {
         LeaveRequest oldLeaveRequest;
         for (java.util.Iterator iter = getIteratorLeaveRequest(); iter.hasNext();)
         {
            oldLeaveRequest = (LeaveRequest)iter.next();
            iter.remove();
            oldLeaveRequest.setDoctor((Doctor)null);
         }
      }
   }

}