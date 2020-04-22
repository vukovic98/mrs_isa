/***********************************************************************
 * Module:  Pacient.java
 * Author:  Vladimir
 * Purpose: Defines the Class Pacient
 ***********************************************************************/

import java.util.*;

/** @pdOid 53fc1dba-a4fc-42aa-8440-41d3f89dcde4 */
public class Pacient extends User {
   /** @pdRoleInfo migr=no name=Appointment assc=association12 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Appointment> appointment;
   /** @pdRoleInfo migr=no name=MedicalRecord assc=association18 mult=0..1 type=Composition */
   public MedicalRecord medicalRecord;
   
   
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
         newAppointment.setPacient(this);      
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
            oldAppointment.setPacient((Pacient)null);
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
            oldAppointment.setPacient((Pacient)null);
         }
      }
   }

}