package com.clinic.team16.beans;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pricelist_ID", nullable = false)
	private long pricelistId;
	
	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "pricelist_pricelisItems", joinColumns = @JoinColumn(name = "pricelist_id"))
	public List<PricelistItem> pricelistItems;

	@JsonIgnore
	@ElementCollection
	@CollectionTable(name = "pricelist_clinics", joinColumns = @JoinColumn(name = "pricelist_id"))
	public List<Clinic> clinics;

	public Pricelist() {
		super();
	}

	public Pricelist(ArrayList<PricelistItem> pricelistItems, ArrayList<Clinic> clinics) {
		super();
		this.pricelistItems = pricelistItems;
		this.clinics = clinics;
	}

	public List<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(ArrayList<Clinic> clinics) {
		this.clinics = clinics;
	}

	public long getPricelistId() {
		return pricelistId;
	}

	public void setPricelistId(long pricelistId) {
		this.pricelistId = pricelistId;
	}

	public List<PricelistItem> getPricelistItems() {
		return pricelistItems;
	}

	public void setPricelistItems(List<PricelistItem> pricelistItems) {
		this.pricelistItems = pricelistItems;
	}

	

}