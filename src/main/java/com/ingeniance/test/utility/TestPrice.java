package com.ingeniance.test.utility;

import java.math.BigDecimal;
import java.text.Bidi;

import com.ingeniance.model.Price;

public enum TestPrice {
    ZERO(0),
    ONE(1),
    ONEHALF(1.5),
    FIVE(5),
    SEVEN(7),
    TEN(10),
    TWENTY(20),
    FIFTY(50);
	
    private final BigDecimal value;
    
    TestPrice(double value) {
        this.value = BigDecimal.valueOf(value);
    }
    public double getValue() {
        return value.doubleValue();
    }

    public  Price toPrice() {
        return new Price(value);
    }
}