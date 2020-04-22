/***********************************************************************
 * Module:  LeaveRequest.java
 * Author:  Vladimir
 * Purpose: Defines the Class LeaveRequest
 ***********************************************************************/

import java.util.*;

/** @pdOid bfc00e7c-b558-481f-82ee-d3cb21d621ff */
public class LeaveRequest {
   /** @pdOid 8befef65-b084-45a1-a32a-05b4ca6f492f */
   private Date dateFrom;
   /** @pdOid 869c5943-c765-4a41-a268-7afc5a022298 */
   private Date dateTo;
   /** @pdOid bce45401-0848-4fe0-bb46-3fa9d88d8e4e */
   private boolean approved;
   
   /** @pdRoleInfo migr=no name=Doctor assc=association15 mult=1..1 side=A */
   public Doctor doctor;
   /** @pdRoleInfo migr=no name=Nurse assc=association16 mult=1..1 side=A */
   public Nurse nurse;
   /** @pdRoleInfo migr=no name=ClinicAdministrator assc=association17 mult=1..1 side=A */
   public ClinicAdministrator clinicAdministrator;
   
   
   /** @pdGenerated default parent getter */
   public Doctor getDoctor() {
      return doctor;
   }
   
   /** @pdGenerated default parent setter
     * @param newDoctor */
   public void setDoctor(Doctor newDoctor) {
      if (this.doctor == null || !this.doctor.equals(newDoctor))
      {
         if (this.doctor != null)
         {
            Doctor oldDoctor = this.doctor;
            this.doctor = null;
            oldDoctor.removeLeaveRequest(this);
         }
         if (newDoctor != null)
         {
            this.doctor = newDoctor;
            this.doctor.addLeaveRequest(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Nurse getNurse() {
      return nurse;
   }
   
   /** @pdGenerated default parent setter
     * @param newNurse */
   public void setNurse(Nurse newNurse) {
      if (this.nurse == null || !this.nurse.equals(newNurse))
      {
         if (this.nurse != null)
         {
            Nurse oldNurse = this.nurse;
            this.nurse = null;
            oldNurse.removeLeaveRequest(this);
         }
         if (newNurse != null)
         {
            this.nurse = newNurse;
            this.nurse.addLeaveRequest(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public ClinicAdministrator getClinicAdministrator() {
      return clinicAdministrator;
   }
   
   /** @pdGenerated default parent setter
     * @param newClinicAdministrator */
   public void setClinicAdministrator(ClinicAdministrator newClinicAdministrator) {
      if (this.clinicAdministrator == null || !this.clinicAdministrator.equals(newClinicAdministrator))
      {
         if (this.clinicAdministrator != null)
         {
            ClinicAdministrator oldClinicAdministrator = this.clinicAdministrator;
            this.clinicAdministrator = null;
            oldClinicAdministrator.removeLeaveRequest(this);
         }
         if (newClinicAdministrator != null)
         {
            this.clinicAdministrator = newClinicAdministrator;
            this.clinicAdministrator.addLeaveRequest(this);
         }
      }
   }

}