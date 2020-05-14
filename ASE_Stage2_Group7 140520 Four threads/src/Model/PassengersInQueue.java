package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import View.GUIMain;

public class PassengersInQueue {

	private Object lock = new Object();
	private Object lock2 = new Object();
	private final int LIMIT = 15;
	private Lock lock1 = new ReentrantLock();
	private Condition con = lock1.newCondition();
	private Semaphore sema = new Semaphore(2);

	private volatile LinkedList<Passenger> q = new LinkedList<Passenger>();
	private volatile LinkedList<Passenger> Checkedin = new LinkedList<Passenger>();
	private volatile ArrayList<Passenger> security = new ArrayList<Passenger>(165);
	private volatile ArrayList<Passenger> boarding = new ArrayList<Passenger>(165);

	// our flight fleet
	FlightList planes = new FlightList().populate();

	// start by populating the pool where the put thread will get customers
	PassengerSet customers1 = new PassengerSet().populate();

	// Baggage object
	Baggage b;
	
	//Passenger p;

	// This method will add a person from the pool of the passenger set and ensure
	// that it updates the set so as to avoid picking the same passenger again
	public void puti() throws InterruptedException {
		System.out.println("Passengers in Check-in queue");
		Passenger p1;

		while (true) {
			synchronized (lock) {

				// inside the queue place a random customer from the set
				p1 = customers1.rando();

				// add passenger and baggage in array list q
				
					q.add(p1);
				

				System.out.format(p1.getpClass() + " passenger," + p1.getFullName() + " travelling by flight "
						+ p1.getflightCode() + " is in the check-in queue.");
				System.out.println();

				while (q.size() == LIMIT) {
					lock.wait();
				}
				String name = p1.getFullName();

				// this will print to the log report of programme execution
				Log log = Log.getInstance();
				log.logCheckInQueue(name);

				// remove the passenger from the set to avoid duplication
				if (customers1.cont(p1)) {
					customers1.removeOb(p1);
					System.out.println("There are currently " + q.size() + " In the queue");
				}
				lock.notifyAll();
			}
		}
	}

	public void forEconomyCheckin() {

		try {
			sema.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			EconomyPicker();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			sema.release();

		}
	}

	public void forEconomyCheckinLock() throws InterruptedException {
		lock1.lock();
		con.signalAll();

		try {
			EconomyPicker();
		}

		finally {
			lock1.unlock();

		}
	}

	public void forFirstBusinessCheckinLock() throws InterruptedException {
		lock1.lock();
		con.await();

		try {
			BusinessFirstPicker();
		}

		finally {
			lock1.unlock();

		}
	}

	public void forFirstBusinessCheckin() {

		try {
			sema.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			BusinessFirstPicker();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			sema.release();

		}
	}

	// method to pick Economy class passenger form the queue q
	public Passenger EconomyPicker() throws InterruptedException {

		Random random = new Random();

		synchronized (lock) {
			while (true) {

				try {
					
					if (q.size() == 0) {
						lock.wait();
					}

					for (int i = 0; i <= q.size(); i++) {

						//Passenger p = (Passenger) q.get(i);
									
						Passenger p= q.removeFirst();
						//Passenger p= q.getFirst();
						
						if (p.getpClass().equalsIgnoreCase("economy")) {
							
							
											
							
							
							Baggage b = p.createAbag(p);
							p.setCheckInStatus(true);
							Checkedin.add(p);

							System.out.println(p.getpClass() + " class passenger, " + p.getFullName()
									+ " travelling by " + p.getflightCode() + " is Checking in at Economy Class "
									+ "Check-in Desk");

							System.out.println("The check-in bag weight is " + b.getWeight()
									+ " Kg and bag dimensions are " + b.getHeight() + " X " + b.getBreadth() + " X "
									+ b.getWidth() + " cubic inches. Therefore, the baggage " + "volume is "
									+ b.baggageVolume() + " cubic inches");

							System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight() + " kg.");

							System.out.println("The check-in bag exceeds volume allowance by " + b.excessBaggageVolume()
									+ " cubic inches.");

							System.out.println("The excess baggage fee is " + b.excessBaggageFee() + " GBP.");

							System.out.println("Total Economy class passengers checked in is " + Checkedin.size());

							String passengerClass = p.getpClass();
							String name = p.getFullName();
							String flightCode = p.getflightCode();

							Log log = Log.getInstance();
							log.logCheckInDeskEconomy(passengerClass, name, flightCode);

							// remove the passenger from the set to avoid duplication
							if (q.contains(p)) {
								q.remove(p);

							}

							lock.notifyAll();

						}
					}
				} catch (NullPointerException | NoSuchElementException N) {
					System.out.println("No economy class passengers waiting to be checked-in");
				}

				Thread.sleep(random.nextInt(1000));
			}

		}

	}

	// method to pick Economy class passenger form the queue q
	public Passenger BusinessFirstPicker() throws InterruptedException {

		Random random = new Random();
		while (true) {
			synchronized (lock) {

				while (q.size() == 0) {
					lock.wait();
				}

				for (int i = 0; i <= q.size(); i++) {

					try {
						// Passenger p = (Passenger) q.get(i);
						Passenger p = q.removeFirst();

						// while (!p.getpClass().equalsIgnoreCase("first") ||
						// !p.getpClass().equalsIgnoreCase("business") ) {
						// lock.wait(200);
						// }

						if (p.getpClass().equalsIgnoreCase("first") || p.getpClass().equalsIgnoreCase("business")) {
							Baggage b = p.createAbag(p);
							p.setCheckInStatus(true);
							Checkedin.add(p);

							System.out.println(p.getpClass() + " class passenger, " + p.getFullName()
									+ " travelling by " + p.getflightCode()
									+ " is Checking in at Business & First Class " + "Check-in Desk");

							System.out.println("The check-in bag weight is " + b.getWeight() + " Kg and volume is "
									+ b.baggageVolume() + " cubic inches");

							System.out.println("The check-in bag is overweight by " + b.excessBaggageWeight() + " kg.");

							System.out.println("The check-in bag exceeds volume allowance by " + b.excessBaggageVolume()
									+ " cubic inches.");

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
							lock.notifyAll();
						}
					} catch (NullPointerException | NoSuchElementException N) {
						System.out.println("No First or Business class passengers waiting to be checked-in");
					}

					finally {
						lock.notifyAll();

					}

				}
				Thread.sleep(random.nextInt(10));

			}
		}
	}

	public LinkedList<Passenger> getQ() {
		return q;
	}

	public LinkedList<Passenger> getQ1() {
		return Checkedin;
	}

	public ArrayList<Passenger> getQ2() {
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
