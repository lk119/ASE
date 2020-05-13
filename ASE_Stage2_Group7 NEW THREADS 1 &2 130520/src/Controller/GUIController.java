package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

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
	
	private Log log;
	
	
	
	
	
	public GUIController(PassengersInQueue q, EconomyCheckIn1Runnable d1, EconomyCheckIn2Runnable d2, EconomyCheckIn3Runnable d3,
			FirstandBusinessCheckInRunnable f, SecurityRunnable s, BoardingRunnable b, 		
			Consumer c, GUIMain v1, GUIReport v2) {
					
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
			//prod = pd;
			//this.log = log;
			view1.addGUIMainListener(new GUIMainController());
			//view2.addGUIReportListener(new GUIReportController());
			
					
			
			
		}
			
	
		
	    
	    class GUIMainController  implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	//view1.setVisible(true);  
	        	
	        	//main method that will trigger the programme
	        	view1.disableProcessButton();
	        	
	        	  Thread thread1 = new Thread(new Consumer(passengersinqueue));
			      Thread thread2 = new Thread(new EconomyCheckIn1Runnable(passengersinqueue));
         		//Thread thread4 = new Thread(new FirstandBusinessCheckInRunnable(passengersinqueue));
                //Thread thread5 = new Thread(new EconomyCheckIn2Runnable(passengersinqueue));
                //Thread thread6 = new Thread(new EconomyCheckIn3Runnable(passengersinqueue));
				//Thread thread7 = new Thread(new SecurityRunnable(passengersinqueue));
	        	
				   
	         		thread1.start();
			     //thread5.start();
	         		thread2.start();
	         		//thread4.start();
	         		//thread6.start();
				    //thread7.start();
	        	
				    
	         		try {
	         			thread1.join();
						thread2.join();
						//thread4.join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	         		
				    //SwingWorker
	        	SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						
						new GUIMain ("SwingWorker CheckIn");
						
					}
	        		
	        		
	        	});
	        	
	                 		
         		
	        }
		
	}
	    
	    class GUIReportController implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	        	   		
	    
	        }
	        
	     
	    }
	    
	    
	    
	    
	    
	    
	    

}
