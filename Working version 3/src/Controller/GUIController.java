package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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

	}

	class GUIMainController implements ActionListener

	{
		public void actionPerformed(ActionEvent e) {
			
			
			// method that will trigger the programme
			view1.disableProcessButton();

			// method that will dictate the display onto GUI
			view1.startDisplay();

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					new GUIMain("SwingWorker CheckIn");

				}

			});

		}

	}

}
