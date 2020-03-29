package Model;

public class EconomyCheckIn1Runnable implements Runnable {
	
		PassengersInQueue q;
		  public EconomyCheckIn1Runnable(PassengersInQueue  c) {
				q=c;
			}
			
			public void run() {
				Passenger pas = null;
				
				try {
					Thread.sleep(6000);
					pas=  q.EconomyPicker();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 

}
