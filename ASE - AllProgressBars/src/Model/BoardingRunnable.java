package Model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.TimerTask;

import View.GUIMain;

public class BoardingRunnable extends Observable implements Runnable {
	
	private GUIMain view1;

	
	    
	PassengersInQueue q;

	public BoardingRunnable(PassengersInQueue  c) {
		q=c;
	
			
	}
	

    	
	long start = System.currentTimeMillis();
	// some time passes
	long end = System.currentTimeMillis();
	long elapsedTime = end - start;
	
	@Override
	public void run() {
		LinkedList<Passenger> pas = null;
		 
		
		
		
			try {
			Thread.sleep(2000);
			pas=  q.boardingPicker();
			System.out.println(elapsedTime);
			
		
						
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}