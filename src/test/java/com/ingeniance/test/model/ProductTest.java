package com.ingeniance.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ingeniance.model.Product;
import com.ingeniance.test.utility.TestName;
import com.ingeniance.test.utility.TestPrice;
import com.ingeniance.test.utility.TestProduct;

public class ProductTest {
	public static class BuilderTest {
       
        @Test
        public void instanceShouldBeCreated() throws Exception {
            Product product = TestProduct.YOGHURT.toProduct();

            assertNotNull(product);
            assertEquals(TestName.YOGHURT.toName(),product.getProductName());
            assertEquals(TestPrice.TWENTY.toPrice(),product.getUnitPrice());
        }
    }
}
