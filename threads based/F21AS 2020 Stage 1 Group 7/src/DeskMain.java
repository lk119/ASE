import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DeskMain {

	// private PassengerSet customers = new PassengerSet();
	public static void main(String[] args) throws InterruptedException {
		PassengersInAqueue pq = new PassengersInAqueue();

		Thread thread1 = new Thread(new Consumer(pq));
		Thread thread3 = new Thread(new EcnomyRunnableTask(pq));
		Thread thread4 = new Thread(new FirstRunnable(pq));
         Thread thread5 = new Thread(new FlightCounterThread(pq));
		Thread thread2 = new Thread(new Producer(pq));
		thread1.start();
		thread5.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		 

		
	
		 
		 
		
	}

}
