package TP;


/**
 * @author crs8
 * baggage constants (weight, volume)
 */
public enum MaxBaggage {
	FIRST(45.0, 150.0), BUSINESS(35.0, 100.0), ECONOMY(25.0, 75.0), FLIGHT(1000.0, 2500.0);

	private final double weight; // weight is calculated in kilograms (kg)
	private final double volume; // volume is calculated in litres (ltr)

	/**constructor
	 * @param weight
	 * @param volume
	 */
	MaxBaggage(double weight, double volume) {
		this.weight = weight;
		this.volume = volume;
	}

	/**
	 * @return
	 */
	public double getweight() {
		return weight;
	}

	/**
	 * @return
	 */
	public double getvolume() {
		return volume;
	}
	
	public MaxBaggage getNextBaggage() {
		// get array of the baggage values
		MaxBaggage[] all = MaxBaggage.values();
		int size = all.length; // how many?
		int ordinal = this.ordinal(); // index
		// get next index position
		int nextOrd = (ordinal + 1) % size;
		// use index position
		return all[nextOrd];
	}
}
