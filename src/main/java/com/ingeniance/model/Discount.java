package com.ingeniance.model;

/**
 * This entity is a POJO  representing a Discount
 * @author binebli
 * @version 1.0
 */
public class Discount {
	private Price discountPrice;
	private int amount;
	private Product discountProduct;
	private Discount(Price discountPrice, int amount) {
		super();
		this.discountPrice = discountPrice;
		this.amount = amount;
	}
	private Discount(Price discountPrice, int amount, Product discountProduct) {
		super();
		this.discountPrice = discountPrice;
		this.amount = amount;
		this.discountProduct = discountProduct;
	}

	@Override
	public String toString() {
		return "Discount [discountPrice=" + discountPrice + ", amount=" + amount + ", discountProduct="
				+ discountProduct + "]";
	}

	public static class DiscountBuilder {
		private int amount;
		private double discountPrice;
		private Product discountProduct;
		private DiscountBuilder() {
		}

		public static DiscountBuilder aDiscount() {
			return new DiscountBuilder();
		}

		public DiscountBuilder forProductAmount(int amount) {
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
			return new Discount(new Price(discountPrice), amount,discountProduct);
		}
	}
}
