package Model;

public class EconomySecurityRunnable implements Runnable {

	PassengersInQueue q;

	public EconomySecurityRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(30000);
			pas=  q.EconomyPickerforSecurity();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
