package pricebasket.interfaces;

import java.util.List;

import pricebasket.dto.*;
import pricebasket.exceptions.ProductNotFoundException;


/**
 * 
 * @author Kavyasree
 * @see ProductService interface to declare methods to be implemented.
 */
public interface ProductService {
	List<Product> getProducts();
	Product getProductByName(String name) throws ProductNotFoundException;

}
