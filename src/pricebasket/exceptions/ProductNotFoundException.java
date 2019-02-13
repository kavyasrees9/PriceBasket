package pricebasket.exceptions;

public class ProductNotFoundException extends Exception {

	
	private static final long serialVersionUID = -7309961134420039850L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}