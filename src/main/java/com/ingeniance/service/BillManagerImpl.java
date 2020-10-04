package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;

public class BillManagerImpl implements BillManager {
	private Map<Product, BigDecimal> checkoutList = new HashMap<Product, BigDecimal>();
	private Set<Discount> discounts = new HashSet<Discount>();
	private PriceManager priceManager = new PriceManagerImpl();

	@Override
	public void addToBill(Product p, BigDecimal amount) {
		checkoutList.computeIfPresent(p, (existedProduct, amountToAdd) ->
			{
				return amountToAdd.add(amount);
			});
		checkoutList.putIfAbsent(p, amount);
	}

	@Override
	public void removeFromBill(Product p, BigDecimal amount) {
		checkoutList.computeIfPresent(p, (existedProduct, amountToAdd) ->
			{
				BigDecimal sum = amountToAdd.subtract(amount);
				return (sum.compareTo(BigDecimal.ZERO) == 1) ? sum
						: sum.compareTo(BigDecimal.ZERO) == 0 ? checkoutList.remove(existedProduct) : BigDecimal.ZERO;
			});
	}

	@Override
	public Map<Product, BigDecimal> getCheckoutList() {
		return checkoutList;
	}

	public void setCheckoutList(Map<Product, BigDecimal> checkoutList) {
		this.checkoutList = checkoutList;
	}

	@Override
	public Price getPrice() {
		final Price price = new Price(BigDecimal.ZERO);
		checkoutList.keySet()
				.stream()
				.forEach((currentProduct) ->
					{
						Optional<Discount> productDiscount = discounts.stream()
								.filter((discount) -> discount.getDiscountProduct()
										.getProductName()
										.equals(currentProduct.getProductName()))
								.findAny();
						price.add(priceManager.calculatePrice(currentProduct, checkoutList.get(currentProduct),
								productDiscount));
					});
		return price;
	}

	@Override
	public Set<Discount> getDiscounts() {
		return discounts;
	}

	@Override
	public void addToDiscounts(Discount d) {
		discounts.add(d);
	}
}
