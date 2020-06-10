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
		// TODO Auto-generated method stub
		
	}
	

    	

}