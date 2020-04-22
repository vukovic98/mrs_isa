/***********************************************************************
 * Module:  Clinic.java
 * Author:  Vladimir
 * Purpose: Defines the Class Clinic
 ***********************************************************************/

import java.util.*;

/** @pdOid 482ebf0d-033d-47f5-84d8-3fc58c9b7784 */
public class Clinic {
   /** @pdOid 251b38bf-ec36-4e32-b96f-476bacf1987b */
   private String name;
   /** @pdOid 05b2d21a-c526-4e5b-b4d2-ca06d76cb48d */
   private String address;
   /** @pdOid 249b3a57-4454-414d-a03e-4b17973a10ec */
   private String description;
   
   /** @pdRoleInfo migr=no name=Ordination assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Ordination> ordination;
   /** @pdRoleInfo migr=no name=Pricelist assc=association9 mult=0..1 */
   public Pricelist pricelist;
   /** @pdRoleInfo migr=no name=ClinicAdministrator assc=association3 mult=0..* side=A */
   public ClinicAdministrator[] clinicAdministrator;
   /** @pdRoleInfo migr=no name=Doctor assc=association4 mult=0..* side=A */
   public Doctor[] doctor;
   /** @pdRoleInfo migr=no name=Nurse assc=association25 mult=0..* side=A */
   public Nurse[] nurse;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Ordination> getOrdination() {
      if (ordination == null)
         ordination = new java.util.HashSet<Ordination>();
      return ordination;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOrdination() {
      if (ordination == null)
         ordination = new java.util.HashSet<Ordination>();
      return ordination.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOrdination */
   public void setOrdination(java.util.Collection<Ordination> newOrdination) {
      removeAllOrdination();
      for (java.util.Iterator iter = newOrdination.iterator(); iter.hasNext();)
         addOrdination((Ordination)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOrdination */
   public void addOrdination(Ordination newOrdination) {
      if (newOrdination == null)
         return;
      if (this.ordination == null)
         this.ordination = new java.util.HashSet<Ordination>();
      if (!this.ordination.contains(newOrdination))
      {
         this.ordination.add(newOrdination);
         newOrdination.setClinic(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldOrdination */
   public void removeOrdination(Ordination oldOrdination) {
      if (oldOrdination == null)
         return;
      if (this.ordination != null)
         if (this.ordination.contains(oldOrdination))
         {
            this.ordination.remove(oldOrdination);
            oldOrdination.setClinic((Clinic)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOrdination() {
      if (ordination != null)
      {
         Ordination oldOrdination;
         for (java.util.Iterator iter = getIteratorOrdination(); iter.hasNext();)
         {
            oldOrdination = (Ordination)iter.next();
            iter.remove();
            oldOrdination.setClinic((Clinic)null);
         }
      }
   }

}