package Model;

import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import View.GUIMain;

// This is our task for taking out the business class people.
public class Producer extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	PassengersInQueue q;

	public Producer(PassengersInQueue c) {
		q = c;
	}

	public boolean isFinished() {
		return Finish;
	}
	
	//indicates end of auction
	public void setFinished() {
		Finish = true;

	}

	public void run() {
		Passenger pas = null;
		
			try {
				Thread.sleep(2000);
				pas = q.BusinessPicker();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Finish = true;
		}

	}
