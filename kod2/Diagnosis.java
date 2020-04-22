/***********************************************************************
 * Module:  Diagnosis.java
 * Author:  Vladimir
 * Purpose: Defines the Class Diagnosis
 ***********************************************************************/

import java.util.*;

/** @pdOid b6f41180-fe27-44cd-8a38-2f7630416aac */
public class Diagnosis {
   /** @pdOid 1f98b9e7-49ae-4961-a736-aa7f3e222dd1 */
   private String name;
   /** @pdOid a4345557-08e3-4788-b106-46fe2cf27e91 */
   private String code;
   
   /** @pdRoleInfo migr=no name=MedicalReport assc=association22 mult=0..* */
   public MedicalReport[] medicalReport;
   /** @pdRoleInfo migr=no name=ClinicalCenter assc=association21 mult=0..1 side=A */
   public ClinicalCenter clinicalCenter;
   
   
   /** @pdGenerated default parent getter */
   public ClinicalCenter getClinicalCenter() {
      return clinicalCenter;
   }
   
   /** @pdGenerated default parent setter
     * @param newClinicalCenter */
   public void setClinicalCenter(ClinicalCenter newClinicalCenter) {
      if (this.clinicalCenter == null || !this.clinicalCenter.equals(newClinicalCenter))
      {
         if (this.clinicalCenter != null)
         {
            ClinicalCenter oldClinicalCenter = this.clinicalCenter;
            this.clinicalCenter = null;
            oldClinicalCenter.removeDiagnosis(this);
         }
         if (newClinicalCenter != null)
         {
            this.clinicalCenter = newClinicalCenter;
            this.clinicalCenter.addDiagnosis(this);
         }
      }
   }

}