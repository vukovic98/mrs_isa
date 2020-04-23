package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

@Entity
public class Pricelist {
	
	@ElementCollection
	@CollectionTable(name = "pricelist_pricelisItems", joinColumns = @JoinColumn(name = "pricelist_id"))
	public ArrayList<PricelistItem> pricelistItems;

	@ElementCollection
	@CollectionTable(name = "pricelist_clinics", joinColumns = @JoinColumn(name = "pricelist_id"))
	public ArrayList<Clinic> clinics;

	public Pricelist() {
		super();
	}

	public Pricelist(ArrayList<PricelistItem> pricelistItems, ArrayList<Clinic> clinics) {
		super();
		this.pricelistItems = pricelistItems;
		this.clinics = clinics;
	}

	public ArrayList<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(ArrayList<Clinic> clinics) {
		this.clinics = clinics;
	}

	public ArrayList<PricelistItem> getPricelistItem() {
		if (pricelistItems == null)
			pricelistItems = new ArrayList<PricelistItem>();
		return pricelistItems;
	}

	public java.util.Iterator getIteratorPricelistItem() {
		if (pricelistItems == null)
			pricelistItems = new ArrayList<PricelistItem>();
		return pricelistItems.iterator();
	}

	public void setPricelistItem(ArrayList<PricelistItem> newPricelistItem) {
		removeAllPricelistItem();
		for (java.util.Iterator iter = newPricelistItem.iterator(); iter.hasNext();)
			addPricelistItem((PricelistItem) iter.next());
	}

	public void addPricelistItem(PricelistItem newPricelistItem) {
		if (newPricelistItem == null)
			return;
		if (this.pricelistItems == null)
			this.pricelistItems = new ArrayList<PricelistItem>();
		if (!this.pricelistItems.contains(newPricelistItem)) {
			this.pricelistItems.add(newPricelistItem);
			newPricelistItem.setPricelist(this);
		}
	}

	public void removePricelistItem(PricelistItem oldPricelistItem) {
		if (oldPricelistItem == null)
			return;
		if (this.pricelistItems != null)
			if (this.pricelistItems.contains(oldPricelistItem)) {
				this.pricelistItems.remove(oldPricelistItem);
				oldPricelistItem.setPricelist((Pricelist) null);
			}
	}

	public void removeAllPricelistItem() {
		if (pricelistItems != null) {
			PricelistItem oldPricelistItem;
			for (java.util.Iterator iter = getIteratorPricelistItem(); iter.hasNext();) {
				oldPricelistItem = (PricelistItem) iter.next();
				iter.remove();
				oldPricelistItem.setPricelist((Pricelist) null);
			}
		}
	}

}