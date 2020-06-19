package Model;

import java.util.Observable;
import java.util.TimerTask;

import View.GUIMain;

public class EconomyCheckIn2Runnable extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	PassengersInQueue q;

	public EconomyCheckIn2Runnable(PassengersInQueue c) {
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
		
while(true) {
			try {
				Thread.sleep(1000);
				q.EconomyPicker();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           // System.out.println("ECONOMY CHECK-IN DESK2 FINISHED");
			//Finish = true;
		}
	}
	}
