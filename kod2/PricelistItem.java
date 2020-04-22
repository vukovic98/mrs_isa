/***********************************************************************
 * Module:  PricelistItem.java
 * Author:  Vladimir
 * Purpose: Defines the Class PricelistItem
 ***********************************************************************/

import java.util.*;

/** @pdOid 8f269288-6e71-4639-9746-942bd6d08991 */
public class PricelistItem {
   /** @pdOid 70338fee-ade7-49f7-abbf-b862725f7694 */
   private String name;
   /** @pdOid f637588b-87c2-4fd6-9c9e-26db73f32dd2 */
   private double price;
   
   /** @pdRoleInfo migr=no name=Pricelist assc=association10 mult=0..1 side=A */
   public Pricelist pricelist;
   /** @pdRoleInfo migr=no name=AppointmentType assc=association11 mult=0..* side=A */
   public AppointmentType[] appointmentType;
   
   
   /** @pdGenerated default parent getter */
   public Pricelist getPricelist() {
      return pricelist;
   }
   
   /** @pdGenerated default parent setter
     * @param newPricelist */
   public void setPricelist(Pricelist newPricelist) {
      if (this.pricelist == null || !this.pricelist.equals(newPricelist))
      {
         if (this.pricelist != null)
         {
            Pricelist oldPricelist = this.pricelist;
            this.pricelist = null;
            oldPricelist.removePricelistItem(this);
         }
         if (newPricelist != null)
         {
            this.pricelist = newPricelist;
            this.pricelist.addPricelistItem(this);
         }
      }
   }

}