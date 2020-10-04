package com.ingeniance.model;

/**
 * This entity is a POJO representing a Discount
 * 
 * @author binebli
 * @version 1.0
 */
public class Discount {
	private Price discountPrice;
	private double amount;
	private Product discountProduct;

	private Discount(Price discountPrice, double amount) {
		super();
		this.discountPrice = discountPrice;
		this.amount = amount;
	}

	private Discount(Price discountPrice, double amount, Product discountProduct) {
		super();
		this.discountPrice = discountPrice;
		this.amount = amount;
		this.discountProduct = discountProduct;
	}

	public Price getDiscountPrice() {
		return discountPrice;
	}

	public double getAmount() {
		return amount;
	}

	public Product getDiscountProduct() {
		return discountProduct;
	}

	@Override
	public String toString() {
		return "Discount [discountPrice=" + discountPrice + ", amount=" + amount + ", discountProduct="
				+ discountProduct + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountProduct == null) ? 0 : discountProduct.hashCode());
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
		Discount other = (Discount) obj;
		if (discountProduct == null) {
			if (other.discountProduct != null)
				return false;
		} else if (!discountProduct.equals(other.discountProduct))
			return false;
		return true;
	}

	public static class DiscountBuilder {
		private double amount;
		private double discountPrice;
		private Product discountProduct;

		private DiscountBuilder() {
		}

		public static DiscountBuilder aDiscount() {
			return new DiscountBuilder();
		}

		public DiscountBuilder forProductAmount(double amount) {
			this.amount = amount;
			return this;
		}

		public DiscountBuilder withDiscountPrice(double discountPrice) {
			this.discountPrice = discountPrice;
			return this;
		}

		public DiscountBuilder withDiscountProduct(Product discountProduct) {
			this.discountProduct = discountProduct;
			return this;
		}

		public Discount build() {
			return new Discount(new Price(discountPrice), amount, discountProduct);
		}
	}
}
