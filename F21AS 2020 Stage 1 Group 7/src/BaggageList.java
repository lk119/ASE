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

	/**
	 * Note that the same bag may be added twice here!!!! code needs modification
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
	 *
	 *
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
	 * Returns a string of information including the total baggage weight, total
	 * baggage volume, and total excess baggage fees collected
	 *
	 * @return baggage capacity analysis
	 */
	public String baggageCapacityAnalysis() {
		return "Total Baggage Weight: " + totalBaggageWeight() + "\n" + "Total Baggage Volume: " + totalBaggageVolume()
				+ "\n" + "Total Excess Baggage Fees: " + totalExcessBaggageCollected();
	}

	public int numberOfBags() {
		return baggageList.size();
	}

	public String excessweight() {
		String overweight = "";
		for (Baggage b : baggageList) {
			double Exweigh = b.excessBaggageWeight();
			overweight = "Excess is :" + Exweigh;
		}
		return overweight;
	}

	public String maxBaggageWeight() {
		String weigh = "";
		for (Baggage b : baggageList) {
			double data = b.getMaxBaggageWeightFlight();
			weigh = "This customer is allowed a max weight of " + data;
		}
		return weigh;
	}

	public String maxBaggagevol() {
		String weigh = "";
		for (Baggage b : baggageList) {
			double data = b.getMaxBaggageVolumeFlight();
			weigh = "This customer is allowed a max vol of " + data;
		}
		return weigh;
	}

	public void adding() {
		Flight f1 = new Flight("Gabs", "BA", "BA203", 200);
		Passenger p1 = new Passenger("zxegvgsvd", "BA203", new Name("Kuda Muagara"), "CheckedIn", "Business");
		Baggage b1 = new Baggage(45.1, 300.0, 40.3, 40.4, p1, f1);
		this.addBaggage(b1);
	}

}