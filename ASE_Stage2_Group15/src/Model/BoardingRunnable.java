package Model;

public class BoardingRunnable implements Runnable {

	PassengersInQueue q;

	public BoardingRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(5000);
			pas=  q.PickerforBoarding();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
