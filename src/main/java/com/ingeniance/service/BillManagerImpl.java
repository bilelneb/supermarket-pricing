package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ingeniance.model.Product;

public class BillManagerImpl implements BillManager {
	private Map<Product, BigDecimal> checkoutList = new HashMap<Product, BigDecimal>();

	@Override
	public void addToBill(Product p, BigDecimal amount) {
		checkoutList.computeIfPresent(p, (existedProduct, amountToAdd) -> {
			return amountToAdd.add(amount);
		});
		checkoutList.putIfAbsent(p, amount);
	}

	@Override
	public void removeFromBill(Product p, BigDecimal amount) {
		checkoutList.computeIfPresent(p, (existedProduct, amountToAdd) -> {
			BigDecimal sum = amountToAdd.subtract(amount);
			return (sum.compareTo(BigDecimal.ZERO) == 1) ? sum
					: sum.compareTo(BigDecimal.ZERO) == 0 ? 
							checkoutList.remove(existedProduct)
							: BigDecimal.ZERO;
		});
	}

	@Override
	public Map<Product, BigDecimal> getCheckoutList() {
		return checkoutList;
	}

	public void setCheckoutList(Map<Product, BigDecimal> checkoutList) {
		this.checkoutList = checkoutList;
	}

}
