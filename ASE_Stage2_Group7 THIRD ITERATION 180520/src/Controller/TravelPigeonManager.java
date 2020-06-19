package Controller;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Model.BoardingRunnable;
import Model.EcoProducer;
import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstProducer;
import Model.FirstandBusinessCheckInRunnable;

import Model.FlightList;
import Model.InvalidBookingReference;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;

import Model.SecurityRunnable;
import Model.Time;
import Model.Time1;
import View.GUIMain;
import View.GUIReport;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of
 * existing of passenger bookings Runs the GUI and check-in threads
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {
	/**
	 * Reads the input files and creates the first instance of the GUI
	 * 
	 * @throws InterruptedException
	 * @throws InvalidBookingReference
	 */
	long howLongTLeaveGatesOpen=50;
      Long mainWorkerBreakTime= howLongTLeaveGatesOpen*1000;
	public void run() throws InterruptedException {

		// read passenger input file
		PassengerSet ps = new PassengerSet();
		ps.readFile("passengers");

		// shared object in producer/consumer model
		PassengersInQueue q = new PassengersInQueue();
		
		   Time1 depture= new Time1();
         
		// models
		PassengersInQueue model1 = new PassengersInQueue();
		EconomyCheckIn1Runnable model2 = new EconomyCheckIn1Runnable(model1);
		EconomyCheckIn2Runnable model3 = new EconomyCheckIn2Runnable(model1);
		EconomyCheckIn3Runnable model4 = new EconomyCheckIn3Runnable(model1);
		FirstandBusinessCheckInRunnable model5 = new FirstandBusinessCheckInRunnable(model1);
		SecurityRunnable model6 = new SecurityRunnable(model1);
		BoardingRunnable model7 = new BoardingRunnable(model1);
		EcoProducer model8 = new EcoProducer(model1);
	

		// view
		// GUIMain view1 = new GUIMain (model1, null, model2, model3, model4, model5,
		// model6, model7, model8);
		//GUIReport view2 = new GUIReport(model1);

		// controller
		// GUIController controller = new GUIController(model1, model2, model3, model4,
		// model5, model6, model7, model8, view1, view2);
		// view1.setVisible(true);

		// read flight input file
		FlightList f = new FlightList();
		f.readFile("flight");

		// open the scanner
		Scanner sc = new Scanner(System.in);

		// close the scanner
		sc.close();
		
	
	

		Thread thread1 = new Thread(new EcoProducer(q));
		Thread thread2 = new Thread(new FirstProducer(q));
		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(q));
		Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(q));
		Thread thread5 = new Thread(new EconomyCheckIn2Runnable(q));
		Thread thread6 = new Thread(new EconomyCheckIn3Runnable(q));
		Thread thread7 = new Thread(new SecurityRunnable(q));
		
	   Thread main=Thread.currentThread();
	   main.setPriority(10);
		Thread thread8 = new Thread(new BoardingRunnable(q));
		


		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		depture.start();
		
		//Gates will close approximately 10 seconds after the time 
		
		Thread.sleep(mainWorkerBreakTime);
		System.out.println("Gaates closing sooooooooooooooooooooooooooooooooooooooooooooooon");
		System.out.println("Elapse:"+depture.getPassed());
	    if(depture.getPassed()==howLongTLeaveGatesOpen) {
	    	thread1.interrupt();
	    	thread2.interrupt();
	    	thread3.interrupt();
	    	thread4.interrupt();
	    	thread5.interrupt();
	    	thread6.interrupt();
	    	thread7.interrupt();
	    	thread8.interrupt();
	       	
            Thread.sleep(10000);
            
	    	depture.cancel();

	    	System.out.println("Gates closed please go home");
	    	
	    	System.exit(0);
	    }


	

	}

}