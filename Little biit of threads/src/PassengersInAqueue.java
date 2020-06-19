
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PassengersInAqueue {

	private volatile BlockingQueue<Passenger> q = new ArrayBlockingQueue<Passenger>(5);
	// start by populating the pool where the put thread will get customers
	PassengerSet customers1 = new PassengerSet().populate();

	
	
	// This method will add a peron from the pool of the customer set and ensure that it updates the set so ass to avoid pickingthe same customer again.
	public void puti()

	{

		// current limitation of the method is that it does not eliminate the custoomer
		// from the set to avoid picking that person again.

		try {
			System.out.println("Now we are inside thread1");

			while (true) {
				// inside the the queue place a random customer from the set
				Passenger p1 = customers1.rando();
				q.put(p1);
				// Lets make sure this method also deletes this person from the set
				System.out.println("Previous number before the deletion is " + customers1.totalcustomers());
				System.out.println();
				if(customers1.cont(p1)) {
				customers1.removeOb(p1);
				System.out.println("the removed object from the set is"+ p1 + "new size of the set is " + customers1.totalcustomers() );
				}
						//System.out.println(Arrays.toString(q.toArray()));

				// System.out.println(q);
				//System.out.println("above is the queue after placing one customer");

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("THE queue SIZE IS " + q.size());

	}

	public void take() {
		Random random = new Random();

		// if (random.nextInt(10) == 0) { try {

		Passenger p;
		try {
			System.out.println("now we are in thread2");
			System.out.println("Below is the queue just before  taking");
			System.out.println(q);
			
			while(true) {
			p = q.take();
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHE PERSON WAS TAKEN OUT:" + p.getFullName());

		}
			//System.out.println(q);
			//System.out.println("tabove is the queue after  taking");
			//System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTHE PERSON WAS TAKEN OUT:" + p.getFullName());
			//System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRemaining:" + q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BlockingQueue<Passenger> getQ() {
		return q;
	}

	public String printAll() {

		String name = "";
		for (Passenger p : q) {
			name = p.getFullName();
		}

		return name;
	}

}
