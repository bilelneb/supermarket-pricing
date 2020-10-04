package com.ingeniance.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.Before;
import org.junit.Test;

import com.ingeniance.model.Product;
import com.ingeniance.model.Product.ProductBuilder;
import com.ingeniance.service.BillManager;
import com.ingeniance.service.BillManagerImpl;
import com.ingeniance.test.utility.TestName;
import com.ingeniance.test.utility.TestPrice;


public class BillManagerTest {
	private BillManager billManager = new BillManagerImpl();
	Product fish, egg, secondEgg;

	@Before
	public void setUp() throws Exception {
		egg = ProductBuilder.aProduct().withName(TestName.EGGS.toName()).withPrice(TestPrice.ONEHALF.getValue())
				.build();
		secondEgg = ProductBuilder.aProduct().withName(TestName.EGGS.toName()).withPrice(TestPrice.ONEHALF.getValue())
				.build();
		fish = ProductBuilder.aProduct().withName(TestName.FISH.toName()).withPrice(TestPrice.FIVE.getValue()).build();

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
		assertEquals(billManager.getCheckoutList(), map);
		assertEquals(billManager.getCheckoutList().keySet(), new HashSet<Product>(Arrays.asList(egg, fish)));
		assertArrayEquals(billManager.getCheckoutList().values().toArray(), val);
		
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
        //then
		BigDecimal[] val = new BigDecimal[2];
		Arrays.fill(val, BigDecimal.valueOf(2));
		assertEquals(billManager.getCheckoutList(), map);
		assertEquals(billManager.getCheckoutList().keySet(), new HashSet<Product>(Arrays.asList(egg, fish)));
		assertArrayEquals(billManager.getCheckoutList().values().toArray(), val);
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
        assertEquals(billManager.getCheckoutList(), map);
		
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
		assertEquals(billManager.getCheckoutList(), map);
 }
}
