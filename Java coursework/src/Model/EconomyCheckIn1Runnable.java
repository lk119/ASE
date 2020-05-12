package Model;

import java.util.Observable;

import View.GUIMain;
/*
 * util. Observable is used to create subclasses that other parts of the program can observe. 
 * When an object of such subclass undergoes a change, observing classes are notified. 
 * The update( ) method is called when an observer is notified of a change.
 */


/*
 * lang. Runnable is an interface that is to be implemented by a class whose instances are intended to be executed by a thread
 */
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
/*
 * In this method, we have the code which we want to execute on a concurrent thread.
 * initially the passenger object is null while the thread sleeps ffor some time
 * The passenger is selected from teh queue using the economy picker method in the PassenngerInQue Class
 */
	public void run() {
		Passenger pas = null;
		
			try {
				Thread.sleep(20000);
				pas = q.EconomyPicker();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("ECONOMY CHECK-IN DESK1 FINISHED");
            
            
			Finish = true;
		}

	}

