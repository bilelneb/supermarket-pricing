package com.ingeniance.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Price;
import com.ingeniance.model.Product;

public interface PriceManager {

	Price calculateWithoutDiscount(Product p, BigDecimal amount);

	Price calculateWithDiscount(Product p, BigDecimal amount, Discount d);

	Price calculatePrice(Product p, BigDecimal amount, Optional<Discount> d);

}