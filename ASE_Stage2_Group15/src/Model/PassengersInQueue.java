package Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PassengersInQueue {

	private volatile BlockingQueue<Passenger> q = new ArrayBlockingQueue<Passenger>(20);
	private volatile BlockingQueue<Passenger> security = new ArrayBlockingQueue<Passenger>(165);
	private volatile BlockingQueue<Passenger> boarding = new ArrayBlockingQueue<Passenger>(165);
	
	
	// our flight fleet
	FlightList planes = new FlightList().populate();
	HashMap<String, String> f = planes.createAMap3();

	// start by populating the pool where the put thread will get customers
	PassengerSet customers1 = new PassengerSet().populate();
	Baggage b;

	// This method will add a person from the pool of the customer set and ensure
	// that it updates the set so as to avoid picking the same customer again.
	public void puti()

	{
		// current limitation of the method is that it does not eliminate the customer
		// from the set to avoid picking that person again.

		try {
			System.out.println("Passengers in Check-in queue");

			while (true) {
				// inside the the queue place a random customer from the set
				Passenger p1 = customers1.rando();
				Baggage b = p1.createAbag(p1);
				q.put(p1);
				
				
				String column1Format = "%-10.10s";  
				String column2Format = "%20.20s";  
				String column3Format = "%10.10s";   
				String column4Format = "%10.4s";   
				String column5Format = "%4.4s";   
				String column6Format = "%4.4s";   
				String formatInfo = column1Format + " " + column2Format + " " + column3Format + " Kg" 
						+ column4Format + "x" + column5Format+ "x" + column6Format + " inches";
				
				System.out.format(formatInfo, p1.getflightCode(), p1.getFullName(), b.getWeight(), 
						b.getHeight(),b.getWidth(),b.getBreadth());
				System.out.println( );
				
				// Lets make sure this method also deletes this person from the set
				// System.out.println("Previous number before the deletion is " +
				// customers1.totalcustomers());
				// System.out.println();

				if (customers1.cont(p1)) {
					customers1.removeOb(p1);
					// System.out.println("the removed object from the set is"+ p1 + "new size of
					// the set is " + customers1.totalcustomers() );
				}
				// System.out.println(Arrays.toString(q.toArray()));

				// System.out.println(q);
				// System.out.println("above is the queue after placing one customer");

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("THE queue SIZE IS " + q.remainingCapacity());

	}

	public Passenger BusinessPicker() {

		Passenger p = null;
		try {
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAll customers in business class please proceed");
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\\t\t\t\t\t\below is the queue
			// just before taking");
			// System.out.println(q);

			while (true) {

				Passenger window = q.peek();
				if (window.getpClass().equalsIgnoreCase("Business")) {
					p = q.take();
					p.setCheckInStatus(true);
					placer();
					securityQueue(p);
					boardingPlaneQueue(p);
					Baggage b = p.createAbag(p);
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t:" + p.getFullName() + "is Checking in"
							+ "Class is: " + p.getpClass() + "At Counter Business");
					System.out.println(p.getFullName()
							+ "is checking in" + "Class is: " + p.getpClass() + " at Economy Class counter");
					System.out.println("The check-in bag weight is " + b.getWeight() +  " Kg and volume is " + b.baggageVolume() + " cubic inches");
					System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight()+ " kg.");
					System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");
				}

				// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());

			}
			// System.out.println(q);
			// System.out.println("tabove is the queue after taking");
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHE PERSON WAS TAKEN OUT:"
			// + p.getFullName());
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException N) {
			System.out.println("The queue is empty");

		}
		return p;
	}

	public Passenger EconomyPicker() {

		Passenger p = null;
		Passenger p1 = null;
		try {
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tECONOMY CLASS");
			System.out.println();
			// System.out.println("Below is the queue just before taking");
			// System.out.println(q);

			while (true) {

				Passenger window = q.peek();
				if (window.getpClass().equalsIgnoreCase("economy")) {
					p = q.take();
					p.setCheckInStatus(true);
					placer();
					securityQueue(p);
					boardingPlaneQueue(p);
					Baggage b = p.createAbag(p);
					System.out.println(p.getFullName()
							+ "is checking in" + "Class is: " + p.getpClass() + " at Economy Class counter");
					System.out.println("The check-in bag weight is " + b.getWeight() +  "Kg and volume is " + b.baggageVolume() + " cubic inches");
					System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight()+ " kg.");
					System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");
				}

				// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:
				// " + q.size());

			}
			// System.out.println(q);
			// System.out.println("tabove is the queue after taking");
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHE PERSON WAS TAKEN OUT:"
			// + p.getFullName());
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (NullPointerException N) {
			System.out.println("The queue is empty");

		}
		return p;
	}

	public BlockingQueue<Passenger> getQ() {

		return q;
	}

	public Passenger firstPicker() {
		Passenger p = null;
		Passenger p1 = null;
		try {
			System.out.println("FIRST CLASS COUNTER");
			System.out.println();
			// System.out.println("Below is the queue just before taking");
			// System.out.println(q);

			while (true) {
				Passenger window = q.peek();
				if (window.getpClass().equalsIgnoreCase("first")) {
					p = q.take();
					p.setCheckInStatus(true);
					placer();
					securityQueue(p);
					boardingPlaneQueue(p);
					Baggage b = p.createAbag(p);
					System.out.println(p.getFullName()
							+ "is checking in" + "Class is: " + p.getpClass() + " at Economy Class counter");
					System.out.println("The check-in bag weight is " + b.getWeight() +  "Kg and volume is " + b.baggageVolume() + " cubic inches");
					System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight()+ " kg.");
					System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");
				}

				// System.out.println("Remaining: " + q.size());

			}
			// System.out.println(q);
			// System.out.println("tabove is the queue after taking");
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHE PERSON WAS TAKEN OUT:"
			// + p.getFullName());
			// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException N) {
			System.out.println("The queue is empty");

		}
		return p;
	}
	
	

	//This method will put customers in a queue following processing	
		public void securityQueue(Passenger p) {
		
			
			try {
				security.put(p);
				System.out.println(p.getFullName());
			} catch (InterruptedException e) {
				System.out.println("The process was interrupted");
			} catch (NullPointerException N) {
				System.out.println("The queue is empty");

			}
			
		}
	
	
	
	
//This method will put customers in a queue following processing	
	public void boardingPlaneQueue(Passenger p) {
	
		
		try {
			boarding.put(p);
			System.out.println(p.getFullName());
		} catch (InterruptedException e) {
			System.out.println("The process was interrupted");
		} catch (NullPointerException N) {
			System.out.println("The queue is empty");

		}
		
	}

	// This method will move passengers from boarding queue into the appropriate
	// planes

	public void placer() {
		// AtomicInteger count = new AtomicInteger();

		for (Map.Entry<String, String> fentry : f.entrySet()) {
			int count = 0;

			for (Passenger p : boarding) {
				if (fentry.getKey().equalsIgnoreCase(p.getflightCode())) {
					// count.addAndGet(1);
					count++;
					
				}
			}
			System.out.println(fentry.getKey() + "  " + count + "   " + "Checked In");

		}
	}

}
