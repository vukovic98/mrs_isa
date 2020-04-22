/***********************************************************************
 * Module:  RegistrationRequest.java
 * Author:  Vladimir
 * Purpose: Defines the Class RegistrationRequest
 ***********************************************************************/

import java.util.*;

/** @pdOid b928ee34-d396-4710-86e7-56ff8913523c */
public class RegistrationRequest {
   /** @pdOid e5fe49ad-283d-4c67-809d-c87716489258 */
   private boolean approved;
   
   /** @pdRoleInfo migr=no name=User assc=association13 mult=1..1 side=A */
   public User user;
   /** @pdRoleInfo migr=no name=ClinicalCenterAdministrator assc=association14 mult=1..1 side=A */
   public ClinicalCenterAdministrator clinicalCenterAdministrator;
   
   
   /** @pdGenerated default parent getter */
   public ClinicalCenterAdministrator getClinicalCenterAdministrator() {
      return clinicalCenterAdministrator;
   }
   
   /** @pdGenerated default parent setter
     * @param newClinicalCenterAdministrator */
   public void setClinicalCenterAdministrator(ClinicalCenterAdministrator newClinicalCenterAdministrator) {
      if (this.clinicalCenterAdministrator == null || !this.clinicalCenterAdministrator.equals(newClinicalCenterAdministrator))
      {
         if (this.clinicalCenterAdministrator != null)
         {
            ClinicalCenterAdministrator oldClinicalCenterAdministrator = this.clinicalCenterAdministrator;
            this.clinicalCenterAdministrator = null;
            oldClinicalCenterAdministrator.removeRegistrationRequest(this);
         }
         if (newClinicalCenterAdministrator != null)
         {
            this.clinicalCenterAdministrator = newClinicalCenterAdministrator;
            this.clinicalCenterAdministrator.addRegistrationRequest(this);
         }
      }
   }

}