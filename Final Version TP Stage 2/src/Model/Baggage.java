package Model;


/**
 * Advanced Software Engineering - Coursework Class to construct the Baggage
 * object Holds Baggage information and calculates any excess baggage
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class Baggage {

	private double weight;
	private double height;
	private double width;
	private double depth;
	private Passenger passenger;

	/**
	 * Constructor for the Baggage class
	 *
	 * @param weight    Weight of the baggage
	 * @param height    Height of the baggage
	 * @param width     Width of the baggage
	 * @param depth     Breadth of the baggage
	 * @param passenger The passenger associated with the baggage
	 * @param flight    The flight associated with the baggage
	 *
	 */
	public Baggage(double weight, double height, double width, double depth, Passenger passenger) {
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.passenger = passenger;

	}

	/**
	 * Returns the baggage weight
	 *
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Returns the baggage height
	 *
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Returns the baggage width
	 *
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the baggage breadth
	 *
	 * @return breadth
	 */
	public double getBreadth() {
		return depth;
	}

	/**
	 * Sets the baggage weight
	 *
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Sets the baggage height
	 *
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Sets the baggage width
	 *
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Sets the baggage breadth
	 *
	 * @param breadth
	 */
	public void setBreadth(double breadth) {
		this.depth = breadth;
	}

	/**
	 * Sets the passenger associated with the baggage
	 * 
	 * @param passenger
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * Returns the passenger associated with the baggage
	 * 
	 * @return passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * Calculates the overall volume of the baggage piece
	 *
	 * @return the baggage volume
	 */
	public double baggageVolume() {
		double volume = width * height * depth;
		return volume;
	}

	/**
	 * Considers if the baggage weight exceeds the maximum baggage allowance for the
	 * given flight and calculates the amount of excess weight
	 *
	 * @return excess baggage weight
	 */
	public double excessBaggageWeight() {
		double excess = 0;
		double maxWeight = getMaxBaggageWeightFlight();

		if (weight > maxWeight) {
			excess = Math.abs(maxWeight - weight);
		}

		return excess;
	}

	/**
	 * Returns the maximum baggage weight allowance for the given passenger flight
	 * 
	 * @return max baggage weight allowance
	 */
	public double getMaxBaggageWeightFlight() {
		Passenger p = this.passenger;
		String clas = p.getpClass().trim().toUpperCase();
		String result1 = " ";
		for (MaxBaggage mx : MaxBaggage.values()) {

			if (mx.name().equalsIgnoreCase(clas)) {
				result1 = String.valueOf(mx.getweight());
				// System.out.println(result1);

			}

		}
		double r2 = Double.parseDouble(result1);
		return r2;

	}

	/**
	 * Considers if the baggage volume exceeds the maximum baggage allowance for the
	 * given flight and calculates the amount of excess volume
	 *
	 * @return excess baggage volume
	 */
	public double excessBaggageVolume() {

		double excess = 0;
		double volume = baggageVolume();

		double maxVolume = getMaxBaggageVolumeFlight(); // needs flight association
		// this calculation may need to change
		if (volume > maxVolume) {
			excess = Math.abs(maxVolume - volume);
		}

		return excess;
	}

	/**
	 * Returns the maximum baggage volume allowance for the given passenger flight
	 * 
	 * @return max baggage volume allowance
	 */
	public double getMaxBaggageVolumeFlight() {
		Passenger p = this.passenger;
		String clas = p.getpClass().trim().toUpperCase();
		String result1 = " ";
		for (MaxBaggage mx : MaxBaggage.values()) {
			if (mx.name().equalsIgnoreCase(clas)) {
				result1 = String.valueOf(mx.getvolume());
				// System.out.println("maximum allowable volume is " + result1 + " cubic
				// inches.");
			}

		}
		return Double.parseDouble(result1);
	}

	/**
	 * Calculates the cost of the excess weight
	 *
	 * @return excess weight fee
	 */
	public double excessWeightFee() {
		double excessFee = 0;
		double excessWeight = excessBaggageWeight();
		excessFee = excessWeight * 10;
		return excessFee;
	}

	/**
	 * Calculates the cost of the excess volume
	 *
	 * @return excess volume fee
	 */
	public double excessVolumeFee() {
		double excessFee = 0;
		double excessVolume = excessBaggageVolume();
		if (excessVolume > 0) {
			excessFee = 20;
		}

		return excessFee;

	}

	/**
	 * Calculates the total cost of excess baggage
	 *
	 * @return excess baggage fee total
	 */
	public double excessBaggageFee() {
		double excessFee = 0;
		double excessWeight = excessWeightFee();
		double excessVolume = excessVolumeFee();
		excessFee = excessWeight + excessVolume;
		return excessFee;
	}

}
