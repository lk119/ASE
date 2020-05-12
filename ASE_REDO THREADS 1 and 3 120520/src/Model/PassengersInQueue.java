 package Model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.concurrent.atomic.AtomicInteger;

import View.GUIMain;

public class PassengersInQueue {

	private Object lock1 = new Object();
	
	
	private volatile ArrayList<Passenger> q = new ArrayList<Passenger>(20);
	
	
	
	private volatile ArrayList<Passenger> Checkedin = new ArrayList<Passenger>(165);
	private volatile ArrayList<Passenger> security = new ArrayList<Passenger>(165);
	private volatile ArrayList<Passenger> boarding = new ArrayList<Passenger>(165);
	
	
		// our flight fleet
	FlightList planes = new FlightList().populate();


	// start by populating the pool where the put thread will get customers
	PassengerSet customers1 = new PassengerSet().populate();

	// Baggage object
	Baggage b;

	
	
	
	
	// This method will add a person from the pool of the passenger set and ensure
	// that it updates the set so as to avoid picking the same passenger again
	public synchronized void puti()
	{
		System.out.println("Passengers in Check-in queue");

		while (true) {
			// inside the queue place a random customer from the set
			Passenger p1 = customers1.rando();

			// create a baggage for each passenger
			Baggage b = p1.createAbag(p1);

			// add passenger and baggage in array list q
			q.add(p1);
			
			// to format the passengers and baggage dimensions in the queue
			String column1Format = "%-10.10s"; // right indent
			String column2Format = "%20.20s"; // left indent
			String column3Format = "%10.10s"; // left indent
			String column4Format = "%10.4s"; // left indent
			String column5Format = "%4.4s"; // left indent
			String column6Format = "%4.4s"; // left indent

			// formatting information
			String formatInfo = column1Format + " " + column2Format + " " + column3Format + " Kg" + column4Format
					+ "x" + column5Format + "x" + column6Format + " inches";
			// column information to be printed
			System.out.format(formatInfo, p1.getflightCode(), p1.getFullName(), b.getWeight(), b.getHeight(),
					b.getWidth(), b.getBreadth());
			System.out.println();
						

			String name = p1.getFullName();

			// this will print to the log report of programme execution
			Log log = Log.getInstance();
			log.logCheckInQueue(name);
			
			// remove the passenger from the set to avoid duplication
			if (customers1.cont(p1)) {
				customers1.removeOb(p1);
				
			}
					

		}

	}

		
		
	// method to pick Economy class passenger form the queue q
		public Passenger EconomyPicker() {
					
			for (int i =0; i< q.size(); i++ ) {  
				
				try {
				 Passenger p = (Passenger) q.get(i);
				 
					if (p.getpClass().equalsIgnoreCase("economy")) {
					  	Baggage b = p.createAbag(p);
						p.setCheckInStatus(true);
						Checkedin.add(p);
										
											
						System.out.println(p.getpClass() + " class passenger, " + p.getFullName() + " travelling by "
								+ p.getflightCode() + " is Checking in at Economy Class " + "Check-in Desk");
						System.out.println("The check-in bag weight is " + b.getWeight() + " Kg and volume is "
								+ b.baggageVolume() + " cubic inches");
						System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight() + " kg.");
						System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");

						String passengerClass = p.getpClass();
						String name = p.getFullName();
						String flightCode = p.getflightCode();

						Log log = Log.getInstance();
						log.logCheckInDeskEconomy(passengerClass, name, flightCode);
						
						// remove the passenger from the set to avoid duplication
						if (q.contains(p)) {
							q.remove(p);

						}
					}
					} catch (NullPointerException N) {
				System.out.println("No economy class passengers waiting to be checked-in");
			}
			
					}
			return null;

		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Passenger> getQ() {
		return q;
	}

	public  ArrayList<Passenger> getQ1() {
		return Checkedin;
	}

	public  ArrayList<Passenger> getQ2() {
		return security;
	}

	public ArrayList<Passenger> getQ3() {
		return boarding;
	}

	
	


	// This method will put customers in the security queue following check-in
	public void securityQueue(Passenger p) {

		try {
			security.add(p);

		} catch (NullPointerException N) {
			System.out.println("All passengers have cleared security");

		}

	}

	// This method will put customers in the security queue following check-in
	public void Checkedin(Passenger p) {

		try {
			Checkedin.add(p);

		} catch (NullPointerException N) {
			System.out.println("All passengers have checked-in");

		}

	}



//This method will put customers in a priority boarding queue following security
	public void boardingPlaneQueue(Passenger p) {

		try {
			boarding.add(p);
		} catch (NullPointerException N) {
			System.out.println("The boarding queue is empty");

		}

	}

	

	// This method will move passengers from boarding queue into the appropriate
	// planes

	public void placer() {
		// AtomicInteger count = new AtomicInteger();

		for (Map.Entry<String, String> fentry : f.entrySet()) {
			int count = 0;

			for (Passenger p : Checkedin) {
				if (fentry.getKey().equalsIgnoreCase(p.getflightCode())) {
					// count.addAndGet(1);
					count++;

				}
			}
			System.out.println(fentry.getKey() + "  " + count + "   " + "Checked In");

		}
	}

	
	public void placer1() {
		// AtomicInteger count = new AtomicInteger();

		for (Map.Entry<String, String> fentry : f.entrySet()) {
			int count = 0;

			for (Passenger p : security) {
				if (fentry.getKey().equalsIgnoreCase(p.getflightCode())) {
					// count.addAndGet(1);
					count++;

				}
			}
			System.out.println(fentry.getKey() + "  " + count + "   " + "have cleared security check");

		}
	}
	
	
	public void placer2() {
		// AtomicInteger count = new AtomicInteger();

		for (Map.Entry<String, String> fentry : f.entrySet()) {
			int count = 0;

			for (Passenger p : boarding) {
				if (fentry.getKey().equalsIgnoreCase(p.getflightCode())) {
					// count.addAndGet(1);
					count++;

				}
			}
			System.out.println(fentry.getKey() + "  " + count + "   " + "have boarded");

		}
	}
	
	
	
	
	
	
	
	
	
		public void addObserver(GUIMain guiMain) {
		// TODO Auto-generated method stub
		
	}


		
		
	
	
	
	
	
}
