import java.awt.BorderLayout;

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
		p.readFile("passengers.csv");
		
		// read flight input file
		FlightList f = new FlightList();
		f.readFile("flights.csv");
		
		TPWelcome TPWelcome = new TPWelcome();
		
		// open the scanner
		Scanner sc = new Scanner(System.in);
		// close the scanner
		sc.close();
		
	}
	
}