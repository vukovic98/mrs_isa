/***********************************************************************
 * Module:  Nurse.java
 * Author:  Vladimir
 * Purpose: Defines the Class Nurse
 ***********************************************************************/

import java.util.*;

/** @pdOid cfd4eb24-972b-43f3-af3b-5fb9879024ca */
public class Nurse extends User {
   /** @pdRoleInfo migr=no name=LeaveRequest assc=association16 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<LeaveRequest> leaveRequest;
   /** @pdRoleInfo migr=no name=MedicalReport assc=association24 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<MedicalReport> medicalReport;
   /** @pdRoleInfo migr=no name=Clinic assc=association25 mult=0..1 */
   public Clinic clinic;
   
   
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
         newLeaveRequest.setNurse(this);      
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
            oldLeaveRequest.setNurse((Nurse)null);
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
            oldLeaveRequest.setNurse((Nurse)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<MedicalReport> getMedicalReport() {
      if (medicalReport == null)
         medicalReport = new java.util.HashSet<MedicalReport>();
      return medicalReport;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicalReport() {
      if (medicalReport == null)
         medicalReport = new java.util.HashSet<MedicalReport>();
      return medicalReport.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicalReport */
   public void setMedicalReport(java.util.Collection<MedicalReport> newMedicalReport) {
      removeAllMedicalReport();
      for (java.util.Iterator iter = newMedicalReport.iterator(); iter.hasNext();)
         addMedicalReport((MedicalReport)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicalReport */
   public void addMedicalReport(MedicalReport newMedicalReport) {
      if (newMedicalReport == null)
         return;
      if (this.medicalReport == null)
         this.medicalReport = new java.util.HashSet<MedicalReport>();
      if (!this.medicalReport.contains(newMedicalReport))
      {
         this.medicalReport.add(newMedicalReport);
         newMedicalReport.setNurse(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldMedicalReport */
   public void removeMedicalReport(MedicalReport oldMedicalReport) {
      if (oldMedicalReport == null)
         return;
      if (this.medicalReport != null)
         if (this.medicalReport.contains(oldMedicalReport))
         {
            this.medicalReport.remove(oldMedicalReport);
            oldMedicalReport.setNurse((Nurse)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicalReport() {
      if (medicalReport != null)
      {
         MedicalReport oldMedicalReport;
         for (java.util.Iterator iter = getIteratorMedicalReport(); iter.hasNext();)
         {
            oldMedicalReport = (MedicalReport)iter.next();
            iter.remove();
            oldMedicalReport.setNurse((Nurse)null);
         }
      }
   }

}