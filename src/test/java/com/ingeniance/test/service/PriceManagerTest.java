package com.ingeniance.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Discount.DiscountBuilder;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;
import com.ingeniance.model.Product.ProductBuilder;
import com.ingeniance.service.PriceManager;
import com.ingeniance.service.PriceManagerImpl;
import com.ingeniance.test.utility.TestName;
import com.ingeniance.test.utility.TestPrice;

public class PriceManagerTest {
	private PriceManager priceManager = new PriceManagerImpl();
	Product fish, egg, secondEgg;

	@Before
	public void setUp() throws Exception {
		egg = ProductBuilder.aProduct()
				.withName(TestName.EGGS.toName())
				.withPrice(TestPrice.ONEHALF.getValue())
				.build();
		secondEgg = ProductBuilder.aProduct()
				.withName(TestName.EGGS.toName())
				.withPrice(TestPrice.ONEHALF.getValue())
				.build();
		fish = ProductBuilder.aProduct()
				.withName(TestName.FISH.toName())
				.withPrice(TestPrice.FIVE.getValue())
				.build();

	}

	@Test
	public void shouldCalculatePriceForOneProductAndWithoutDiscount() {
		Price p = priceManager.calculatePrice(egg, BigDecimal.valueOf(2), Optional.ofNullable(null));
		assertEquals(new Price(BigDecimal.valueOf(3f)),p);
	}

	@Test
	public void shouldCalculatePriceForOneProductAndWhenDiscountNotVerified() {
		Discount d = DiscountBuilder.aDiscount()
				.forProductAmount(3)
				.withDiscountPrice(4)
				.withDiscountProduct(egg)
				.build();
		Price p = priceManager.calculatePrice(egg, BigDecimal.valueOf(2), Optional.of(d));
		assertEquals(new Price(BigDecimal.valueOf(3f)),p);
	}

	@Test
	public void shouldCalculatePriceForOneProductAndWhenDiscountIsVerified() {
		Discount d = DiscountBuilder.aDiscount()
				.forProductAmount(3)
				.withDiscountPrice(4)
				.withDiscountProduct(egg)
				.build();
		Price p = priceManager.calculatePrice(egg, BigDecimal.valueOf(8), Optional.of(d));
		assertTrue(p.getPrice()
				.compareTo(BigDecimal.valueOf(11f)) == 0);
	}

}
