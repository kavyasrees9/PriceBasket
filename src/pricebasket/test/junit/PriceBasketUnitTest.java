package pricebasket.test.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import pricebasket.app.PriceBasket;
import pricebasket.dto.Product;
import pricebasket.exceptions.ProductNotFoundException;
import pricebasket.services.ProductServiceImpl;

public class PriceBasketUnitTest {

	/**
	 * 
	 * @param args
	 * @see method for Junit configurations.
	 */
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(PriceBasketUnitTest.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}

	@Test
	/**
	 * Testcase to check Total amount of products in the basket.
	 */
	public void testTotal() {
		System.out.println("Unit test for Total:");
		System.out.printf("%n");
		double expResult1 = 3.00;
		String[] testInput1 = new String[] { "Apples", "Bread", "Milk" };
		System.out.println("UnitTestCase1:" + " " + Arrays.toString(testInput1));
		double actualResult1 = PriceBasket.runPriceBasket(testInput1).getTotal();
		assertEquals(expResult1, actualResult1, 0.001);
		String[] testInput2 = new String[] { "Milk" };
		System.out.println("UnitTestCase2:" + " " + Arrays.toString(testInput2));
		double expResult2 = 1.3;
		double actualResult2 = PriceBasket.runPriceBasket(testInput2).getTotal();
		assertEquals(expResult2, actualResult2, 0.001);
		String[] testInput3 = new String[] { "Milk", "Bread", "Bread", "Soup", "Soup" };
		System.out.println("UnitTestCase3:" + " " + Arrays.toString(testInput3));
		double expResult3 = 3.8;
		double actualResult3 = PriceBasket.runPriceBasket(testInput3).getTotal();
		assertEquals(expResult3, actualResult3, 0.001);
		String[] testInput4 = new String[] { "Milk", "Bread", "Bread", "Bread", "Apples", "Soup", "Soup", "Apples",
				"Soup", "Soup", "Soup" };
		System.out.println("UnitTestCase4:" + " " + Arrays.toString(testInput4));
		double expResult4 = 7.95;
		double actualResult4 = PriceBasket.runPriceBasket(testInput4).getTotal();
		assertEquals(expResult4, actualResult4, 0.001);

	}

	@Test
	/**
	 * Testcase to check if productname entered is correct or not.
	 */
	public void testProudctName() throws ProductNotFoundException {
		ProductServiceImpl productService = new ProductServiceImpl();
		Product product;
		System.out.printf("%n");
		System.out.println("Unit test for productName:");
		String[] args = new String[] { "Bread", "Milk", "Mangoes", "Soup", "Egg" };
		for (String prodName : args) {
			try {
				product = productService.getProductByName(prodName);
				System.out.println(prodName + " " + "Availabe");
				assertEquals(prodName, product.getName());
			} catch (ProductNotFoundException e) {
				System.out.println(e.getMessage());
				// return;
			}
		}

	}

}