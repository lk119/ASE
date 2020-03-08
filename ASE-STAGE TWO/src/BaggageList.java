
// import toolkits
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Advanced Software Engineering - Coursework Creates an ArrayList to store the
 * Baggage objects created from the GUI
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class BaggageList {

	private ArrayList<Baggage> baggageList;

	// initialise lists and maps
	List<Baggage> BaggageList = new ArrayList<Baggage>();
	List<Baggage> BaggageList2 = new ArrayList<Baggage>();
	List<Baggage> BaggageList3 = new ArrayList<Baggage>();
	HashMap<Baggage, String> passae = new HashMap<Baggage, String>();
	HashMap<String, String> planes = new HashMap<String, String>();

	/**
	 * Creates the arraylist to hold baggage objects
	 */
	public BaggageList() {
		baggageList = new ArrayList<Baggage>();
	}

	/**
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

	/**
	 * Returns the number of bags in the array list
	 *
	 * @return total number of bags
	 */
	public int numberOfBags() {
		return baggageList.size();
	}

	/**
	 * Returns a string value showing the amount of excess weight
	 *
	 * @return excess weight
	 */
	public String excessweight() {
		String overweight = "";
		for (Baggage b : baggageList) {
			double Exweigh = b.excessBaggageWeight();
			overweight = "Excess is :" + Exweigh;
		}
		return overweight;
	}

	/**
	 * Returns a string value showing the maximum weight the baggage is allowed to
	 * be.
	 *
	 * @return maximum allowed weight
	 */
	public String maxBaggageWeight() {
		String weigh = "";
		for (Baggage b : baggageList) {
			double data = b.getMaxBaggageWeightFlight();
			weigh = "This customer is allowed a max weight of " + data;
		}
		return weigh;
	}

	/**
	 * Returns a string value showing the maximum volume the baggage is allowed to
	 * be.
	 *
	 * @return maximum allowed volume
	 */
	public String maxBaggagevol() {
		String weigh = "";
		for (Baggage b : baggageList) {
			double data = b.getMaxBaggageVolumeFlight();
			weigh = "This customer is allowed a max vol of " + data;
		}
		return weigh;
	}

	/**
	 * Returns the baggage object that belongs to the passenger.
	 *
	 * @param passenger the passenger the baggage belongs to
	 * 
	 * @return maximum allowed volume
	 */
	public Baggage findByPassenger(Passenger passenger) {
		for (Baggage b : baggageList) {
			if (b.getPassenger() == (passenger)) {
				return b;
			}
		}
		return null;
	}

	/**
	 * Returns a string value showing the total weight capacity.
	 *
	 * @return total weight capacity
	 * 
	 * @param BaggageList	the list of baggage
	 */
	public String calcWeight(List<Baggage> BaggageList) {
		ArrayList<String> weightsArrayList = new ArrayList<>();
		ArrayList<String> weightcapArrayList = new ArrayList<>();
		String str = "";
		String capacity = "";
		int weightcapacityFlight = 1000;
		double wreached = 0.0;

		// First step
		Map<String, List<Double>> weight = new HashMap<>();
		for (Baggage b : baggageList) {
			String name = b.getPassenger().getBookingReferenceNum().substring(0, 5);
			if (weight.containsKey(name)) {
				weight.get(name).add(b.getWeight());
			} else {
				List<Double> wei = new ArrayList<>();
				wei.add(b.getWeight());
				weight.put(name, wei);
			}
		}
		// Second step
		for (Entry<String, List<Double>> entry : weight.entrySet()) {
			Double sum = calcSum(entry.getValue());
			str = "\n" + entry.getKey() + " = " + sum;
			weightsArrayList.add(str);
			wreached = (sum * 100) / weightcapacityFlight;
			weightcapArrayList.add("The volume capacity for flight " + entry.getKey() + " reached: "
					+ String.format("%.2f", wreached) + "%" + "\n");
			capacity = "\n" + weightsArrayList.toString() + "\n\n" + " " + weightcapArrayList.toString();
		}
		return capacity;
	}

	/**
	 * Returns the sum of the baggage aspect
	 *
	 * @return sum of weight or volume
	 */
	private double calcSum(List<Double> values) {
		double result = 0;
		for (Double value : values) {
			result += value;
		}
		return result;
	}

	/**
	 * Returns a string value showing the the total volume capacity
	 *
	 * @return total volume capacity
	 * 
	 * @param BaggageList2	the list of baggage
	 */
	public String calcVolume(List<Baggage> BaggageList2) {
		ArrayList<String> volumeArrayList = new ArrayList<>();
		ArrayList<String> volumecapArrayList = new ArrayList<>();
		String str = "";
		String capacity = "";
		int volumecapacityFlight = 2500;
		double vreached = 0.0;

		// First step
		Map<String, List<Double>> volume = new HashMap<>();
		for (Baggage b : baggageList) {
			String name = b.getPassenger().getBookingReferenceNum().substring(0, 5);
			if (volume.containsKey(name)) {
				volume.get(name).add(b.baggageVolume());
			} else {
				List<Double> vol = new ArrayList<>();
				vol.add(b.baggageVolume());
				volume.put(name, vol);
			}
		}
		// Second step
		for (Entry<String, List<Double>> entry : volume.entrySet()) {
			Double sum = calcSum(entry.getValue());
			str = "\n" + entry.getKey() + " = " + sum;
			volumeArrayList.add(str);
			vreached = (sum * 100) / volumecapacityFlight;
			volumecapArrayList.add("The volume capacity for flight " + entry.getKey() + " reached: "
					+ String.format("%.2f", vreached) + "%" + "\n");
			capacity = "\n" + volumeArrayList.toString() + "\n\n" + " " + volumecapArrayList.toString();
		}
		return capacity;
	}

	/**
	 * Returns an ArrayList of all the excess baggage fees
	 *
	 * @return excess baggage fees ArrayList
	 * 
	 * @param BaggageList3	the list of baggage
	 */
	public ArrayList<String> calcFees(List<Baggage> BaggageList3) {
		ArrayList<String> feeArrayList = new ArrayList<>();
		String str = "";
		// First step
		Map<String, List<Double>> fee = new HashMap<>();
		for (Baggage b : baggageList) {
			String name = b.getPassenger().getBookingReferenceNum().substring(0, 5);
			if (fee.containsKey(name)) {
				fee.get(name).add(b.excessBaggageFee());
			} else {
				List<Double> f = new ArrayList<>();
				f.add(b.excessBaggageFee());
				fee.put(name, f);
			}
		}
		// Second step
		for (Entry<String, List<Double>> entry : fee.entrySet()) {
			Double sum = calcSum(entry.getValue());
			str = "\n" + entry.getKey() + " = " + sum;
			feeArrayList.add(str);
		}
		return feeArrayList;
	}

	/**
	 * Returns an ArrayList of the checked-in passengers
	 *
	 * @return checked-in passenger ArrayList
	 */
	public ArrayList<String> passengerCapacity() {
		int count2 = 0;
		double count3 = 0;
		final double capacity = 150;
		int count = 0;
		ArrayList<String> checkins = new ArrayList<>();
		HashMap<Baggage, String> passae = new HashMap<Baggage, String>();
		HashMap<String, String> planes = new HashMap<String, String>();
		for (Baggage b : baggageList) {
			passae.put(b, b.getPassenger().getflightCode());
			planes.put(b.getPassenger().getflightCode(), b.getPassenger().getpClass());
		}
		// auto updates of checkIn
		for (Map.Entry<String, String> fentry : planes.entrySet()) {
			for (Map.Entry<Baggage, String> entry : passae.entrySet()) {
				if (fentry.getKey().equalsIgnoreCase(entry.getKey().getPassenger().getflightCode())) {
					count++;
				}
			}
			count2 = Collections.frequency(passae.values(), fentry.getKey());
			String count4 = String.valueOf(count2);
			count3 = (Double.valueOf(count4) / capacity) * 100;
			String strCount3 = String.format("%.2f", count3);
			String res = "\n" + fentry.getKey() + " = " + count2 + "     |     " + "  Flight capacity reached:" + ""
					+ strCount3 + "%";
			checkins.add(res);
		}
		return checkins;
	}

	/**
	 * Returns an a report indicating, for each flight, the number of passengers
	 * checked-in, the total weight of their baggage, the total volume of their
	 * baggage, and the total excess baggage fees collected.
	 *
	 * @return flight report
	 */
	public void flightReport() {
		String WoL = "Weight of Luggage (Kg)|" + "\n" + "---------------------";
		String VoB = "Volume of Baggage (In3)|" + "\n" + "---------------------";
		String LoF = "Total excess Baggage Fees (GBP)   |" + "\n" + "-----------------------------------";
		String LoP = "Total Passengers Checked-In   |" + "\n" + "-----------------------------";

		String WoL2 = calcWeight(BaggageList).toString().replace("[", "").replace("]", "").replace(",", "");
		String VoB2 = calcVolume(BaggageList2).toString().replace("[", "").replace("]", "").replace(",", "");
		String LoF2 = calcFees(BaggageList3).toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');
		String LoP2 = passengerCapacity().toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');

		String flightReport = "----------------------------------------------------------------------------" + "\n"
				+ WoL + "\n" + WoL2 + "\n" + "\n"
				+ "----------------------------------------------------------------------------" + "\n" + VoB + "\n"
				+ VoB2 + "\n" + "\n" + "----------------------------------------------------------------------------"
				+ "\n" + LoF + "\n" + LoF2 + "\n" + "\n"
				+ "----------------------------------------------------------------------------" + "\n" + LoP + "\n"
				+ LoP2 + "\n" + "\n" + "----------------------------------------------------------------------------";

		writeToFile("FlightReport.txt", flightReport);
	}

	/**
	 * writes supplied text to file
	 * 
	 * @exception FileNotFoundException if an attempt to open the file denoted by a
	 *                                  specified pathname has failed
	 * @exception IOException         stream to file cannot be written to or
	 *                                  closed
	 * @param Report the name of the file to be written to
	 * @param report the text to be written to file
	 */
	private void writeToFile(String Report, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(Report);
			fw.write("Travel Pigeon Check-In Report\n");
			fw.write(report);
			fw.close();
		}
		// if file not found give message and stop
		catch (FileNotFoundException fnf) {
			System.out.println(Report + " not found ");
			System.exit(0);
		}
		// stack trace here
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
}