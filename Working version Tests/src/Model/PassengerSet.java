package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class PassengerSet {

	private ArrayList<Passenger> PassengerSet;
	private Scanner scanner;

	/**
	 * Creates the hash set for the passengers
	 */
	public PassengerSet() {
		PassengerSet = new ArrayList<Passenger>();

	}

	/**
	 * Method to allow addition of passenger objects to the set of passengers
	 * 
	 * @param p the passenger object
	 */
	public void add(Passenger p) {
		PassengerSet.add(p);
	}

	public int totalcustomers() {

		return PassengerSet.size();

	}

	// return random passenger for the set of customers
	public Passenger rando() {
		Random random = new Random();
		Iterator<Passenger> iter = PassengerSet.iterator();

		try {
		     
			return iter.next();
		}

		catch (IllegalArgumentException iae) {
			System.out.println("All Customers have been processed");
		} catch (NoSuchElementException iae) {
			System.out.println("");
		}
		return null;

	}

	/**
	 * This returns the set of the passengers
	 * 
	 * @return the set of passengers
	 */
	public ArrayList<Passenger> getPassengerSet() {
		return PassengerSet;
	}

	/**
	 * Process line, extracts information and creates Passenger object
	 * 
	 * @exception NumberFormatException          if unable to format string to
	 *                                           number.
	 * @exception ArrayIndexOutOfBoundsException if the array index does not exist.
	 * @param line the line processed from input file
	 * @return
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

			Passenger p = new Passenger(bookingReferenceNum, flightCode, passengerName, checkInStatus, pClass, null);
			this.add(p);

			// System.out.println(getAllPassengers());
			// System.out.println(findBookingTest2());
		}

		catch (NumberFormatException ohno) {
			String error = "Number conversion error in '" + line + "'  - " + ohno.getMessage();
			System.out.println(error);
		} catch (ArrayIndexOutOfBoundsException ohshoot) {
			String error = "Not enough items in  : '" + line + "' index position : " + ohshoot.getMessage();
			System.out.println(error);
		} catch (InvalidBookingReference ibr) {
			String error = "Invalid Booking Reference Number : '" + line + "' index position : " + ibr.getMessage();
			System.out.println(error);
		}
		;

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
	 * Will find number of passengers checked in
	 * 
	 * @param check in status from the input list
	 * @return size of the set
	 */

	public int totalNumberOfPassengersCheckedIn() {
		for (Passenger p : PassengerSet) {
			if (p.getCheckInStatus() != false) {
			}
			return PassengerSet.size();
		}
		return 0;
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

	
	// Trial passengers that will be used to populate our poolset

	public PassengerSet populate() {
		PassengerSet p = new PassengerSet();
		p.readFile("passengers");
		

		return p;

	}

	public void removeOb(Passenger p) {
		PassengerSet.remove(p);
	}

//Does it contain a person

	public Boolean cont(Passenger pes) {
		boolean result = PassengerSet.contains(pes);
		return result; 
		
		
	}



}
