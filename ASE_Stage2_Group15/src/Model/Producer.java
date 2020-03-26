package Model;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

// This is our task for taking out the business class people.
public class Producer implements Runnable {
	PassengersInQueue q;

	public Producer(PassengersInQueue c) {
		q = c;
	}

	public void run() {
		Passenger pas = null;
		
		try {
			Thread.sleep(2000);
			pas = q.BusinessPicker();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
