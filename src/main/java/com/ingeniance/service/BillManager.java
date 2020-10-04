package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;
/**
 * This interface defines the bill service
 * where we define the required methods to add/remove 
 * from the bill and calculate the total of bought items
 * @author binebli
 *
 */
public interface BillManager {
	void addToBill(Product p, BigDecimal amount);

	void removeFromBill(Product p, BigDecimal amount);
	
	void addToDiscounts(Discount d);

	Map<Product, BigDecimal> getCheckoutList();

	Set<Discount> getDiscounts();


	Price getPrice();

}
