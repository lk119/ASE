/**
 * Advanced Software Engineering - Coursework
 * Creates an ArrayList to store the Baggage objects
 * created from the GUI 
 * 
 * @author Lynsey Kirk
 */


import java.util.ArrayList;

public class BaggageList {
	
	private ArrayList<Baggage> baggageList;
	
	/**
	 * Creates the arraylist to hold baggage objects
	 */
	public BaggageList() {
		baggageList = new ArrayList<Baggage>();
	}
	
	/**Note that the same bag may be added twice here!!!! code needs modification 
	 * Allows the addition of a baggage object to the baggage arraylist
	 * 
	 * @param b the baggage object
	 */
	public void addBaggage(Baggage b) {
		baggageList.add(b);
	}
	
	/**
	 * This returns the list of all the baggage
	 * 
	 * @return the list baggage
	 */
	public ArrayList<Baggage> getAllBaggage() {
		return baggageList;
	}


	
	/**
	 * Calculates the total baggage weight from all baggage
	 * 
	 * @return total weight of baggage
	 */
	public double totalBaggageWeight() {
		double total = 0;
		for (Baggage b : baggageList) {
			total += b.getWeight();
		}
		return total;
	}
	
	/**
	 * Calculates the total baggage volume from all baggage
	 * 
	 * @return total volume of baggage
	 */
	public double totalBaggageVolume() {
		double total = 0;
		for (Baggage b : baggageList) {
			total += b.baggageVolume();
		}
		return total;
	}

	/**
	 * Calculates the total excess baggage fees from all baggage
	 * 
	 * @return total excess baggage fees
	 */
	public double totalExcessBaggageCollected() {
		double total = 0;
		for (Baggage b : baggageList) {
			total += b.excessBaggageFee();
		}
		return total;
	}
	
	/**
	 * Returns a string of information including
	 * the total baggage weight, total baggage volume,
	 * and total excess baggage fees collected
	 * 
	 * @return baggage capacity analysis
	 */
	public String baggageCapacityAnalysis() {
		return "Total Baggage Weight: " + totalBaggageWeight() + "\n"
				+ "Total Baggage Volume: " + totalBaggageVolume() + "\n"
				+ "Total Excess Baggage Fees: " + totalExcessBaggageCollected();
	}
	
	public Baggage findByPassenger(Passenger passenger) {
		for (Baggage b : baggageList) {
			if (b.getPassenger() == (passenger)) {
				return b;
			}
		}
		return null;
	}
	
}
