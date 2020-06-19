package Model;

import java.util.Observable;

import View.GUIMain;

public class EconomyCheckIn1Runnable extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;

	PassengersInQueue q;

	public EconomyCheckIn1Runnable(PassengersInQueue c) {
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
	
			try {
				Thread.sleep(1000);
				
				System.out.println("Passengers at Economy Desk 1");
				pas = q.EconomyPicker();
				//q.forEconomyCheckinLock();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println("ECONOMY CHECK-IN DESK2 FINISHED");
				//Finish = true;
		}

	}

