package com.ingeniance.model;




/**
 * This entity is a POJO  representing a Product
 * @author binebli
 * @version 1.0
 */
public  class Product {
	/**
     * The productName is a field describing the name of the Product
     */
	private String productName;
	/**
	 * The unitPrice is a field representing the unit price for a Product
	 */
	private Price unitPrice;
	
	private Product(String productName, Price unitPrice) {
		super();
		this.productName = productName;
		this.unitPrice = unitPrice;
	}
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", unitPrice=" + unitPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		Product other = (Product) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	/**
	 * A static class for Building a Product using the Builder Pattern
	 * @author binebli
	 *
	 */
	public static class ProductBuilder {
        private       String          name;
        private       double          price;
        
        private ProductBuilder() {
        }
        /**
         * static method to instantiate a {@link ProductBuilder}
         * @return {@link ProductBuilder}
         */
        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withName( String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }
        /**
         * method to build a Product
         * @return product object after building it
         */
        public Product build() {
			return new Product(name, new Price(price));
        }
    }
}
