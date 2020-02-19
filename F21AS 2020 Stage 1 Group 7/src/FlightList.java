import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

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

	public void readFile(String filename) {
		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				// read first line and process it
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {// ignored if blank line
					processLine(inputLine);

				}
			}
		}
		// if the file is not found, stop with system exit
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		} catch (InputMismatchException ex) {
			System.out.println("Error " + ex);
		}
		// Scanner may need to be closed
	}

	private void processLine(String inputLine) {
		// TODO Auto-generated method stub

	}

}
