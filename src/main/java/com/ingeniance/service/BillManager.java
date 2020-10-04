package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;

public interface BillManager {
	void addToBill(Product p, BigDecimal amount);

	void removeFromBill(Product p, BigDecimal amount);

	Map<Product, BigDecimal> getCheckoutList();

	Set<Discount> getDiscounts();

	void addToDiscounts(Discount d);

	Price getPrice();

}
