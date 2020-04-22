/***********************************************************************
 * Module:  ClinicalCenterAdministrator.java
 * Author:  Vladimir
 * Purpose: Defines the Class ClinicalCenterAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid d5cdcfe9-916f-49e2-ad19-5864bfb6d0e1 */
public class ClinicalCenterAdministrator extends User {
   /** @pdRoleInfo migr=no name=ClinicalCenter assc=association1 mult=1..1 */
   public ClinicalCenter clinicalCenter;
   /** @pdRoleInfo migr=no name=RegistrationRequest assc=association14 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<RegistrationRequest> registrationRequest;
   /** @pdRoleInfo migr=no name=AppointmentRequest assc=association27 mult=0..* side=A */
   public AppointmentRequest[] appointmentRequest;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<RegistrationRequest> getRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new java.util.HashSet<RegistrationRequest>();
      return registrationRequest;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new java.util.HashSet<RegistrationRequest>();
      return registrationRequest.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRegistrationRequest */
   public void setRegistrationRequest(java.util.Collection<RegistrationRequest> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (java.util.Iterator iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequest)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRegistrationRequest */
   public void addRegistrationRequest(RegistrationRequest newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequest == null)
         this.registrationRequest = new java.util.HashSet<RegistrationRequest>();
      if (!this.registrationRequest.contains(newRegistrationRequest))
      {
         this.registrationRequest.add(newRegistrationRequest);
         newRegistrationRequest.setClinicalCenterAdministrator(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRegistrationRequest */
   public void removeRegistrationRequest(RegistrationRequest oldRegistrationRequest) {
      if (oldRegistrationRequest == null)
         return;
      if (this.registrationRequest != null)
         if (this.registrationRequest.contains(oldRegistrationRequest))
         {
            this.registrationRequest.remove(oldRegistrationRequest);
            oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRegistrationRequest() {
      if (registrationRequest != null)
      {
         RegistrationRequest oldRegistrationRequest;
         for (java.util.Iterator iter = getIteratorRegistrationRequest(); iter.hasNext();)
         {
            oldRegistrationRequest = (RegistrationRequest)iter.next();
            iter.remove();
            oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator)null);
         }
      }
   }

}