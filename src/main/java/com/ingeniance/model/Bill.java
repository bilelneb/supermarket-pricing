package com.ingeniance.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bill {
	private Map<Product, Double> checkoutList = new LinkedHashMap<Product, Double>();

	public Map<Product, Double> getCard() {
		return checkoutList;
	}

	public void setCard(Map<Product, Double> card) {
		this.checkoutList = card;
	}

}
