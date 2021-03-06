
/**
 * Advanced Software Engineering - Coursework Class to construct the Baggage
 * object Holds Baggage information and calculates any excess baggage
 *
 *  @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class Baggage {

	private double weight;
	private double height;
	private double width;
	private double depth;
	private Passenger passenger;
	private Flight flight;

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
	public Baggage(double weight, double height, double width, double depth, Passenger passenger, Flight flight) {
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.passenger = passenger;
		this.flight = flight;
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
	 * Returns the passenger associated with the baggage
	 *
	 * @return passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * Returns the flight associated with the baggage
	 *
	 * @return flight
	 */
	public Flight getFlight() {
		return flight;
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
		System.out.println("in");
		double excess = 0;
		double maxWeight = getMaxBaggageWeightFlight();
		if (weight > maxWeight) {
			excess = Math.abs(maxWeight - weight);
		}
		System.out.println("Your bag is too heavy by:" + excess);
		return excess;
	}

	/**
	 * Calculates the maximum baggage weight allowance for the flight
	 *
	 * @return maximum baggage weight
	 */
	public double getMaxBaggageWeightFlight() {
		Passenger p = this.passenger;
		System.out.println("aaa");
		String clas = p.getpClass().trim().toUpperCase();
		String result1 = " ";
		System.out.println("you are in" + clas + "class");
		for (MaxBaggage mx : MaxBaggage.values()) {
			if (mx.name().equalsIgnoreCase(clas)) {
				result1 = String.valueOf(mx.getweight());
				System.out.println(result1);
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
		System.out.println("your baggage volume is" + volume);
		double maxVolume = getMaxBaggageVolumeFlight(); // needs flight association
		// this calculation may need to change
		if (volume > maxVolume) {
			excess = Math.abs(maxVolume - volume);
		}
		System.out.println("your excess volume is" + excess);
		return excess;
	}

	/**
	 * Calculates the maximum baggage volume allowance for the flight
	 *
	 * @return maximum baggage volume
	 */
	public double getMaxBaggageVolumeFlight() {
		Passenger p = this.passenger;
		String clas = p.getpClass().trim().toUpperCase();
		String result1 = " ";
		for (MaxBaggage mx : MaxBaggage.values()) {
			if (mx.name().equalsIgnoreCase(clas)) {
				result1 = String.valueOf(mx.getvolume());
				System.out.println("maximum allowable volume is:" + result1);
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
		excessFee = excessWeight * 50;
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
			excessFee = 60;
		}
		System.out.println("your excess fees are" + excessFee);
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

	/**
	 * Returns a string value with the baggage detials including weight, volume and excess fees
	 *
	 * @return baggage details
	 */
	public String baggageDetails() {
		String bD = getWeight() + "" + baggageVolume() + "" + excessBaggageFee();
		return bD;
	}
}
