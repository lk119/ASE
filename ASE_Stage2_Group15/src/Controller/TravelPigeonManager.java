package Controller;

import java.util.Scanner;
import Model.BoardingRunnable;
import Model.Consumer;
import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstandBusinessCheckInRunnable;
import Model.FlightCounterThread;
import Model.FlightList;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;
import Model.Producer;
import Model.SecurityRunnable;
import View.GUIMain;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of existing
 * of passenger bookings 
 * Runs the GUI and check-in threads 
 *  
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */


public class TravelPigeonManager {
	/**
	 * Reads the input files and creates the first instance of the GUI
	 * @throws InterruptedException 
	 */
	
	public void run() throws InterruptedException {
		
		        // read passenger input file
				PassengerSet p = new PassengerSet();
				p.readFile("passengers");
				
				
				//shared object in producer/consumer model
				PassengersInQueue q = new PassengersInQueue();
				
				
						
				//to create passenger map
				p.createAMap();
				
				
				
				
				
				//FlightCounterThread Model = new FlightCounterThread(q);
				
				
				Thread thread1 = new Thread(new Consumer(q));
				Thread thread2 = new Thread(new Producer(q));
         		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(q));
         		Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(q));
                Thread thread5 = new Thread(new EconomyCheckIn2Runnable(q));
                Thread thread6 = new Thread(new EconomyCheckIn3Runnable(q));
                Thread thread7 = new Thread(new SecurityRunnable(q));
                Thread thread8 = new Thread(new BoardingRunnable(q));
               
                
         		
         		thread1.start();
         		thread5.start();
         		thread2.start();
         		thread3.start();
         		thread4.start();
         		thread6.start();
         		thread7.start();
         		thread8.start();
         		
         		
         		
         		thread1.join();
         		thread2.join();
         		thread3.join();
         		thread4.join();
         		thread5.join();
         		thread6.join();
         		thread7.join();
         		thread8.join();
         		
         		
								
				
				// read flight input file
				FlightList f = new FlightList();
				f.readFile("flight");
				
				// open the scanner
				Scanner sc = new Scanner(System.in);
				
				// close the scanner
				sc.close();

							
				// create new instance of GUIMain
				//the View
				//GUIMain View = new GUIMain (Model);
				
				// create new instance of GUIController
				//the Controller
				//GUIController Controller = new GUIController (Model, View);
				
						  
		        // View.setVisible(true);
	
		         

		        
		         		

		         		
		         		 

		         		
		         	
		         		 
		         		 
		         	
	}

}
