package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;
/**
 * This class is the implementation of the price service
 * @author binebli
 *
 */
public class PriceManagerImpl implements PriceManager {

	@Override
	public Price calculatePrice(Product p, BigDecimal amount, Optional<Discount> d) {
		if (d.isPresent()) {
			return calculateWithDiscount(p, amount, d.get());
		} else {
			return calculateWithoutDiscount(p, amount);
		}
	}

	private Price calculateWithDiscount(Product p, BigDecimal amount, Discount d) {
		BigDecimal reductionRequiredAmount = BigDecimal.valueOf(d.getAmount());
		BigDecimal[] numberOfPackagesToCalculate = amount.divideAndRemainder(reductionRequiredAmount);
		BigDecimal reductionValue = d.getDiscountPrice().getPrice();
		BigDecimal reducedPrice = numberOfPackagesToCalculate[0]
				.multiply(reductionValue);
		BigDecimal unreducedPrice = calculateWithoutDiscount(p, numberOfPackagesToCalculate[1]).getPrice();
		return new Price(reducedPrice.add(unreducedPrice));
	}

	private Price calculateWithoutDiscount(Product p, BigDecimal amount) {
		return new Price(p.getUnitPrice()
				.getPrice()
				.multiply(amount));
	}
}
