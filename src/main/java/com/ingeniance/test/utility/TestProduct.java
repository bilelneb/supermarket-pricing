package com.ingeniance.test.utility;

import com.ingeniance.model.Product;
import com.ingeniance.model.Product.ProductBuilder;

public enum TestProduct {
	FISH(TestName.FISH, TestPrice.TEN),
	POTATO(TestName.POTATO, TestPrice.FIVE),
	EGGS(TestName.EGGS, TestPrice.TWENTY),
	YOGHURT(TestName.YOGHURT, TestPrice.TWENTY);
	
    private final TestName  name;
    private final TestPrice price;

    TestProduct( TestName name,  TestPrice price) {
        this.name = name;
        this.price = price;
    }

    public  Product toProduct() {
        return ProductBuilder.aProduct().withName(name.name())
                         .withPrice(price.getValue())
                         .build();
    }
}