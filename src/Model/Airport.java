package Model;

import java.util.Observable;

public class Airport extends Observable implements Runnable {

	private boolean finished = false;

	PassengersInQueue q;

	public Airport(PassengersInQueue c) {
		q = c;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished() {
		finished = true;

	}

	@Override
	public void run() {

		if (Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(1000000000);
			} catch (InterruptedException y) {
			}
		}

		Thread thread1 = new Thread(new EcoProducer(q));
		Thread thread2 = new Thread(new FirstProducer(q));
		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(q));
		Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(q));
		Thread thread5 = new Thread(new EconomyCheckIn2Runnable(q));
		Thread thread6 = new Thread(new EconomyCheckIn3Runnable(q));
		Thread thread7 = new Thread(new SecurityRunnable(q));
		Thread thread8 = new Thread(new BoardingRunnable(q));

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();

		/*
		 * try { thread1.join(); thread2.join(); thread3.join(); thread4.join();
		 * thread5.join(); thread6.join(); thread7.join(); thread8.join();
		 * 
		 * } catch (InterruptedException e) {}
		 */

	}

}
