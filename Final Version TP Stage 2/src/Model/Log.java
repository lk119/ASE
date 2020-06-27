package Model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Advanced Software Engineering - Coursework Class to construct and initialise
 * the Log object as well as methods to allow other classes to access the
 * instance of the Log object so the Log file can acquire the relevant
 * information
 * 
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */

public class Log {

	private static volatile Log log = null;

	private final String logFile = "log.txt";
	private FileWriter fw;
	private PrintWriter writer;

	private Log() {
		this.createLogFile();
	}

	/**
	 * This method allows for the instance of the Log object to be accessed and
	 * called by another class using getInstance
	 * 
	 * @return log
	 */
	public static Log getInstance() {
		if (log == null) {
			synchronized (Log.class) {
				if (log == null) {
					log = new Log();
				}
			}
		}
		return log;
	}

	public void createLogFile() {
		try {
			fw = new FileWriter(logFile);
			fw.write("PROGRESS LOG:\n\n");
			writer = new PrintWriter(fw, true);
		}
		// if file not found give message and stop
		catch (FileNotFoundException fnf) {
			System.out.println(logFile + " not found ");
			System.exit(0);
		}
		// stack trace here
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @param name       Passenger's full name
	 * @param Class      Passenger's class (economy/first/business)
	 * @param flightCode Passenger's flight code
	 */
	public void logCheckInQueue(String name, String Class, String flightCode) {

		// display when passenger has joined check-in queue
		writer.println(Class + " class passenger " + name + " ,travelling by flight " + flightCode + " has joined the "
				+ Class + " class check-in queue\n");

	}

	/**
	 * @param passengerClass Passenger's class (economy/first/business)
	 * @param name           Passenger's full name
	 * @param flightCode     Passenger's flight code
	 * @param bag            Baggage dimensions
	 * @param bagWeight      Baggage weight
	 * @param fees           Total Excess baggage fees
	 * @param excessVolume   Excess baggage volume
	 * @param excessWeight   Excess baggage weight
	 * @param volumeFees     Excess volume fees
	 * @param weightFees     Excess weight fees
	 */
	public void logCheckInDesk(String passengerClass, String name, String flightCode, String bag, String bagWeight,
			String fees, String excessVolume, String excessWeight, String volumeFees, String weightFees) {
		// display when passenger is at economy check-in desk
		writer.println(name + " (Flight: " + flightCode + ", " + passengerClass + " Class) is now at " + passengerClass
				+ " class Check-In desk.");
		writer.println("The bag dimensions are " + bag + " The bag weight is " + bagWeight + " Kg.");
		writer.println("Excess baggage volume is " + excessVolume);
		writer.println("Excess baggage weight is " + excessWeight);
		writer.println(
				"Excess baggage volume fees due is " + volumeFees + " Excess baggage weight fees due is " + weightFees);
		writer.println("The total excess baggage fees collected is " + fees + "\n");
	}

	/**
	 * @param name       Passenger's full name
	 * @param Class      Passenger's class (economy/first/business)
	 * @param flightCode Passenger's flight code
	 */

	public void logSecurityQueue(String passengerClass, String name, String flightCode) {
		// display when passenger has joined security queue
		writer.println(
				name + " (Flight " + flightCode + ", " + passengerClass + " Class) has joined the security queue\n");
	}

	/**
	 * @param name       Passenger's full name
	 * @param Class      Passenger's class (economy/first/business)
	 * @param flightCode Passenger's flight code
	 */

	public void logBoardingQueue(String passengerClass, String name, String flightCode) {
		// display when passenger has joined boarding queue
		writer.println(
				name + " (" + passengerClass + " Class) has joined the boarding queue for Flight " + flightCode + "\n");
	}

}