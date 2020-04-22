/***********************************************************************
 * Module:  AppointmentType.java
 * Author:  Vladimir
 * Purpose: Defines the Class AppointmentType
 ***********************************************************************/

import java.util.*;

/** @pdOid 49daef1b-3efd-417a-91a0-ed0649ab9ed3 */
public class AppointmentType {
   /** @pdOid 9b1695fd-bbe6-426c-9346-f3be4df448af */
   private double discount;
   
   /** @pdRoleInfo migr=no name=PricelistItem assc=association11 mult=0..1 */
   public PricelistItem pricelistItem;
   /** @pdRoleInfo migr=no name=Appointment assc=association8 mult=0..* side=A */
   public Appointment[] appointment;

}