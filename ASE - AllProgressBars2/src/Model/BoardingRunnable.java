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
	

    	

	@Override
	public void run() {
		LinkedList<Passenger> pas = null;
		 
		
	while(true) {	
		
			try {
			Thread.sleep(2000);
			q.boardingPicker();
			
		
						
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}}
}