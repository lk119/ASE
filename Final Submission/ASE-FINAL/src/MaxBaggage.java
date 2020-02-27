/**
 * 
 * Advanced Software Engineering - Coursework Enumeration Class to create   
 * max baggage weight and volume constants. Used to calculate excess baggage fees
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */

public enum MaxBaggage {
	FIRST(45.0, 150.0), BUSINESS(35.0, 100.0), ECONOMY(25.0, 75.0), FLIGHT(1000.0, 2500.0);

	private final double weight; // weight is calculated in kilograms (kg)
	private final double volume; // volume is calculated in litres (Cubic iches)

	/**
	 * constructor for the enum class
	 *
	 * @param weight  max weight values of baggage 
	 * @param volume  max volume values of baggage
	 */
	MaxBaggage(double weight, double volume) {
		this.weight = weight;
		this.volume = volume;
	}

	/**
	 * returns max baggage weight values (kilograms)
	 * 
	 * @return max baggage weight
	 */
	public double getweight() {
		return weight;
	}

	/**
	 * returns max baggage volume values (cubic inches)
	 * 
	 * @return max baggage volume
	 */
	public double getvolume() {
		return volume;
	}
}