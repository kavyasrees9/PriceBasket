package pricebasket.dto;

/**
 * 
 * @author Kavyasree
 * @see Pojo class with setters and getters for the required variables name,id..
 */
public class Offer {
	int id;
	String name;
	double offerPercentage;

	public Offer(int id, String name, double offerPercentage) {
		super();
		this.id = id;
		this.name = name;
		this.offerPercentage = offerPercentage;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getOfferPercentage() {
		return offerPercentage;
	}

}
