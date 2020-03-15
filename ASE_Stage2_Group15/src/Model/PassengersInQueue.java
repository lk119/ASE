package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class PassengersInQueue {

	private ArrayList<Passenger> PassengersInQueue;
	
	private PassengerSet ps = new PassengerSet();
	private HashMap<String,String> p;
	
	private boolean empty;
	private boolean done;

	

	/**
	 * creates an ArrayList
	 */
	public PassengersInQueue() {
		PassengersInQueue = new ArrayList<Passenger>();
		empty = true;
		done = false;

	}

	public int size() {
		return PassengersInQueue.size();
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized HashMap<String,String> get() {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got: " + p);
		empty = true;
		notifyAll();
		return p;
	}

	// wait while number there
	// when waiting over, put number
	// set empty to false and notify waiting methods
	public synchronized void put(HashMap<String,String> flyers) {

		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put: " + flyers);
		empty = false;
		notifyAll();
		this.p = flyers;
	}

	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}

	

	
}
