package Model;

import java.util.Observable;

import View.GUIMain;

public class FirstandBusinessCheckInRunnable extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	PassengersInQueue q;

	public FirstandBusinessCheckInRunnable(PassengersInQueue c) {
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

		Passenger pas = null;
		
			try {
				Thread.sleep(1000);
				//pas = q.firstPicker();
				pas = q.BusinessFirstPicker();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}

	}

