package Model;

/**
 * Advanced Software Engineering - Coursework class to define the baggage weight
 * and volume allowances for passengers (economy/first/business) as well as the
 * weight and volume hold capacity for flights
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */
public enum MaxBaggage {
	FIRST(45.0, 150.0), BUSINESS(35.0, 100.0), ECONOMY(25.0, 75.0), FLIGHT(1000.0, 2500.0);

	private final double weight; // weight is calculated in kilograms (kg)
	private final double volume; // volume is calculated in litres (Cubic inches)

	/**
	 * Constructor for MaxBaggage enum class
	 *
	 * @param weight maximum baggage weight allowances for passengers
	 *               (economy/first/business) and flights
	 * @param volume maximum baggage volume allowances for passengers
	 *               (economy/first/business) and flights
	 */
	MaxBaggage(double weight, double volume) {
		this.weight = weight;
		this.volume = volume;
	}

	/**
	 * Returns the maximum baggage weight allowances
	 * 
	 * @return
	 */
	public double getweight() {
		return weight;
	}

	/**
	 * Returns the maximum baggage volume allowances
	 * 
	 * @return
	 */
	public double getvolume() {
		return volume;
	}

}