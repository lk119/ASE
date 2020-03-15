package Controller;

import java.util.Scanner;

import Model.CheckIn;
import Model.FlightList;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;
import View.GUIMain;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of existing
 * of passenger bookings 
 * Runs the GUI and check-in threads 
 *  
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */


public class TravelPigeonManager {
	/**
	 * Reads the input files and creates the first instance of the GUI
	 */
	
	public void run() {
		
		        // read passenger input file
				PassengerSet p = new PassengerSet();
				p.readFile("passengers");
				
				
				//shared object in producer/consumer model
				PassengersInQueue q = new PassengersInQueue();
				
				
				//to create passenger thread as the producer
				Thread passengerThread = new Thread(new PassengerSet(q));
				passengerThread.start();
				
				//to create passenger map
				p.createAMap();
				
				
				
				
				// create new instance of CheckIn
				//the Modelnull
				CheckIn Model = new CheckIn(q);   
				
						
				//to create checkIn thread as consumer
				Thread checkInThread = new Thread(new CheckIn(q));
				checkInThread.start();
				
				// read flight input file
				FlightList f = new FlightList();
				f.readFile("flight");
				
				// open the scanner
				Scanner sc = new Scanner(System.in);
				
				// close the scanner
				sc.close();

							
				// create new instance of GUIMain
				//the View
				GUIMain View = new GUIMain (Model);
				
				// create new instance of GUIController
				//the Controller
				GUIController Controller = new GUIController (Model, View);
				
						  
		         View.setVisible(true);
	
		
	}

}
