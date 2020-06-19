package Model;

import java.util.Observable;
import java.util.TimerTask;

import View.GUIMain;

public class EconomyCheckIn3Runnable extends Observable implements Runnable {
	
	private GUIMain view1;
	private boolean Finish = false;
	
	PassengersInQueue q;
	  public EconomyCheckIn3Runnable(PassengersInQueue  c) {
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
			// TODO Auto-generated method stub
			
		}

		


}
