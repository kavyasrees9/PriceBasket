package pricebasket.interfaces;

import java.util.List;

import pricebasket.dto.*;


/**
 * @author Kavyasree
 * @see OfferService interface to declare applyOffers method to be implemented.
 */
public interface OffersService {

	List<Product> applyOffers(List<Product> basketProducts);
}
