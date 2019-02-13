package pricebasket.app;

import java.util.ArrayList;
import java.util.List;

import pricebasket.dto.Product;
import pricebasket.dto.ShoppingBasket;
import pricebasket.exceptions.ProductNotFoundException;
import pricebasket.interfaces.Basket;
import pricebasket.interfaces.OffersService;
import pricebasket.interfaces.ProductService;
import pricebasket.services.OfferServiceImpl;
import pricebasket.services.ProductServiceImpl;

public class PriceBasket {

	/**
	 * @author Kavyasree
	 * @param args
	 * @see This method is the entrypoint of the PriceBasket application
	 */
	public static void main(String[] args) {
		runPriceBasket(args);
	}

	/**
	 * 
	 * @param args
	 * @see This method returns the subtotal,offers applied,total price of the
	 *      items in the basket
	 * 
	 */
	public static Basket runPriceBasket(String[] args) {
		OffersService offerService = new OfferServiceImpl();
		ProductService productService = new ProductServiceImpl();
		Basket basket = new ShoppingBasket(offerService);
		for (String prodName : args) {
			try {
				Product product = productService.getProductByName(prodName);
				basket.addProduct(product);
			} catch (ProductNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Subtotal: " + getPrintPrice(basket.getSubTotal()));
		printOffers(basket);
		System.out.println("Total: " + getPrintPrice(basket.getTotal()));
		return basket;
	}

	/**
	 * 
	 * @param basket
	 * @see This is the internal method to check and display the available
	 *      offers on items
	 */
	private static void printOffers(Basket basket) {
		List<Product> productsOnOffer = basket.getProductsOnOffer();
		if (productsOnOffer.size() > 0) {
			List<String> offers = new ArrayList<String>();
			for (Product product : productsOnOffer) {
				if (!offers.stream().anyMatch(of -> of.equals(product.getOffer().getName()))) {
					offers.add(product.getOffer().getName());
				}
			}

			System.out.println(String.join(", ", offers) + ": -" + getPrintPrice(basket.getTotalDiscount()));

		} else {
			System.out.println("(No offers available)");
		}
	}

	/**
	 * 
	 * @param price
	 * @see method to check and return the denomination symbol £ or p
	 */
	private static String getPrintPrice(double price) {
		if (price >= 1) {
			return "£" + String.format("%.2f", price);
		} else {
			return String.format("%.0f", (price * 100)) + "p";
		}
	}
}
