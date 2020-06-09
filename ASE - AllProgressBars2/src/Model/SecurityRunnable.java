package Model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.TimerTask;

import View.GUIMain;

public class SecurityRunnable extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	PassengersInQueue q;

	public SecurityRunnable(PassengersInQueue c) {
		q = c;

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
		LinkedList<Passenger> pas = null;
		
while(true) {
			try {
				Thread.sleep(2000);
				pas = q.securityPicker() ;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}}
	}


