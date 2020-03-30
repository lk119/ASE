package Model;

public class FirstandBusinessBoardingRunnable implements Runnable {

	PassengersInQueue q;

	public FirstandBusinessBoardingRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(1500);
			pas=  q.firstPickerforBoarding();
			pas=  q.BusinessPickerforBoarding();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
