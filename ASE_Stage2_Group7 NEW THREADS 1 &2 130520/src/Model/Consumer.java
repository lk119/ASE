package Model;

import java.util.Iterator;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import View.GUIMain;

public class Consumer extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	/*
	 * PassengerSet customers; FlightList planes; volatile BlockingQueue<Passenger>
	 * q= new ArrayBlockingQueue<Passenger>(20);
	 */
	PassengersInQueue q;
	
	public Consumer(PassengersInQueue c) {
		q = c;
	}

	/*
	 * public void run() { customers.readFile("passengers");
	 * planes.readFile("flights"); while(true) {
	 * System.out.println(customers.rando()); try { q.put(customers.rando()); }
	 * catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * System.out.println("THE queue SIZE IS " + q.size());
	 * 
	 * }
	 * 
	 * }
	 */
	public boolean isFinished() {
		return Finish;
	}
	


	@Override
	public void run() {
		
		
			try {
				Thread.sleep(300);
				q.puti();

			} catch (NullPointerException | InterruptedException N) {
				System.out.println("The queue is empty");

			}
		}

	}

