
public class FirstRunnable implements Runnable {
	PassengersInAqueue q;

	public FirstRunnable(PassengersInAqueue  c) {
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
