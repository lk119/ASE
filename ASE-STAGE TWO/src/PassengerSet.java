
//import toolkits
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Advanced Software Engineering - Coursework Creates an set to store the
 * passenger objects inputed from the input file
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */
public class PassengerSet {

	private HashSet<Passenger> PassengerSet;
	private Scanner scanner;

	/**
	 * Creates the hash set for the passengers
	 */
	public PassengerSet() {
		PassengerSet = new HashSet<Passenger>();
	}

	/**
	 * Method to allow addition of passenger objects to the set of passengers
	 * 
	 * @param p the passenger object
	 */
	public void add(Passenger p) {
		PassengerSet.add(p);
	}

	/**
	 * Process line, extracts information and creates Passenger object
	 * 
	 * @exception NumberFormatException          if unable to format string to
	 *                                           number.
	 * @exception ArrayIndexOutOfBoundsException if the array index does not exist.
	 * 
	 * @exception InvalidBookingReference        if booking reference isn't valid.
	 * 
	 * @param line the line processed from input file
	 */
	private void processLine(String line) {
		try {
			String parts[] = line.split(",");
			Name passengerName = new Name(parts[2], parts[3], parts[4]);
			String bookingReferenceNum = parts[0];
			String flightCode = parts[1];
			String pClass = parts[6];
			String checkInStatusString = parts[5];

			boolean checkInStatus = Boolean.parseBoolean(checkInStatusString);

			Passenger p = new Passenger(bookingReferenceNum, flightCode, passengerName, checkInStatus, pClass);
			this.add(p);
			// System.out.println(getAllPassengers());
			// System.out.println(findBookingTest2());
		} catch (NumberFormatException ohno) {
			String error = "Number conversion error in '" + line + "'  - " + ohno.getMessage();
			System.out.println(error);
		} catch (ArrayIndexOutOfBoundsException ohshoot) {
			String error = "Not enough items in  : '" + line + "' index position : " + ohshoot.getMessage();
			System.out.println(error);
		} catch (InvalidBookingReference ibr) {
			String error = "Invalid Booking Reference Number : '" + line + "' index position : " + ibr.getMessage();
			System.out.println(error);
		}
	}

	/**
	 * Reads specified files, extracts passenger information, creating passenger
	 * objects Add the information to passenger set
	 * 
	 * @param filename is the name of the input file
	 * @exception FileNotFoundException if the file does not exists
	 */
	public void readFile(String filename) {
		try {
			File f = new File(filename);
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
	 * Will find passenger based on booking reference number and last name
	 * 
	 * @param bookingRefernceNum from the input list
	 */
	public Passenger findBooking(String bookingRef, String LastName) {
		HashMap<String, Passenger> findBooking = new HashMap<String, Passenger>();
		String key = "";
		for (Passenger p : PassengerSet) {
			key = p.getBookingReferenceNum();
			Passenger value = p;
			findBooking.put(key, value);
		}
		return findBooking.get(bookingRef);
	}

	/**
	 * Returns a list of passengers by iterating through the PassengerSet
	 * 
	 * @param PassengerSet
	 * @return report of Passengers
	 * 
	 */
	// this method may not be needed
	public String getAllPassengers() {
		String report = "";
		for (Passenger p : PassengerSet) {
			report += String.format("%-30s", p.getFullName() + " " + p.getCheckInStatus());
			report += "\n";
		}
		return report;
	}

	/**
	 * Creates a HashMap of passengers
	 * 
	 * @return HashMap of passengers
	 * 
	 */
	public HashMap createAMap() {
		HashMap<String, String> flyers = new HashMap<String, String>();
		for (Passenger p : PassengerSet) {
			flyers.put(p.getBookingReferenceNum(), p.getLastName());
		}
		return flyers;
	}

	/**
	 * Creates a HashMap of passengers
	 * 
	 * @return HashMap of passengers
	 * 
	 */
	public HashMap createAMap2() {
		HashMap<String, Passenger> flyers = new HashMap<String, Passenger>();
		for (Passenger p : PassengerSet) {
			flyers.put(p.getBookingReferenceNum(), p);
		}
		return flyers;
	}

}