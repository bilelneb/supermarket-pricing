package com.ingeniance.model;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * This entity is a POJO  representing a Bill
 * a bill is the list of bought products with their amount
 * @author binebli
 * @version 1.0
 */
public class Bill {
	/**
	 * This attribute is a list responsible for storing the bought 
	 * products with their amounts
	 */
	private Map<Product, Double> checkoutList = new LinkedHashMap<Product, Double>();

	public Map<Product, Double> getCard() {
		return checkoutList;
	}

	public void setCard(Map<Product, Double> card) {
		this.checkoutList = card;
	}

}
