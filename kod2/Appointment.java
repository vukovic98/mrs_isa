/***********************************************************************
 * Module:  Appointment.java
 * Author:  Vladimir
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import java.util.*;

/** @pdOid 03285dcb-e00c-45f3-b11c-ac4c1848adb6 */
public class Appointment {
   /** @pdOid ec82cd82-8f02-435c-b1ad-1f5e180becb7 */
   private Date dateTime;
   /** @pdOid 92261650-ebf7-4473-bf31-388a96893ec6 */
   private double duration;
   
   /** @pdRoleInfo migr=no name=Ordination assc=association6 mult=0..1 */
   public Ordination ordination;
   /** @pdRoleInfo migr=no name=AppointmentType assc=association8 mult=0..1 */
   public AppointmentType appointmentType;
   /** @pdRoleInfo migr=no name=MedicalReport assc=association19 mult=0..1 type=Composition */
   public MedicalReport medicalReport;
   /** @pdRoleInfo migr=no name=AppointmentRequest assc=association26 mult=0..1 */
   public AppointmentRequest appointmentRequest;
   /** @pdRoleInfo migr=no name=Doctor assc=association7 mult=1..1 side=A */
   public Doctor doctor;
   /** @pdRoleInfo migr=no name=Pacient assc=association12 mult=0..1 side=A */
   public Pacient pacient;
   
   
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
            oldDoctor.removeAppointment(this);
         }
         if (newDoctor != null)
         {
            this.doctor = newDoctor;
            this.doctor.addAppointment(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Pacient getPacient() {
      return pacient;
   }
   
   /** @pdGenerated default parent setter
     * @param newPacient */
   public void setPacient(Pacient newPacient) {
      if (this.pacient == null || !this.pacient.equals(newPacient))
      {
         if (this.pacient != null)
         {
            Pacient oldPacient = this.pacient;
            this.pacient = null;
            oldPacient.removeAppointment(this);
         }
         if (newPacient != null)
         {
            this.pacient = newPacient;
            this.pacient.addAppointment(this);
         }
      }
   }

}