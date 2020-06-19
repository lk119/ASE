package Model;

import java.util.Iterator;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import View.GUIMain;

public class FirstProducer extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	/*
	 * PassengerSet customers; FlightList planes; volatile BlockingQueue<Passenger>
	 * q= new ArrayBlockingQueue<Passenger>(20);
	 */
	PassengersInQueue q;
	
	public FirstProducer(PassengersInQueue c) {
		q = c;
	}

	
	public boolean isFinished() {
		return Finish;
	}
	


	@Override
	public void run() {
		
		
			try {
				Thread.sleep(300);
				q.firstbusinessQueueputi();

			} catch (NullPointerException | InterruptedException N) {
				System.out.println("The first and business class check-in queue is empty");

			}
			
			//Finish = true;
		}

	}


