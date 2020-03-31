package Model;

public class FirstandBusinessCheckInRunnable implements Runnable {

	PassengersInQueue q;

	public FirstandBusinessCheckInRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(1000);
			pas=  q.firstPicker();
			pas=  q.BusinessPicker();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
