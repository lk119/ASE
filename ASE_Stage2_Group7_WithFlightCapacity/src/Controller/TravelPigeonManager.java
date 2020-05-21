package Controller;

import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Model.Airport;
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
import View.GUIDemo;
import View.GUIMain;
import View.GUIReport;

/**
 * Advanced Software Engineering - Coursework Stage 2 Reads the input files of
 * existing of passenger bookings Runs the GUI and check-in threads
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {

	public void addpopup () {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "GATES CLOSED.");
	}
	
	


	public void run() throws InterruptedException {

		// read passenger input file
		PassengerSet ps = new PassengerSet();

		// shared object in producer/consumer model
		PassengersInQueue q = new PassengersInQueue();

		ps.readFile("passengers");

		// open the scanner
		Scanner sc = new Scanner(System.in);

		// close the scanner
		sc.close();

		// models
		PassengersInQueue model1 = new PassengersInQueue();
		EconomyCheckIn1Runnable model2 = new EconomyCheckIn1Runnable(model1);
		EconomyCheckIn2Runnable model3 = new EconomyCheckIn2Runnable(model1);
		EconomyCheckIn3Runnable model4 = new EconomyCheckIn3Runnable(model1);
		FirstandBusinessCheckInRunnable model5 = new FirstandBusinessCheckInRunnable(model1);
		SecurityRunnable model6 = new SecurityRunnable(model1);
		BoardingRunnable model7 = new BoardingRunnable(model1);
		EcoProducer model8 = new EcoProducer(model1);
		FirstProducer model9 = new FirstProducer(model1);
		
		
		// view
		//GUIMain view1 = new GUIMain(model1, null, model2, model3, model4, model5,
		//model6, model7, model8, model9);
		// GUIReport view2 = new GUIReport(model1);

		// controller
		// GUIController controller = new GUIController(model1, model2, model3, model4,
		// model5, model6, model7, model8,
		// model9, view1);
		//view1.setVisible(true);

		// GUIDemo GUIDemo = new GUIDemo(toString());view2 = v2;
		
		
		
		
		Timer airportTimer = new Timer();
		
	
		    	   
		Thread airportthread = new Thread(new Airport(model1));
		   
	    airportTimer.schedule(new TimerTask() {

		@Override
		public void run() {
			
		 System.out.println("GATES CLOSING SOON");
        
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {}
			
       System.out.println("FINAL BOARDING CALL"); 

       
       System.out.println("GATES CLOSED"); 
        
               
	         
       System.exit(0);
           
   	
      
       //Runtime.getRuntime().exit(0);	
       //Runtime.getRuntime().halt(0);
      
		}
		   
		   
		   
	   }, 60000);
		
		
	   airportthread.start();

		

		

		

	

	}

	/*
	 * try {
	 * 
	 * Thread.sleep(2000);
	 * 
	 * }catch (InterruptedException ex) {}
	 * 
	 * 
	 * timer.cancel();
	 */

	/*
	 * timer2.schedule(new TimerTask() {
	 * 
	 * @Override public void run() { System.out.println("TIME CHECK"); Thread
	 * thread2 = new Thread(new FirstProducer(q)); thread2.start(); try {
	 * thread2.join(); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }, 10, 60000);
	 */
	/*
	 * timer3.schedule(new TimerTask() {
	 * 
	 * @Override public void run() { System.out.println("TIME CHECK"); Thread
	 * thread3 = new Thread(new EconomyCheckIn1Runnable(q)); thread3.start(); try {
	 * thread3.join(); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }, 10, 60000);
	 */

	/*
	 * SwingUtilities.invokeLater(new Runnable() {
	 * 
	 * @Override public void run() { // new GUIDemo (null);
	 * 
	 * }
	 * 
	 * });
	 */
}
