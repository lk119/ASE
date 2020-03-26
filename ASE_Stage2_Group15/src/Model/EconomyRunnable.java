package Model;

public class EconomyRunnable implements Runnable {

PassengersInQueue q;
  public EconomyRunnable(PassengersInQueue  c) {
		q=c;
	}
	
	public void run() {
		Passenger pas = null;
		
		try {
			Thread.sleep(3000);
			pas=  q.EconomyPicker();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 

}
