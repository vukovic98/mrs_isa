
/***********************************************************************
 * Module:  PricelistItem.java
 * Author:  Vladimir
 * Purpose: Defines the Class PricelistItem
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

public class PricelistItem {

	private String name;

	private double price;

	public Pricelist pricelist;

	public PricelistItem() {
		super();
	}

	public PricelistItem(String name, double price, Pricelist pricelist) {
		super();
		this.name = name;
		this.price = price;
		this.pricelist = pricelist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist newPricelist) {
		if (this.pricelist == null || !this.pricelist.equals(newPricelist)) {
			if (this.pricelist != null) {
				Pricelist oldPricelist = this.pricelist;
				this.pricelist = null;
				oldPricelist.removePricelistItem(this);
			}
			if (newPricelist != null) {
				this.pricelist = newPricelist;
				this.pricelist.addPricelistItem(this);
			}
		}
	}

}