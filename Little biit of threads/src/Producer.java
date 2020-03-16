import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	PassengersInAqueue q;
	public Producer(PassengersInAqueue  c) {
		q=c;
	}
	
	public void run() {
	
		try {
			Thread.sleep(2000);
			q.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 

}
