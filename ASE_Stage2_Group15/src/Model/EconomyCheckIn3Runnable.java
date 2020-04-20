package Model;

public class EconomyCheckIn3Runnable implements Runnable {
	PassengersInQueue q;
	  public EconomyCheckIn3Runnable(PassengersInQueue  c) {
			q=c;
		}
		
		public void run() {
			Passenger pas = null;
			
			try {
				Thread.sleep(50000);
				pas=  q.EconomyPicker();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 

}
