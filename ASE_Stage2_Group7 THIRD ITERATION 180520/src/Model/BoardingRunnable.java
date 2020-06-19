package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import View.GUIMain;

public class BoardingRunnable extends Observable implements Runnable {
	
	private GUIMain view1;
	private boolean Finish = false;
    Timer timer= new Timer();
	PassengersInQueue q;

	public BoardingRunnable(PassengersInQueue  c) {
		q=c;
			
	}
	
	public boolean isFinished() {
		return Finish;
	}
	
	//indicates end of auction
	public void setFinished() {
		Finish = true;

	}
	
    	
	
	@Override
	public void run() {
		Passenger pas = null;
		
			try {
			   //Time1 depture= new Time1();
			 //  depture.start();
			// int kuda=  depture.getPassed();
			 //System.out.println("time passed: "+ kuda);
			Thread.sleep(2000);
			
			pas=  q.boardingPicker();
			
			//update view display
			setChanged();
			notifyObservers();
	    	clearChanged();
						
		 }catch (InterruptedException e) {
			e.printStackTrace();
		}
			//System.out.println("BOARDING FINISHED");
			//Finish = true;
			
	}
	
	
	
	

	
	
}