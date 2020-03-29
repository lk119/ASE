package Model;

public class EconomyCheckIn2Runnable implements Runnable {
	
	PassengersInQueue q;
	  public EconomyCheckIn2Runnable(PassengersInQueue  c) {
			q=c;
		}
		
		public void run() {
			Passenger pas = null;
			
			try {
				Thread.sleep(30000);
				pas=  q.EconomyPicker();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 

}
