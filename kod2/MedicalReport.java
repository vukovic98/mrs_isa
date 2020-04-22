/***********************************************************************
 * Module:  MedicalReport.java
 * Author:  Vladimir
 * Purpose: Defines the Class MedicalReport
 ***********************************************************************/

import java.util.*;

/** @pdOid 67a67cd8-ac67-4058-8a76-c0340a911bd6 */
public class MedicalReport {
   /** @pdOid 30e367cc-9ace-40ad-a769-39e9e6d173d6 */
   private String details;
   /** @pdOid fc73917a-4e10-46f1-b757-494f5b6ebd6d */
   private Boolean approved;
   
   /** @pdRoleInfo migr=no name=Diagnosis assc=association22 mult=0..* side=A */
   public Diagnosis[] diagnosis;
   /** @pdRoleInfo migr=no name=Medication assc=association23 mult=0..* side=A */
   public Medication[] medication;
   /** @pdRoleInfo migr=no name=Nurse assc=association24 mult=1..1 side=A */
   public Nurse nurse;
   
   
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
            oldNurse.removeMedicalReport(this);
         }
         if (newNurse != null)
         {
            this.nurse = newNurse;
            this.nurse.addMedicalReport(this);
         }
      }
   }

}