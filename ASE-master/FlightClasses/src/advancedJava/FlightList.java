package advancedJava;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author km184
 *
 */

class FlightList {
	private ArrayList<Flight> flightList;

	/**
	 * creating an empty array list to contain the flights
	 */
	public FlightList() {
		flightList = new ArrayList<Flight>();
	}

	/**
	 * This method adds the flight validated by the constructor to the flight list
	 *
	 * @param f the flight to be added to the array list
	 */
	public boolean addFlight(Flight f) {

		String code = f.getFlightCode();
		Flight inList = this.findByCode(code);

		if (inList == null) {
			flightList.add(f);
			return true;
		}

		return false;
	}

	public Flight findByCode(String flightcode) {
		for (Flight f : flightList) {
			if (f.getFlightCode() == (flightcode)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * @return all the flights in the list added using the flightList()
	 */
	public ArrayList<Flight> getAllFlights() {
		return flightList;
	}

	/**
	 * @return returns all flights sorted by their unique codes.
	 */
	public ArrayList<Flight> listByCode() {
		ArrayList<Flight> clone = this.flightList;
		Collections.sort(clone, new FlightCodeComparator());
		return getAllFlights();
	}

	/**
	 * @return returns all the Flights by the carrier.
	 */
	public ArrayList<Flight> listByName() {
		ArrayList<Flight> clone = this.flightList;
		Collections.sort(clone, new FlightCarrierComparator());
		return getAllFlights();
	}

	public int numberOfFlights() {
		return flightList.size();
	}

}
