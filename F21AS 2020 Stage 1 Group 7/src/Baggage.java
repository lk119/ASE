
/**
 * Advanced Software Engineering - Coursework
 * Class to construct the Baggage object
 * Holds Baggage information and calculates
 * any excess baggage 
 * 
 * @author Lynsey Kirk
 */

public class Baggage {

	private double weight;
	private double height;
	private double width;
	private double breadth;
	private Passenger passenger;
	private Flight flight;
	
	
	/**
	 * Constructor for the Baggage class
	 * 
	 * @param weight		Weight of the baggage
	 * @param height		Height of the baggage
	 * @param width			Width of the baggage
	 * @param breadth		Breadth of the baggage
	 * @param passenger		The passenger associated with the baggage
	 * @param flight		The flight associated with the baggage
	 * 
	 */
	public Baggage (double weight, double height, double width, double breadth, Passenger passenger, Flight flight) {
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
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
		return breadth;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public Flight getFlight() {
		return flight;
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
		this.breadth = breadth;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	
	
	/**
	 * Calculates the overall volume of the baggage piece
	 * 
	 * @return the baggage volume
	 */
	public double baggageVolume() {
		double volume = width*height*breadth;
		return volume;
	}
	
	/**
	 * Considers if the baggage weight exceeds the maximum baggage
	 * allowance for the given flight and
	 * calculates the amount of excess weight
	 * 
	 * @return excess baggage weight
	 */
	public double excessBaggageWeight() {
		double excess = 0;
		double maxWeight = getMaxBaggageWeightFlight(); //needs flight association
		if (weight > maxWeight) {
			excess = maxWeight - weight;
		}
		return excess;
	}
	
	/**
	 * Considers if the baggage volume exceeds the maximum baggage
	 * allowance for the given flight and
	 * calculates the amount of excess volume
	 * 
	 * @return excess baggage volume
	 */
	public double excessBaggageVolume() {
		double excess = 0;
		double volume = baggageVolume();
		double maxVolume = getMaxBaggageVolumeFlight(); //needs flight association
		// this calculation may need to change
		if (volume > maxVolume) {
			excess = maxVolume - volume;
		}
		return excess;
	}
	
	

	/**
	 * Calculates the cost of the excess weight
	 * 
	 * @return excess weight fee
	 */
	public double excessWeightFee() {
		double excessFee = 0;
		double excessWeight = excessBaggageWeight();
		excessFee = excessWeight * 5;
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