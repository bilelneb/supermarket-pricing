package com.ingeniance.test.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.ingeniance.model.Price;

public class PriceTest {
	@Test
	public void twoPricesShouldBeAdded() throws Exception {
		Price price = new Price(10);
		Price otherPrice = new Price(12);
		assertEquals(price.add(otherPrice)
				.getPrice(), BigDecimal.valueOf(22f));
	}

	@Test
	public void amountShouldBeMultipliedToPrice() throws Exception {
		Price price = new Price(10);
		int amount = 4;
		assertEquals(BigDecimal.valueOf(40f),price.multiply(amount)
				.getPrice() );
	}
}
