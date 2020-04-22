/***********************************************************************
 * Module:  ClinicalCenter.java
 * Author:  Vladimir
 * Purpose: Defines the Class ClinicalCenter
 ***********************************************************************/

import java.util.*;

/** @pdOid 501b8e92-ae66-454e-99bb-837ebcb5b3d7 */
public class ClinicalCenter {
   /** @pdOid 89d83853-2948-4756-9fd3-130c4d2cd45b */
   private String name;
   
   /** @pdRoleInfo migr=no name=Clinic assc=association2 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Clinic> clinic;
   /** @pdRoleInfo migr=no name=Medication assc=association20 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Medication> medication;
   /** @pdRoleInfo migr=no name=Diagnosis assc=association21 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Diagnosis> diagnosis;
   /** @pdRoleInfo migr=no name=ClinicalCenterAdministrator assc=association1 mult=0..* side=A */
   public ClinicalCenterAdministrator[] clinicalCenterAdministrator;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Clinic> getClinic() {
      if (clinic == null)
         clinic = new java.util.HashSet<Clinic>();
      return clinic;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorClinic() {
      if (clinic == null)
         clinic = new java.util.HashSet<Clinic>();
      return clinic.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newClinic */
   public void setClinic(java.util.Collection<Clinic> newClinic) {
      removeAllClinic();
      for (java.util.Iterator iter = newClinic.iterator(); iter.hasNext();)
         addClinic((Clinic)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newClinic */
   public void addClinic(Clinic newClinic) {
      if (newClinic == null)
         return;
      if (this.clinic == null)
         this.clinic = new java.util.HashSet<Clinic>();
      if (!this.clinic.contains(newClinic))
         this.clinic.add(newClinic);
   }
   
   /** @pdGenerated default remove
     * @param oldClinic */
   public void removeClinic(Clinic oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinic != null)
         if (this.clinic.contains(oldClinic))
            this.clinic.remove(oldClinic);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllClinic() {
      if (clinic != null)
         clinic.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Medication> getMedication() {
      if (medication == null)
         medication = new java.util.HashSet<Medication>();
      return medication;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedication() {
      if (medication == null)
         medication = new java.util.HashSet<Medication>();
      return medication.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedication */
   public void setMedication(java.util.Collection<Medication> newMedication) {
      removeAllMedication();
      for (java.util.Iterator iter = newMedication.iterator(); iter.hasNext();)
         addMedication((Medication)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedication */
   public void addMedication(Medication newMedication) {
      if (newMedication == null)
         return;
      if (this.medication == null)
         this.medication = new java.util.HashSet<Medication>();
      if (!this.medication.contains(newMedication))
      {
         this.medication.add(newMedication);
         newMedication.setClinicalCenter(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldMedication */
   public void removeMedication(Medication oldMedication) {
      if (oldMedication == null)
         return;
      if (this.medication != null)
         if (this.medication.contains(oldMedication))
         {
            this.medication.remove(oldMedication);
            oldMedication.setClinicalCenter((ClinicalCenter)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedication() {
      if (medication != null)
      {
         Medication oldMedication;
         for (java.util.Iterator iter = getIteratorMedication(); iter.hasNext();)
         {
            oldMedication = (Medication)iter.next();
            iter.remove();
            oldMedication.setClinicalCenter((ClinicalCenter)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Diagnosis> getDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.HashSet<Diagnosis>();
      return diagnosis;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.HashSet<Diagnosis>();
      return diagnosis.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDiagnosis */
   public void setDiagnosis(java.util.Collection<Diagnosis> newDiagnosis) {
      removeAllDiagnosis();
      for (java.util.Iterator iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((Diagnosis)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDiagnosis */
   public void addDiagnosis(Diagnosis newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosis == null)
         this.diagnosis = new java.util.HashSet<Diagnosis>();
      if (!this.diagnosis.contains(newDiagnosis))
      {
         this.diagnosis.add(newDiagnosis);
         newDiagnosis.setClinicalCenter(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDiagnosis */
   public void removeDiagnosis(Diagnosis oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosis != null)
         if (this.diagnosis.contains(oldDiagnosis))
         {
            this.diagnosis.remove(oldDiagnosis);
            oldDiagnosis.setClinicalCenter((ClinicalCenter)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDiagnosis() {
      if (diagnosis != null)
      {
         Diagnosis oldDiagnosis;
         for (java.util.Iterator iter = getIteratorDiagnosis(); iter.hasNext();)
         {
            oldDiagnosis = (Diagnosis)iter.next();
            iter.remove();
            oldDiagnosis.setClinicalCenter((ClinicalCenter)null);
         }
      }
   }

}