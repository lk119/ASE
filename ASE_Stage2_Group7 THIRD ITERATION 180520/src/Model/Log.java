package Model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

	private static volatile Log log = null;

	private final String logFile = "log.txt";
	private FileWriter fw;
	private PrintWriter writer;

	private Log() {
		this.createLogFile();
	}

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

	public void logCheckInQueue(String name) {
		//display when passenger has joined check-in queue
		writer.println(name + " has joined the check-in queue\n");
	}

	public void logCheckInDeskEconomy(String passengerClass, String name, String flightCode) {
		//display when passenger is at economy check-in desk
		writer.println(name + " (Flight: " + flightCode + ", " + passengerClass + " Class) is now at the Economy Check-In desk\n");
	}

	public void logCheckInDeskExecutive(String passengerClass, String name, String flightCode) {
		//display when passenger is at executive check-in desk
		writer.println(name + " (Flight " + flightCode + ", " + passengerClass + " Class) is now at the Executive Check-In desk\n");
	}

	public void logSecurityQueue(String passengerClass, String name, String flightCode) {
		//display when passenger has joined security queue
		writer.println(name + " (Flight " + flightCode + ", " + passengerClass + " Class) has joined the security queue\n");
	}

	public void logBoardingQueue(String passengerClass, String name, String flightCode) {
		//display when passenger has joined boarding queue
		writer.println(name + " (" + passengerClass + " Class) has joined the boarding queue for Flight " + flightCode + "\n");
	}
	
	public void logPassengerCheckInSatus() {
		//display if passenger has successfully checked in and display baggage details
		writer.println();
	}
	
	public void logFlightCheckInNumber() {
		//update total passenger check-in count for flight
		writer.println();
	}
	
	public void logFlightCheckInSatus() {
		//display when check-in for flight has closed
		writer.println();
	}
	
	public void logFlightBoardingNumber() {
		//update total passenger count boarded for flight
		writer.println();
	}
	
	public void logFlightBoardingSatus() {
		//display when check-in for flight has closed
		writer.println();
	}
}