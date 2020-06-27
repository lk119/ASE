package Controller;


import java.util.Scanner;
import Model.PassengerSet;
import Model.PassengersInQueue;
import View.GUIMain;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the passenger input
 * file to pick passenger objects and pass them through check-in queue, check-in
 * desk, security and boarding which is presented as timed simulation on GUI
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {

	public void run() throws InterruptedException {

		// read passenger input file
		PassengerSet ps = new PassengerSet();

		// Input file of the passengers
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
		GUIController controller = new GUIController(model, view1);

		// display the GUI
		view1.setVisible(true);

	}
}
