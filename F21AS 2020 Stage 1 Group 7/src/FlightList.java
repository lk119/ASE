import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



/**
 * @author km184
 *
 */

class FlightList {
	private ArrayList<Flight> flightList;
	private Scanner scanner;

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
	 * Will find number of passengers checked in per flight
	 * 
	 * @param check in status from the Passenger Class
	 * @return frequency 
	 */
	//test this method and updated Checkin method for appropriate
	//output and use one that works (if envisioned correctly 
	//this will get checkin info from input)
	/*
	 * public int flightTotalPassengersCheckedin(PassengerSet passengerSet) { int
	 * count = passengerSet.getPassengerStatusfromLastName();
	 * 
	 * return count; }
	 */
	
	
	
	
	/**
	 * Will find number of passengers checked in per flight
	 * 
	 * @param updated check in status from the Baggage Class
	 * @return size of the set 
	 */
	//test this method and updated Checkin method for appropriate
	//output and use one that works (if envisioned correctly 
	//this will get checkin info from Baggage)
//	public double flightTotalUpdatedPassengersCheckedin() {
//		for (Flight f : flightList) {
//			if (f.flightupdatedCheckInStatus() != false) {
//			}
//			return flightList.size();
//			}
//			return 0;
//			}
	
	
	/**
	 * Will find how full the flight is based on total 
	 * passengers checked in per flight
	 * 
	 * @param check in status from the Passenger Class
	 * @param flight passenger capacity from the Flight Class
	 * @return percentage 
	 */
	//test this method and updated Checkin method for appropriate
	//output and use one that works (if envisioned correctly 
	//this will get checkin info from input)
	/*
	 * public double flightPassengerCapacityReached(PassengerSet passengerSet) {
	 * double percentage = 0; for (Flight f : flightList) { //PassengerSet
	 * passengerSet = null; percentage =
	 * (((flightTotalPassengersCheckedin(passengerSet))*100)/(f.
	 * getPassangerCarryingCapacity()));
	 * 
	 * } return percentage; }
	 */

	
	/**
	 * Will find how full the flight is based on total 
	 * updated passengers checked in per flight from Baggage
	 * 
	 * @param updated check in status from the Baggage Class
	 * @param flight passenger capacity from the Flight Class
	 * @return percentage 
	 */
	//test this method and updated Checkin method for appropriate
	//output and use one that works (if envisioned correctly 
	//this will get checkin info from Baggage)
	//public double flightUpdatedPassengerCapacityReached() {
		//double percentage = 0;
		//for (Flight f : flightList) 
				//{
			//percentage = ((flightTotalUpdatedPassengersCheckedin() / f.getPassangerCarryingCapacity()) * 100);
		//}
		//return percentage;
		//}
	
	
	
	
	
	
	/**
	 * writes supplied text to file
	 * 
	 * @exception FileNotFoundException if an attempt to open the file denoted by a
	 *                                  specified pathname has failed
	 * @exception IOExceptionif         stream to file cannot be written to or
	 *                                  closed
	 * @param Report the name of the file to be written to
	 * @param report the text to be written to file
	 */
	//need to finish this code for report
	public void writeToFile(String Report, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(Report);
			fw.write("THE REPORT\n");
			fw.write("\n" + toString()   
					);
			fw.write(report);
			
			fw.close();
		}
		// if file not found give message and stop
		catch (FileNotFoundException fnf) {
			System.out.println(Report + " not found ");
			System.exit(0);
		}
		// stack trace here- stackoverflow - encountered this one too
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
	
	public HashMap createAMap() {
		HashMap<String, String> planes = new HashMap<String, String>();

		for (Flight p : flightList) {
			planes.put(p.getFlightCode(), p.getCarrier());
		}
		return planes;
	}
	
	
}
