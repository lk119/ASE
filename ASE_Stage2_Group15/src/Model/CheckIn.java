package Model;

import java.util.HashMap;
import java.util.Observable;

public class CheckIn extends Observable implements Runnable {
	
	private PassengersInQueue q;

	public CheckIn(PassengersInQueue q) {
		this.q = q;
	}

	
	public void run() {
		while (!q.getDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			HashMap<String,String> number = q.get();

		}
	}
	
	
	
	
	
	
}
