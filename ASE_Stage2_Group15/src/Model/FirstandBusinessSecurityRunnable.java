package Model;

public class FirstandBusinessSecurityRunnable implements Runnable {

	PassengersInQueue q;

	public FirstandBusinessSecurityRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(1500);
			pas=  q.firstPickerforSecurity();
			pas=  q.BusinessPickerforSecurity();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
