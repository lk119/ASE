package Model;

import java.util.Observable;

import View.GUIMain;

public class BoardingRunnable extends Observable implements Runnable {
	
	private GUIMain view1;
	private boolean Finish = false;
	
	    
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
			Thread.sleep(8000);
			pas=  q.PickerforBoarding();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			System.out.println("BOARDING FINISHED");
			Finish = true;
	}
}