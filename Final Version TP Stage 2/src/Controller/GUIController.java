package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Model.PassengersInQueue;
import View.GUIMain;

/**
 * Advanced Software Engineering Assignment Stage 2 - Coursework Class that
 * implements the methods such as Action and Change Listener which control and
 * handle user events that occur in the View or GUI Main. This class is key to
 * the Model-View-Controller (MVC) architecture that has been used in this
 * programme.
 * 
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */

public class GUIController {

	private PassengersInQueue passengersinqueue;

	private GUIMain view1;

	/**
	 * @param q  Passengers in queue
	 * @param v1 GUI view
	 * @param GUIMainListener inner class
	 * @param GUISliderListener inner class
	 */

	public GUIController(PassengersInQueue q, GUIMain v1) {

		passengersinqueue = q;
		view1 = v1;
		view1.addGUIMainListener(new GUIMainController());
		view1.addGUISliderListener(new GUISlider());

	}

	
	class GUIMainController implements ActionListener

	{
		public void actionPerformed(ActionEvent e) {

			// method that will trigger the programme
			view1.disableProcessButton();

			// method that will dictate the display onto GUI
			view1.startDisplay();


		}
	}

	public class GUISlider implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int sliderPosition = view1.timerSlider.getValue();
			view1.setTimeForClosing(sliderPosition);
		}

	}

}
