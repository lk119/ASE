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



import Model.FlightList;
import Model.InvalidBookingReference;
import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;



import View.GUIMain;


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


		ps.readFile("passengers");

		// open the scanner
		Scanner sc = new Scanner(System.in);

		// close the scanner
		sc.close();

		// models
		PassengersInQueue model = new PassengersInQueue();
		
		
		// view
		GUIMain view1 = new GUIMain(model, null);
		

		// controller
		GUIController controller = new GUIController(model,  view1);
		view1.setVisible(true);

		// GUIDemo GUIDemo = new GUIDemo(toString());view2 = v2;
		
		
		
		
//		Timer airportTimer = new Timer();
//		
//	
//		    	   
//		Thread airportthread = new Thread(new Airport(model1));
//		   
//	    airportTimer.schedule(new TimerTask() {
//
//		@Override
//		public void run() {
//			
//		 System.out.println("GATES CLOSING SOON");
//        
//        try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {}
//			
//       System.out.println("FINAL BOARDING CALL"); 
//
//       
//       System.out.println("GATES CLOSED"); 
//        
//               
//	         
//       System.exit(0);
//           
//   	
//      
//      //Runtime.getRuntime().exit(0);	
//      //Runtime.getRuntime().halt(0);
//      
//		}
//		   
//		   
//		   
//	   }, 60000);
//		
//		
//	   airportthread.start();
//
//		
//
//		
//
//		

	

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
