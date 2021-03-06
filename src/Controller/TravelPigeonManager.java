package Controller;

import java.util.Scanner;
import Model.BoardingRunnable;
import Model.EcoProducer;
import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstProducer;
import Model.FirstandBusinessCheckInRunnable;

import Model.FlightList;
import Model.InvalidBookingReference;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;

import Model.SecurityRunnable;
import View.GUIMain;
import View.GUIReport;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of
 * existing of passenger bookings Runs the GUI and check-in threads
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {
	/**
	 * Reads the input files and creates the first instance of the GUI
	 * 
	 * @throws InterruptedException
	 * @throws InvalidBookingReference
	 */

	public void run() throws InterruptedException {

		// read passenger input file
		PassengerSet ps = new PassengerSet();
		ps.readFile("passengers");

		// shared object in producer/consumer model
		PassengersInQueue q = new PassengersInQueue();

		// models
		PassengersInQueue model1 = new PassengersInQueue();
		EconomyCheckIn1Runnable model2 = new EconomyCheckIn1Runnable(model1);
		EconomyCheckIn2Runnable model3 = new EconomyCheckIn2Runnable(model1);
		EconomyCheckIn3Runnable model4 = new EconomyCheckIn3Runnable(model1);
		FirstandBusinessCheckInRunnable model5 = new FirstandBusinessCheckInRunnable(model1);
		SecurityRunnable model6 = new SecurityRunnable(model1);
		BoardingRunnable model7 = new BoardingRunnable(model1);
		EcoProducer model8 = new EcoProducer(model1);
	

		// view
		// GUIMain view1 = new GUIMain (model1, null, model2, model3, model4, model5,
		// model6, model7, model8);
		//GUIReport view2 = new GUIReport(model1);

		// controller
		// GUIController controller = new GUIController(model1, model2, model3, model4,
		// model5, model6, model7, model8, view1, view2);
		// view1.setVisible(true);

		// read flight input file
		FlightList f = new FlightList();
		f.readFile("flight");

		// open the scanner
		Scanner sc = new Scanner(System.in);

		// close the scanner
		sc.close();

		Thread thread1 = new Thread(new EcoProducer(q));
		Thread thread2 = new Thread(new FirstProducer(q));
		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(q));
		Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(q));
		Thread thread5 = new Thread(new EconomyCheckIn2Runnable(q));
		Thread thread6 = new Thread(new EconomyCheckIn3Runnable(q));
		Thread thread7 = new Thread(new SecurityRunnable(q));
		Thread thread8 = new Thread(new BoardingRunnable(q));
		
	
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		

		try {
			
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
			
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}