package Model;

public class SecurityRunnable implements Runnable {

	PassengersInQueue q;

	public SecurityRunnable(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		Passenger pas = null;
	
		try {
			Thread.sleep(15000);
			pas=  q.PickerforSecurity();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
