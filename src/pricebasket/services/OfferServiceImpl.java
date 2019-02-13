package pricebasket.services;

import java.util.List;

import pricebasket.dto.Offer;
import pricebasket.dto.Product;
import pricebasket.interfaces.OffersService;

/**
 * 
 * @author Kavyasree
 * @see OfferServiceImpl implements all the methods declared in OfferService
 *      interface.
 *
 */
public class OfferServiceImpl implements OffersService {

	Offer applesOffer = new Offer(1, "Apples 10% off", 10);
	Offer soupBreadOffer = new Offer(2, "Loaf of bread is half price with 2 tins of soup", 50);

	@Override
	/**
	 * @see Method to call two Current special offers: Apples have a 10%
	 *      discount and Buy 2 tins of soup and get a loaf of bread for half
	 *      price
	 */
	public List<Product> applyOffers(List<Product> basketProducts) {

		basketProducts = applyApplesDiscount(basketProducts);

		basketProducts = applySoupTinsBreadDiscount(basketProducts);

		return basketProducts;
	}

	/**
	 * @see Method to apply
	 *      "Apples have a 10% discount off their normal price this week" if
	 *      Apples is found in the basket
	 */
	private List<Product> applyApplesDiscount(List<Product> basketProducts) {
		//
		for (Product product : basketProducts) {
			if (product.getName().equals("Apples")) {
				product.setOffer(applesOffer);
			}
		}
		return basketProducts;
	}

	/**
	 * @see Method to apply
	 *      "Buy 2 tins of soup and get a loaf of bread for half price" if
	 *      applicable on selected products in the basket.
	 */
	private List<Product> applySoupTinsBreadDiscount(List<Product> basketProducts) {
		long soupTins = basketProducts.stream().filter(product -> product.getName().equals("Soup")).count();
		// possible number of breads on offer.
		long breadsInOffer = Math.floorDiv(soupTins, 2);
		if (breadsInOffer > 0) {
			for (Product prod : basketProducts) {
				if (breadsInOffer > 0 && prod.getName().equals("Bread")) {
					prod.setOffer(soupBreadOffer);
					breadsInOffer--;
				}
			}
		}

		return basketProducts;
	}

}
