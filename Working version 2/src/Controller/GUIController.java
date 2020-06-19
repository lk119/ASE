package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;


import Model.Log;

import Model.PassengersInQueue;
import Model.Passenger;
import Model.PassengerSet;


import View.GUIMain;




public class GUIController {

	
	private PassengersInQueue passengersinqueue; 
	
	
	private GUIMain view1;
	
	
	private Log log;
	
	
	
	
	
	public GUIController(PassengersInQueue q, GUIMain v1) {
					
		   
		    passengersinqueue = q;
		    
			view1 = v1;
			
			
			//this.log = log;
			view1.addGUIMainListener(new GUIMainController());
			//view2.addGUIReportListener(new GUIReportController());
			
					
			
			
		}
			
	
		
	    
	    class GUIMainController  implements ActionListener
	    
	    
	    {
	        public void actionPerformed(ActionEvent e) 
	        { 
	        	view1.setVisible(true); 
	        	//main method that will trigger the programme
	        	view1.disableProcessButton();
	        	
	        	view1.startDisplay();
	        	
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
