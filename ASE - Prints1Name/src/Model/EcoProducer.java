package Model;


import java.util.Observable;
import java.util.TimerTask;


import View.GUIMain;

public class EcoProducer extends Observable implements Runnable {

	private GUIMain view1;
	private boolean Finish = false;
	PassengersInQueue q;
	
	
	
	public EcoProducer(PassengersInQueue c) {
		q = c;
	}


	public boolean isFinished() {
		return Finish;
	}
	


	@Override
	public void run() {
		
		
			try {
				Thread.sleep(300);
				q.economyQueueputi();
				
			} catch (NullPointerException | InterruptedException N) {
				System.out.println("The economy check-in queue is empty");

			}
			
			//Finish = true;
		}

	}

