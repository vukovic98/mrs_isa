/***********************************************************************
 * Module:  Doctor.java
 * Author:  Vladimir
 * Purpose: Defines the Class Doctor
 ***********************************************************************/

import java.util.*;

/** @pdOid e417e9f4-c2b3-48ed-8573-998ffd23bf80 */
public class Doctor extends User {
   /** @pdRoleInfo migr=no name=Clinic assc=association4 mult=0..1 */
   public Clinic clinic;
   /** @pdRoleInfo migr=no name=Appointment assc=association7 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   /** @pdRoleInfo migr=no name=LeaveRequest assc=association15 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<LeaveRequest> leaveRequest;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new java.util.HashSet<Appointment>();
      return appointment;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new java.util.HashSet<Appointment>();
      return appointment.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointment(java.util.Collection<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new java.util.HashSet<Appointment>();
      if (!this.appointment.contains(newAppointment))
      {
         this.appointment.add(newAppointment);
         newAppointment.setDoctor(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldAppointment */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.Collection<LeaveRequest> getLeaveRequest() {
      if (leaveRequest == null)
         leaveRequest = new java.util.HashSet<LeaveRequest>();
      return leaveRequest;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorLeaveRequest() {
      if (leaveRequest == null)
         leaveRequest = new java.util.HashSet<LeaveRequest>();
      return leaveRequest.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newLeaveRequest */
   public void setLeaveRequest(java.util.Collection<LeaveRequest> newLeaveRequest) {
      removeAllLeaveRequest();
      for (java.util.Iterator iter = newLeaveRequest.iterator(); iter.hasNext();)
         addLeaveRequest((LeaveRequest)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newLeaveRequest */
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
   
   /** @pdGenerated default remove
     * @param oldLeaveRequest */
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
   
   /** @pdGenerated default removeAll */
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