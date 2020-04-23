package Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import View.GUIMain;

public class PassengersInQueue {

	// declare array blocking queues such that they are stored in main memory and
	// visible across threads
	private volatile BlockingQueue<Passenger> q = new ArrayBlockingQueue<Passenger>(20);
	private volatile BlockingQueue<Passenger> Checkedin = new ArrayBlockingQueue<Passenger>(165);
	private volatile BlockingQueue<Passenger> security = new ArrayBlockingQueue<Passenger>(165);
	private volatile BlockingQueue<Passenger> boarding = new ArrayBlockingQueue<Passenger>(165);

	// our flight fleet
	FlightList planes = new FlightList().populate();
	HashMap<String, String> f = planes.createAMap3();

	// start by populating the pool where the put thread will get customers
	PassengerSet customers1 = new PassengerSet().populate();

	// Baggage object
	Baggage b;

	// This method will add a person from the pool of the customer set and ensure
	// that it updates the set so as to avoid picking the same customer again.
	public void puti()

	{
		try {
			System.out.println("Passengers in Check-in queue");

			while (true) {
				// inside the queue place a random customer from the set
				Passenger p1 = customers1.rando();

				// create a baggage for each passenger
				Baggage b = p1.createAbag(p1);

				// add passenger and baggage in array blocking queue q
				q.put(p1);

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

				Log log = Log.getInstance();
				log.logCheckInQueue( name);

				// remove the passenger from the set to avoid duplication
				if (customers1.cont(p1)) {
					customers1.removeOb(p1);

				}

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("The Check-in queue size is " + q.remainingCapacity());

	}

	public BlockingQueue<Passenger> getQ() {
		return q;
	}

	public BlockingQueue<Passenger> getQ1() {
		return Checkedin;
	}

	public BlockingQueue<Passenger> getQ2() {
		return security;
	}

	public BlockingQueue<Passenger> getQ3() {
		return boarding;
	}

	// method to pick Business class passenger form the queue q
	public Passenger BusinessPicker() {

		Passenger p = null;
		try {

			while (true) {

				// The peek() is used to return the first passenger from queue q
				Passenger window = q.peek();

				// take() the passenger from q only if the pClass is Business
				if (window.getpClass().equalsIgnoreCase("Business")) {
					p = q.take();

					// get the information on the associated baggage
					Baggage b = p.createAbag(p);

					// set the check-in status as true
					p.setCheckInStatus(true);

					// will move passengers from one queue to another
					placer();

					// passenger is placed in Checkedin queue
					Checkedin(p);
					
					String passengerClass = p.getpClass();
					String name = p.getFullName();
					String flightCode = p.getflightCode();

					Log log = Log.getInstance();
					log.logCheckInDeskExecutive(passengerClass, name, flightCode);

					// get the information needed to display at the check-in desk
					System.out.println(p.getpClass() + " class passenger, " + p.getFullName() + " travelling by "
							+ p.getflightCode() + " is Checking in at First & Business Class " + "Check-in Desk");
					System.out.println("The check-in bag weight is " + b.getWeight() + " Kg and volume is "
							+ b.baggageVolume() + " cubic inches");
					System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight() + " kg.");
					System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");
				}

			}

			// this is important for threads
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// if the check-in queue is empty
		} catch (NullPointerException N) {
			System.out.println("No business class passengers waiting to be checked-in");

		}
		return p;
	}

	// method to pick Economy class passenger form the queue q
	public Passenger EconomyPicker() {

		Passenger p = null;

		try {

			while (true) {

				Passenger window = q.peek();
				if (window.getpClass().equalsIgnoreCase("economy")) {
					p = q.take();
					Baggage b = p.createAbag(p);
					p.setCheckInStatus(true);
					placer();
					Checkedin(p);
					
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
				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (NullPointerException N) {
			System.out.println("No economy class passengers waiting to be checked-in");

		}
		return p;
	}

	// method to pick First class passenger form the queue q
	public Passenger firstPicker() {
		Passenger p = null;

		try {

			while (true) {
				Passenger window = q.peek();
				if (window.getpClass().equalsIgnoreCase("first")) {
					p = q.take();
					Baggage b = p.createAbag(p);
					p.setCheckInStatus(true);
					placer();
					Checkedin(p);
					
					System.out.println(p.getpClass() + " class passenger, " + p.getFullName() + " travelling by "
							+ p.getflightCode() + " is Checking in at First & Business Class " + "Check-in Desk");
					System.out.println("The check-in bag weight is " + b.getWeight() + " Kg and volume is "
							+ b.baggageVolume() + " cubic inches");
					System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight() + " kg.");
					System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");
				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException N) {
			System.out.println("No first class passengers waiting to be checked-in");

		}
		return p;
	}

	// This method will put customers in the security queue following check-in
	public void securityQueue(Passenger p) {

		try {
			security.put(p);

		} catch (InterruptedException e) {
			System.out.println("The process was interrupted");
		} catch (NullPointerException N) {
			System.out.println("All passengers have cleared security");

		}

	}

	// This method will put customers in the security queue following check-in
	public void Checkedin(Passenger p) {

		try {
			Checkedin.put(p);

		} catch (InterruptedException e) {
			System.out.println("The process was interrupted");
		} catch (NullPointerException N) {
			System.out.println("All passengers have checked-in");

		}

	}

	// method to pick passengers form the security queue
	public Passenger PickerforSecurity() {
		Passenger p = null;

		try {

			while (true) {
				Passenger window = Checkedin.peek();
				if (window.getCheckInStatus() == true) {
					p = Checkedin.take();
					
					securityQueue(p);

					String passengerClass = p.getpClass();
					String name = p.getFullName();
					String flightCode = p.getflightCode();

					Log log = Log.getInstance();
					log.logSecurityQueue(passengerClass, name, flightCode);

					System.out.println(p.getpClass() + " class passenger, " + p.getFullName() + " travelling by "
							+ p.getflightCode() + " is in the security queue.");
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException N) {
			System.out.println("The security queue is empty");

		}
		return p;
	}

//This method will put customers in a priority boarding queue following security
	public void boardingPlaneQueue(Passenger p) {

		try {
			boarding.put(p);
		} catch (InterruptedException e) {
			System.out.println("The process was interrupted");
		} catch (NullPointerException N) {
			System.out.println("The boarding queue is empty");

		}

	}

	// method to pick passengers form the boarding queue
	public Passenger PickerforBoarding() {
		Passenger p = null;

		try {

			while (true) { Passenger window = security.peek();
			if (window.getCheckInStatus() == true) {
				p = security.take();
				boardingPlaneQueue(p);
				System.out.println(p.getpClass() + " class passenger, " + p.getFullName() + " travelling by "
						+ p.getflightCode() + " is in the boarding queue.");

				String passengerClass = p.getpClass();
				String name = p.getFullName();
				String flightCode = p.getflightCode();

				Log log = Log.getInstance();
				log.logBoardingQueue(passengerClass, name, flightCode);
			}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException N) {
			System.out.println("All passengers have boarded");

		}
		return p;
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

	public void addObserver(GUIMain guiMain) {
		// TODO Auto-generated method stub
		
	}

}
