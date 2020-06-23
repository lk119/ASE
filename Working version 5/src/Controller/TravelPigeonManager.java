package Controller;

import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;




import Model.InvalidBookingReference;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;



import View.GUIMain;


/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of
 * existing of passenger bookings Runs the GUI and check-in threads
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {

	

	public void run() throws InterruptedException {

		// read passenger input file
		PassengerSet ps = new PassengerSet();

        //Input file of the passengers
		ps.readFile("passengers");

		// open the scanner
		Scanner sc = new Scanner(System.in);

		// close the scanner
		sc.close();

		// model
		PassengersInQueue model = new PassengersInQueue();
		
		
		// view
		GUIMain view1 = new GUIMain(model, null, null);
		

		// controller
		GUIController controller = new GUIController(model,  view1);
		
		//display the GUI
		view1.setVisible(true);

	}
}
