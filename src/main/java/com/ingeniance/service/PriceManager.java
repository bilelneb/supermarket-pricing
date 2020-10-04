package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;
/**
 * This interface defines the price service
 * where we define the method responsible for the price 
 * calculation of a product whether based on a discount or not
 * @author binebli
 *
 */
public interface PriceManager {

	Price calculatePrice(Product p, BigDecimal amount, Optional<Discount> d);

}