package Model;

public class EconomyBoardingRunnable implements Runnable {
	
	PassengersInQueue q;

	public EconomyBoardingRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(40000);
			pas=  q.EconomyPickerforBoarding();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
