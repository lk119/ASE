package Model;

import java.util.Iterator;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import View.GUIMain;

/*
 * util. Observable is used to create subclasses that other parts of the program can observe. 
 * When an object of such subclass undergoes a change, observing classes are notified. 
 * The update( ) method is called when an observer is notified of a change.
 */
/*
 * lang. Runnable is an interface that is to be implemented by a class whose instances are intended to be executed by a thread
 */
public class Consumer extends Observable implements Runnable {
	// instance variables
	private GUIMain view1;
	private boolean Finish = false;

	/*
	 * PassengerSet customers; FlightList planes; volatile BlockingQueue<Passenger>
	 * q= new ArrayBlockingQueue<Passenger>(20);
	 */
	PassengersInQueue q;

	/*
	 * This is the constructor of the class
	 */
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

	// indicates end of auction
	public void setFinished() {
		Finish = true;

	}
	
	/*
	 * In this method, we have the code which we want to execute on a concurrent thread. In this method, we can use variables,
	 *  instantiate classes, and perform an action like the same way the main thread does. 
	 * The thread remains until the return of this method. The run method establishes an entry point to a new thread.
	 */

	@Override
	public void run() {

		try {

			q.puti();

		} catch (NullPointerException N) {
			System.out.println("The queue is empty");

		}
		//Finish = true;
	}

}
