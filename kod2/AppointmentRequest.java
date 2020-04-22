/***********************************************************************
 * Module:  AppointmentRequest.java
 * Author:  Vladimir
 * Purpose: Defines the Class AppointmentRequest
 ***********************************************************************/

import java.util.*;

/** @pdOid 72500e41-329d-40b8-845a-625d7a7fc8ed */
public class AppointmentRequest {
   /** @pdOid f3c61937-d9f1-4e73-88a0-074e99b41ac0 */
   private Boolean approved;
   /** @pdOid f47c0684-ecce-4505-897a-5081c90f0ff9 */
   private Date requestDate;
   
   /** @pdRoleInfo migr=no name=ClinicalCenterAdministrator assc=association27 mult=0..1 */
   public ClinicalCenterAdministrator clinicalCenterAdministrator;
   /** @pdRoleInfo migr=no name=Appointment assc=association26 mult=1..1 side=A */
   public Appointment appointment;

}