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

	private void processLine(String line) {

		try {
			String[] parts = line.split(",");
			String destination = parts[0];
			destination = destination.trim();
			String car = parts[1];
			car = car.trim();
			String cod = parts[2];
			cod = cod.trim();
			String cap = parts[3];
			int cap1 = Integer.parseInt(cap);
			Flight f = new Flight(destination, car, cod, cap1);
			this.addFlight(f);
		}
		// ignore lines in error and try and carry on

		// this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		// this catches missing items if only one or two items
		// other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in  : '" + line

					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}

	public void addingFlights() {
		Flight p1 = new Flight("Gaborone", "virgin", "BA234", 200);
		Flight p2 = new Flight("Gaborone", "BA", "BAdd5", 200);
		this.addFlight(p1);
		this.addFlight(p2);
	}

}
