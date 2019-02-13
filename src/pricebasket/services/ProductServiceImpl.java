package pricebasket.services;

import java.util.ArrayList;
import java.util.List;

import pricebasket.dto.Product;
import pricebasket.exceptions.ProductNotFoundException;
import pricebasket.interfaces.ProductService;

/**
 * 
 * @author Kavyasree
 * @see ProductServiceImpl implements all the methods declared in ProductService interface.
 *     
 *
 */
public class ProductServiceImpl implements ProductService {

	private static List<Product> products = new ArrayList<Product>();

	public ProductServiceImpl() {
		if (products.size() == 0) {
			Product soup = new Product("Soup", 0.65);
			products.add(soup);
			Product bread = new Product("Bread", 0.80);
			products.add(bread);
			Product milk = new Product("Milk", 1.3);
			products.add(milk);
			Product apples = new Product("Apples", 1.0);
			products.add(apples);
		}
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	/**
	 * @see Method to check if the productname entered is correct or not
	 */
	public Product getProductByName(String name) throws ProductNotFoundException {

		for (Product product : products) {
			if (product.getName().equals(name)) {
				try {
					return product.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		throw new ProductNotFoundException("Product not found with name " + name);
	}

}
