package pricebasket.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pricebasket.interfaces.Basket;
import pricebasket.interfaces.OffersService;

public class ShoppingBasket implements Basket {

	List<Product> products = new ArrayList<Product>();
	OffersService offerService;

	public ShoppingBasket(OffersService offerService) {
		this.offerService = offerService;
	}

	@Override
	public void addProduct(Product product) {
		products.add(product);
	}

	@Override
	/**
	 * @see Method to calculate Subtotal before any offers applied on products in the basket
	 */
	public double getSubTotal() {
		double sTotal = 0;
		for (Product product : products) {
			sTotal += product.getPrice();
		}
		return sTotal;
	}

	private void applyOffers() {
		this.products = offerService.applyOffers(this.products);
	}

	@Override
	public List<Product> getProductsOnOffer() {
		applyOffers();
		return this.products.stream().filter(product -> product.getOffer() != null).collect(Collectors.toList());
	}

	@Override
	/**
	 * @see Method to return Totalprice on the products in the basket.
	 */
	public double getTotal() {
		return getSubTotal() - getTotalDiscount();
	}
	
	@Override
	/**
	 * @see Method to calculate Totaldiscount on each product after the applied offers
	 */
	public double getTotalDiscount() {
		double sTotalDiscount = 0;
		for (Product product : products) {
			if (product.getOffer() != null) {
				sTotalDiscount += product.getDiscount();
			}
		}
		return sTotalDiscount;
	}
	
	
}
