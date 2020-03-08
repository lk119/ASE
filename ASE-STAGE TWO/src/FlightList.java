
//import toolkits
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Advanced Software Engineering - Coursework Reads and processes a file input
 * from the manager class. Creates an Arraylist to store the flights from the
 * input files and populates it.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */
public class FlightList {
	private ArrayList<Flight> flightList;
	private Scanner scanner;

	/**
	 * creates an ArrayList to hold flight objects
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

	/**
	 * This method finds the flight object by the given flight code
	 *
	 * @param flightcode the flight to be found
	 */
	public Flight findByCode(String flightcode) {
		for (Flight f : flightList) {
			if (f.getFlightCode() == (flightcode)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 * @return all the flights in the list added using the flightList()
	 */
	public ArrayList<Flight> getAllFlights() {
		return flightList;
	}

	/**
	 *
	 * @return all the flights in the list added using the flightList()
	 */
	public int numberOfFlights() {
		return flightList.size();
	}

	/**
	 * Reads specified files, extracts flight information, creating flight objects
	 * Add the information to flight list
	 * 
	 * @param filename is the name of the input file
	 * @exception FileNotFoundException if the file does not exists
	 */
	public void readFile(String filename) {
		try {
			File f = new File("flights");
			scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
					processLine(inputLine);
				}
			}
		} catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
	}

	/**
	 * Process line, extracts information and creates flight object
	 * 
	 * @exception NumberFormatException          if unable to format string to
	 *                                           number.
	 * @exception ArrayIndexOutOfBoundsException if the array index does not exist.
	 * 
	 * @param line the line processed from input file
	 */
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
			Flight f = new Flight(destination, car, cod, cap1, null, null);
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

	/**
	 * Creates a HashMap of flights
	 * 
	 * @return HashMap of flights
	 * 
	 */
	public HashMap createAMap3() {
		HashMap<String, String> flyers = new HashMap<String, String>();

		for (Flight f : flightList) {
			flyers.put(f.getFlightCode(), f.getCarrier());
		}
		return flyers;
	}
}
