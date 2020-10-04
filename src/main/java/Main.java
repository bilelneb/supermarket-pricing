import java.math.BigDecimal;

import com.ingeniance.model.Discount;
import com.ingeniance.model.Discount.DiscountBuilder;
import com.ingeniance.model.Product;
import com.ingeniance.model.Product.ProductBuilder;
import com.ingeniance.service.BillManager;
import com.ingeniance.service.BillManagerImpl;


public class Main {
	private static  BillManager billManager = new BillManagerImpl();
	public static void main(String[] args) {
		Product p1 = ProductBuilder.aProduct().withName("Tmatem").withPrice(3f).build();
		Product p2 = ProductBuilder.aProduct().withName("batata").withPrice(2f).build();
		Product p3 = ProductBuilder.aProduct().withName("Tmatem").withPrice(3.5f).build();
		Product p4 = ProductBuilder.aProduct().withName("batata").withPrice(1f).build();
		Product p5 = ProductBuilder.aProduct().withName("batata").withPrice(1f).build();

		
		Discount d = DiscountBuilder.aDiscount().build();
		Discount d1 = DiscountBuilder.aDiscount().forProductAmount(2).build();
		Discount d2= DiscountBuilder.aDiscount().withDiscountPrice(5f).build();
		billManager.addToBill(p1, BigDecimal.valueOf(10));
		billManager.addToBill(p2, BigDecimal.valueOf(20));
		billManager.addToBill(p3, BigDecimal.valueOf(30));
		billManager.addToBill(p4,  BigDecimal.valueOf(40));
		System.out.println(billManager.getCheckoutList());
		billManager.removeFromBill(p5,  BigDecimal.valueOf(60));
		System.out.println(billManager.getCheckoutList());

	}

}
