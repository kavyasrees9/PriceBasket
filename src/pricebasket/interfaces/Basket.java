package pricebasket.interfaces;

import java.util.List;

import pricebasket.dto.Product;

/**
 * 
 * @author Kavyasree
 * @see Basket interface to declare all the methods to be implemented.
 */
public interface Basket {
	
	void addProduct(Product product);
	
	double getSubTotal();
	
	List<Product> getProductsOnOffer();
	
	double getTotal();
	
	double getTotalDiscount();
}
