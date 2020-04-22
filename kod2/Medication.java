/***********************************************************************
 * Module:  Medication.java
 * Author:  Vladimir
 * Purpose: Defines the Class Medication
 ***********************************************************************/

import java.util.*;

/** @pdOid 2c80b785-3131-4af4-a9cf-6b3d1fc37730 */
public class Medication {
   /** @pdOid b40b0ecd-6c4e-40d2-9b46-b8dc79ed95e6 */
   private String name;
   /** @pdOid 6c9e2678-32da-416c-80b4-dabf67637e9c */
   private String code;
   
   /** @pdRoleInfo migr=no name=MedicalReport assc=association23 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<MedicalReport> medicalReport;
   /** @pdRoleInfo migr=no name=ClinicalCenter assc=association20 mult=0..1 side=A */
   public ClinicalCenter clinicalCenter;
   
   
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
         this.medicalReport.add(newMedicalReport);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicalReport */
   public void removeMedicalReport(MedicalReport oldMedicalReport) {
      if (oldMedicalReport == null)
         return;
      if (this.medicalReport != null)
         if (this.medicalReport.contains(oldMedicalReport))
            this.medicalReport.remove(oldMedicalReport);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicalReport() {
      if (medicalReport != null)
         medicalReport.clear();
   }
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
            oldClinicalCenter.removeMedication(this);
         }
         if (newClinicalCenter != null)
         {
            this.clinicalCenter = newClinicalCenter;
            this.clinicalCenter.addMedication(this);
         }
      }
   }

}