package com.ingeniance.test.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Discount.DiscountBuilder;
import com.ingeniance.model.Product;
import com.ingeniance.model.Product.ProductBuilder;
import com.ingeniance.service.BillManager;
import com.ingeniance.service.BillManagerImpl;
import com.ingeniance.test.utility.TestName;
import com.ingeniance.test.utility.TestPrice;

public class BillManagerTest {
	private BillManager billManager = new BillManagerImpl();

	Product fish, egg, secondEgg, potato, yoghurt;
	Discount discount1, discount2;

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
		potato = ProductBuilder.aProduct()
				.withName(TestName.POTATO.toName())
				.withPrice(TestPrice.SEVEN.getValue())
				.build();
		yoghurt = ProductBuilder.aProduct()
				.withName(TestName.YOGHURT.toName())
				.withPrice(TestPrice.TEN.getValue())
				.build();
		discount1 = DiscountBuilder.aDiscount()
				.forProductAmount(3)
				.withDiscountPrice(2f)
				.withDiscountProduct(egg)
				.build();
		discount2 = DiscountBuilder.aDiscount()
				.forProductAmount(1.5)
				.withDiscountPrice(6f)
				.withDiscountProduct(fish)
				.build();
	}

	@Test
	public void ShouldAddToBillWhenAdded() {
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		Map<Product, BigDecimal> map = new HashMap<Product, BigDecimal>() {
			{
				put(egg, BigDecimal.ONE);
				put(fish, BigDecimal.ONE);
			}
		};
		BigDecimal[] val = new BigDecimal[2];
		Arrays.fill(val, BigDecimal.ONE);
		assertEquals(map, billManager.getCheckoutList());
		assertEquals(new HashSet<Product>(Arrays.asList(egg, fish)), billManager.getCheckoutList()
				.keySet());
		assertArrayEquals(val, billManager.getCheckoutList()
				.values()
				.toArray());

	}

	@Test
	public void shouldUpdateValueInBillWhenAddedTwice() {
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		Map<Product, BigDecimal> map = new HashMap<Product, BigDecimal>() {
			{
				put(egg, BigDecimal.valueOf(2));
				put(fish, BigDecimal.valueOf(2));
			}
		};
		// then
		BigDecimal[] val = new BigDecimal[2];
		Arrays.fill(val, BigDecimal.valueOf(2));
		assertEquals(map, billManager.getCheckoutList());
		assertEquals(new HashSet<Product>(Arrays.asList(egg, fish)), billManager.getCheckoutList()
				.keySet());
		assertArrayEquals(val, billManager.getCheckoutList()
				.values()
				.toArray());
	}

	@Test
	public void shouldRemovePartialQuantityFromBillWhenRemoved() {
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		billManager.removeFromBill(egg, BigDecimal.ONE);
		Map<Product, BigDecimal> map = new HashMap<Product, BigDecimal>() {
			{
				put(egg, BigDecimal.valueOf(1));
				put(fish, BigDecimal.valueOf(2));
			}
		};
		assertEquals(map, billManager.getCheckoutList());

	}

	@Test
	public void shouldRemoveEntireProductFromBillWhenRemoved() {
		billManager.addToBill(egg, BigDecimal.ONE);
		billManager.addToBill(fish, BigDecimal.ONE);
		billManager.addToBill(egg, BigDecimal.TEN);
		billManager.addToBill(fish, BigDecimal.ONE);
		billManager.removeFromBill(egg, BigDecimal.valueOf(11));
		Map<Product, BigDecimal> map = new HashMap<Product, BigDecimal>() {
			{
				put(fish, BigDecimal.valueOf(2));
			}
		};
		assertEquals(map, billManager.getCheckoutList());
	}

	@Test
	public void ShouldAddToDiscountWhenAdded() {
		billManager.addToDiscounts(discount1);
		billManager.addToDiscounts(discount2);
		Set<Discount> discounts = new HashSet<Discount>(Arrays.asList(discount1, discount2));

		assertEquals(discounts, billManager.getDiscounts());
	}

	@Test
	public void ShouldCalculatePriceWithoutDiscountsProducts() {
		billManager.addToBill(potato, BigDecimal.ONE);
		billManager.addToBill(yoghurt, BigDecimal.valueOf(4));
		billManager.addToBill(potato, BigDecimal.valueOf(5));
		assertEquals(BigDecimal.valueOf(82f), billManager.getPrice()
				.getPrice());
	}
	@Test
	public void ShouldCalculatePriceWithDiscountsOneProduct() {
		billManager.addToBill(fish, BigDecimal.valueOf(4));
		billManager.addToDiscounts(discount2);
		assertTrue(BigDecimal.valueOf(17f).compareTo(billManager.getPrice()
				.getPrice())==0);
	}
	@Test
	public void ShouldCalculatePriceWithDiscountsProducts() {
		billManager.addToBill(potato, BigDecimal.ONE);
		billManager.addToBill(yoghurt, BigDecimal.valueOf(4));
		billManager.addToBill(potato, BigDecimal.valueOf(5));
		billManager.addToBill(egg, BigDecimal.valueOf(5));
		billManager.addToBill(fish, BigDecimal.valueOf(4));
		billManager.addToDiscounts(discount1);
		billManager.addToDiscounts(discount2);
		assertTrue(BigDecimal.valueOf(104f).compareTo(billManager.getPrice()
				.getPrice())==0);
	}
}
