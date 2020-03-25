
public class EcnomyRunnableTask implements Runnable {
	PassengersInAqueue q;
  public EcnomyRunnableTask(PassengersInAqueue  c) {
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
