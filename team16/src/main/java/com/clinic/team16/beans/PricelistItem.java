package com.clinic.team16.beans;
import java.util.*;
import javax.persistence.*;

@Entity
@Embeddable
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PricelistItem_ID", nullable = false)
	private long pricelistItemId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private double price;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Pricelist_ID")
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