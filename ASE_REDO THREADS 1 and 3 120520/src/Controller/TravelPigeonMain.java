
package Controller;

import Model.Consumer;
import Model.EconomyCheckIn1Runnable;
import Model.InvalidBookingReference;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;

/**
 * Advanced Software Engineering - Coursework Stage 2 Main class to initiate the
 * application MVC design pattern is used to compile the code into Model, View
 * and Controller packages
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonMain {

	/**
	 * The application's entry point
	 * 
	 * @param args an array of command-line arguments for the application
	 * @throws InvalidBookingReference
	 * @throws InterruptedException
	 */
	private Passenger passenger;
	private PassengersInQueue passengersinqueue;
	private PassengerSet passengerset;

	public static void main(String[] args) throws InterruptedException {

		// This is our shared object
		PassengersInQueue passengersinqueue = new PassengersInQueue();

		Thread thread1 = new Thread(new Consumer(passengersinqueue));
		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(passengersinqueue));
		// Thread thread4 = new Thread(new
		// FirstandBusinessCheckInRunnable(passengersinqueue));
		// Thread thread5 = new Thread(new EconomyCheckIn2Runnable(passengersinqueue));
		// Thread thread6 = new Thread(new EconomyCheckIn3Runnable(passengersinqueue));
		// Thread thread7 = new Thread(new SecurityRunnable(passengersinqueue));

		
		
		thread1.start();
		thread3.start();
		
		
		thread1.join(1000);
		thread3.join(1000);

	}

}
