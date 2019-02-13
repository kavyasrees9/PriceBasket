package pricebasket.dto;

/**
 * 
 * @author Kavyasree
 * @see Pojo class with setters and getters
 *
 */
public class Product implements Cloneable {

	private String name;
	private double price;
	private Offer offer;

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public double getDiscount() {
		if (offer != null) {
			return (price * (offer.offerPercentage / 100));
		}

		return 0;
	}

	public Product clone() throws CloneNotSupportedException {
		return (Product) super.clone();
	}
}
