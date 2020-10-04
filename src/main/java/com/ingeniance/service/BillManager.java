package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Map;

import com.ingeniance.model.Product;

public interface BillManager {
	void addToBill(Product p,BigDecimal amount);
	void removeFromBill(Product p, BigDecimal amount);
	Map<Product, BigDecimal> getCheckoutList();
}
