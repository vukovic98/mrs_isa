/***********************************************************************
 * Module:  Pricelist.java
 * Author:  Vladimir
 * Purpose: Defines the Class Pricelist
 ***********************************************************************/

import java.util.*;

/** @pdOid e6232eda-3936-4efc-9d56-c1b2a38111a6 */
public class Pricelist {
   /** @pdRoleInfo migr=no name=PricelistItem assc=association10 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<PricelistItem> pricelistItem;
   /** @pdRoleInfo migr=no name=Clinic assc=association9 mult=0..* side=A */
   public Clinic[] clinic;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<PricelistItem> getPricelistItem() {
      if (pricelistItem == null)
         pricelistItem = new java.util.HashSet<PricelistItem>();
      return pricelistItem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPricelistItem() {
      if (pricelistItem == null)
         pricelistItem = new java.util.HashSet<PricelistItem>();
      return pricelistItem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPricelistItem */
   public void setPricelistItem(java.util.Collection<PricelistItem> newPricelistItem) {
      removeAllPricelistItem();
      for (java.util.Iterator iter = newPricelistItem.iterator(); iter.hasNext();)
         addPricelistItem((PricelistItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPricelistItem */
   public void addPricelistItem(PricelistItem newPricelistItem) {
      if (newPricelistItem == null)
         return;
      if (this.pricelistItem == null)
         this.pricelistItem = new java.util.HashSet<PricelistItem>();
      if (!this.pricelistItem.contains(newPricelistItem))
      {
         this.pricelistItem.add(newPricelistItem);
         newPricelistItem.setPricelist(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPricelistItem */
   public void removePricelistItem(PricelistItem oldPricelistItem) {
      if (oldPricelistItem == null)
         return;
      if (this.pricelistItem != null)
         if (this.pricelistItem.contains(oldPricelistItem))
         {
            this.pricelistItem.remove(oldPricelistItem);
            oldPricelistItem.setPricelist((Pricelist)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPricelistItem() {
      if (pricelistItem != null)
      {
         PricelistItem oldPricelistItem;
         for (java.util.Iterator iter = getIteratorPricelistItem(); iter.hasNext();)
         {
            oldPricelistItem = (PricelistItem)iter.next();
            iter.remove();
            oldPricelistItem.setPricelist((Pricelist)null);
         }
      }
   }

}