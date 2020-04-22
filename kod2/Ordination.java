/***********************************************************************
 * Module:  Ordination.java
 * Author:  Vladimir
 * Purpose: Defines the Class Ordination
 ***********************************************************************/

import java.util.*;

/** @pdOid 500af6f4-16c4-4acf-80ae-856e1e38fc0c */
public class Ordination {
   /** @pdOid ac3127c3-80e0-41e8-9396-a21de1d7e3b7 */
   private int number;
   /** @pdOid acf29409-f76d-401c-922b-cf5a60e9fdd6 */
   private OrdinationType type;
   /** @pdOid 350954c2-436c-49cd-8d25-f1c06a127ba6 */
   private String name;
   
   /** @pdRoleInfo migr=no name=Clinic assc=association5 mult=1..1 side=A */
   public Clinic clinic;
   /** @pdRoleInfo migr=no name=Appointment assc=association6 mult=0..* side=A */
   public Appointment[] appointment;
   
   
   /** @pdGenerated default parent getter */
   public Clinic getClinic() {
      return clinic;
   }
   
   /** @pdGenerated default parent setter
     * @param newClinic */
   public void setClinic(Clinic newClinic) {
      if (this.clinic == null || !this.clinic.equals(newClinic))
      {
         if (this.clinic != null)
         {
            Clinic oldClinic = this.clinic;
            this.clinic = null;
            oldClinic.removeOrdination(this);
         }
         if (newClinic != null)
         {
            this.clinic = newClinic;
            this.clinic.addOrdination(this);
         }
      }
   }

}