package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.Baggage;
import Model.Log;
import Model.Passenger;
import Model.PassengersInQueue;


import View.GUIMain;

public class GUIController {

	private PassengersInQueue passengersinqueue;

	private GUIMain view1;

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

			//view1.timerSlider.setEnabled(false);
			
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					new GUIMain("SwingWorker CheckIn");

				}

			});

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
