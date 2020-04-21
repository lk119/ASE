package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstandBusinessCheckInRunnable;
import Model.Log;
import Model.BoardingRunnable;
import Model.Consumer;
import Model.SecurityRunnable;
import Model.PassengersInQueue;
import Model.Passenger;
import Model.PassengerSet;
import Model.Producer;

import View.GUIMain;
import View.GUIReport;




public class GUIController {

	private Passenger passenger;
	private PassengersInQueue passengersinqueue; 
	private PassengerSet passengerset;
	private EconomyCheckIn1Runnable desk1;
	private EconomyCheckIn2Runnable desk2;
	private EconomyCheckIn3Runnable desk3;
	private FirstandBusinessCheckInRunnable FirstDesk;
	private SecurityRunnable security;
	private BoardingRunnable boarding;
	private GUIMain view1;
	private GUIReport view2;
	private Consumer con;
	private Producer prod;
	private Log log;
	
	
	
	
	
	public GUIController(PassengersInQueue q, EconomyCheckIn1Runnable d1, EconomyCheckIn2Runnable d2, EconomyCheckIn3Runnable d3,
			FirstandBusinessCheckInRunnable f, SecurityRunnable s, BoardingRunnable b, 		
			Consumer c, Producer pd, GUIMain v1, GUIReport v2) {
					
		    //passenger = p;
		    passengersinqueue = q;
		    desk1 = d1;
		    desk2 = d2;
		    desk3 = d3;
		    FirstDesk = f;
		    security = s;
		    boarding = b;
			view1 = v1;
			view2 = v2;
			con = c;
			prod = pd;
			//this.log = log;
			view1.addGUIMainListener(new GUIMainController());
			//view2.addGUIReportListener(new GUIReportController());
			
					
			
			
		}
			
	    
	    class GUIMainController  implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	//view1.setVisible(true);       	      	
	        	view1.disableProcessButton();
	        	Thread thread1 = new Thread(new Consumer(passengersinqueue));
				Thread thread2 = new Thread(new Producer(passengersinqueue));
         		Thread thread3 = new Thread(new EconomyCheckIn1Runnable(passengersinqueue));
         		Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(passengersinqueue));
                Thread thread5 = new Thread(new EconomyCheckIn2Runnable(passengersinqueue));
                Thread thread6 = new Thread(new EconomyCheckIn3Runnable(passengersinqueue));
                Thread thread7 = new Thread(new SecurityRunnable(passengersinqueue));
                Thread thread8 = new Thread(new BoardingRunnable(passengersinqueue));
                
                thread1.start();
         		thread5.start();
         		thread2.start();
         		thread3.start();
         		thread4.start();
         		thread6.start();
         		thread7.start();
         		thread8.start();
         		
         		try {
					thread1.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread2.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread3.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread4.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread5.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread6.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread7.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		try {
					thread8.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
         		
         		view1.redirectSystemStreams();
         		
         		
	        }
		
	}
	    
	    class GUIReportController implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	        	   		
	    
	        }
	        
	     
	    }
	    
	    
	    
	    
	    
	    
	    

}
