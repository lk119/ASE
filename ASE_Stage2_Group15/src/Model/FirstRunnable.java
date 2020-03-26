package Model;

public class FirstRunnable implements Runnable {
	PassengersInQueue q;

	public FirstRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(1500);
			pas=  q.firstPicker();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
