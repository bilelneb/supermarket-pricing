package com.ingeniance.model;

import java.math.BigDecimal;
/**
 * This entity is a POJO  representing a Price
 * @author binebli
 * @version 1.0
 */
public class Price {
	private BigDecimal price;

	public Price(double price) {
		super();
		this.price = BigDecimal.valueOf(price);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Price add(Price otherPrice) {
		price = price.add(otherPrice.price);
		return this;
	}

	public Price multiply(int amount) {
		BigDecimal multiplier = BigDecimal.valueOf(amount);
		price = price.multiply(multiplier);
		return this;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
