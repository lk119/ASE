package Model;

public class FlightCounterThread implements Runnable{
	PassengersInQueue q;
	public FlightCounterThread(PassengersInQueue  c) {
		q=c;
		
		
	}
	@Override
	public void run() {
		System.out.println("I will just be updating flights as passengers board the plane");
		
		
		try {
			Thread.sleep(30000);
			q.placer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
