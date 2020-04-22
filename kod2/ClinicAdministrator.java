/***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Vladimir
 * Purpose: Defines the Class ClinicAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid f2eb8509-e4fa-433a-aed9-15d32cb11736 */
public class ClinicAdministrator extends User {
   /** @pdRoleInfo migr=no name=Clinic assc=association3 mult=1..1 */
   public Clinic clinic;
   /** @pdRoleInfo migr=no name=LeaveRequest assc=association17 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<LeaveRequest> leaveRequest;
   
   
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
         newLeaveRequest.setClinicAdministrator(this);      
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
            oldLeaveRequest.setClinicAdministrator((ClinicAdministrator)null);
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
            oldLeaveRequest.setClinicAdministrator((ClinicAdministrator)null);
         }
      }
   }

}