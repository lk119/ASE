
/**
 * Advanced Software Engineering - Coursework
 * Creates an ArrayList to store the Baggage objects
 * created from the GUI
 *
 * @author Lynsey Kirk
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public Baggage findByPassenger(Passenger passenger) {
		for (Baggage b : baggageList) {
			if (b.getPassenger() == (passenger)) {
				return b;
			}
		}
		return null;
	}

	List<Baggage> BaggageList = new ArrayList<Baggage>();

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
				System.out.println(weight);
			}
		}

		// Second step
		// Map<Flight, Double> results = new HashMap<>();
		for (Entry<String, List<Double>> entry : weight.entrySet()) {
			Double sum = calcSum(entry.getValue());

			// results.put(entry.getKey(), sum);
			// Print results
			str = "\n" + entry.getKey() + " = " + sum;
			weightsArrayList.add(str);
			System.out.println(weightsArrayList);
			wreached = (sum * 100) / weightcapacityFlight;
			weightcapArrayList.add("The volume capacity for flight " + entry.getKey() + " reached: "+ String.format("%.2f", wreached)
			+ "%" + "\n");
			capacity =  "\n" + weightsArrayList.toString() + "\n\n" + " " + weightcapArrayList.toString();
		}
		return capacity;
	}

	private double calcSum(List<Double> values) {
		double result = 0;
		for (Double value : values) {
			result += value;
		}
		return result;
	}

	List<Baggage> BaggageList2 = new ArrayList<Baggage>();

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
				System.out.println(volume);
			}
		}

		// Second step

		// Map<String, Double> results = new HashMap<>();
		for (Entry<String, List<Double>> entry : volume.entrySet()) {
			Double sum = calcSum(entry.getValue());

			// results.put(entry.getKey(), sum);
			// Print results
			str = "\n" + entry.getKey() + " = " + sum;
			volumeArrayList.add(str);
			
			vreached = (sum * 100) / volumecapacityFlight;
			volumecapArrayList.add("The volume capacity for flight " + entry.getKey() + " reached: "+ String.format("%.2f", vreached)
			+ "%" + "\n");
			capacity =  "\n" + volumeArrayList.toString() + "\n\n" +  " " + volumecapArrayList.toString();
			System.out.println(volumeArrayList);
		}
		return capacity;
	}

	List<Baggage> BaggageList3 = new ArrayList<Baggage>();

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
				System.out.println(fee);
			}
		}

		// Second step

		// Map<String, Double> results = new HashMap<>();
		for (Entry<String, List<Double>> entry : fee.entrySet()) {
			Double sum = calcSum(entry.getValue());

			// results.put(entry.getKey(), sum);
			// Print results
			str = "\n" + entry.getKey() + " = " + sum;
			feeArrayList.add(str);

			System.out.println(feeArrayList);
		}
		return feeArrayList;
	}

	HashMap<Baggage, String> passae = new HashMap<Baggage, String>();

	HashMap<String, String> planes = new HashMap<String, String>();

	// System.out.println(passae.entrySet());

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
					/*
					 * boolean sta= entry.getValue().getPassenger().getCheckInStatus(); String res=
					 * String.valueOf(sta);
					 */

					count++;
					// System.out.println(count);{

				}

			}

			System.out.println(fentry.getKey() + "has:" + count + " passengers AAAAA  checkedIn");
			count2 = Collections.frequency(passae.values(), fentry.getKey());
			String count4 = String.valueOf(count2);
			count3 = (Double.valueOf(count4) / capacity) * 100;
			String strCount3 = String.format("%.2f", count3);
			String res = "\n" + fentry.getKey() + " = " +  count2 + "     |     " + "  Flight capacity reached:" + "" + strCount3 + "%";
			checkins.add(res);
			System.out.println("" + count2 + fentry.getKey());
		}

		return checkins;

	}
	
	public void flightReport() {

		String WoL = "Weight of Luggage (Kg)|"                    + "\n" + "---------------------" ;
		String VoB = "Volume of Baggage (In3)|"                   + "\n" + "---------------------" ;
		String LoF = "Total excess Baggage Fees (GBP)   |"        + "\n" + "-----------------------------------" ;
        String LoP = "Total Passengers Checked-In   |"    + "\n"  + "-----------------------------";
		
		
		String WoL2 = calcWeight(BaggageList).toString().replace("[","").replace("]", "").replace(",", "");
		String VoB2 = calcVolume(BaggageList2).toString().replace("[", "").replace("]", "").replace(",", "");
		String LoF2 = calcFees(BaggageList3).toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');
		String LoP2 = passengerCapacity().toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');

		String flightReport = 
		         "----------------------------------------------------------------------------"
		+ "\n" + WoL  + "\n" + WoL2 + "\n"
		+ "\n" + "----------------------------------------------------------------------------" 
		+ "\n" + VoB  + "\n" + VoB2 + "\n"
		+ "\n" + "----------------------------------------------------------------------------"
		+ "\n" + LoF  + "\n" + LoF2 + "\n"
		+ "\n" + "----------------------------------------------------------------------------"
		+ "\n" + LoP  + "\n" + LoP2 + "\n"
		+ "\n" + "----------------------------------------------------------------------------";
		
		writeToFile("FlightReport.txt", flightReport);
	}

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