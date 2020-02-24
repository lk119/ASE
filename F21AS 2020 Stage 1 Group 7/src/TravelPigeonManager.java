import java.awt.BorderLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Advanced Software Engineering - Coursework
 * Reads the input files of existing of passenger bookings
 * and flight details
 * Runs the GUI for the electronic check-in system
 * 
 * @author Lynsey Kirk
 */

import java.util.Scanner;
import javax.swing.JFrame;

public class TravelPigeonManager {

	public void run() {

		// read passenger input file
		PassengerSet p = new PassengerSet();
		p.readFile("passengers");
		// System.out.println (p.totalNumberOfPassengersCheckedIn());
		// System.out.println(p.getPassengerStatusfromLastName());
		// System.out.println (p.getAllPassengers());

		BaggageList b = new BaggageList();

		// read flight input file
		FlightList f = new FlightList();
		f.readFile("flight");
		// System.out.println(f.flightTotalPassengersCheckedin(p));
		// System.out.println(f.flightPassengerCapacityReached(p));

		// TPWelcome TPWelcome = new TPWelcome(p, f, b);
		HashMap<String, String> FlightsAndLine = f.createAMap();
		HashMap<String, String> PassFlights = p.createAMap2();

		System.out.println(PassFlights.values());

		for (Map.Entry<String, String> fentry : PassFlights.entrySet()) {
			int count = 0;
			if (fentry.getValue().toString().equalsIgnoreCase("true")) {

				for (Map.Entry<String, String> entry : FlightsAndLine.entrySet()) {
					if (entry.getKey().trim().toString().equalsIgnoreCase(fentry.getKey().substring(0, 5))) {
					
                       count++;
					System.out.println(count + "from flight " + fentry.getKey().substring(0, 5) +PassFlights);

					}
				}

			}

			/*
			 * int count = Collections.frequency(PassFlights.values(),true);
			 * System.out.println("has:"+ count +" passengers already checkedIn");
			 * System.out.println(fentry);
			 */

		}

		// int count = Collections.frequency(fentry.values(),true);
		// System.out.println("has:"+ count +" passengers already checkedIn");

		// open the scanner
		Scanner sc = new Scanner(System.in);
		// close the scanner
		sc.close();

	}
}
