package ase;

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
		
		// setup the GUI JFrame
		JFrame frame = new JFrame("TravelPigeon Check-in");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new tabbedPane(c), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		
		// open the scanner
		Scanner sc = new Scanner(System.in);
		// close the scanner
		sc.close();
		
	}
	
}
