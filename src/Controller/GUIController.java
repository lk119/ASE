package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstandBusinessCheckInRunnable;
import Model.Log;
import Model.Name;
import Model.BoardingRunnable;
import Model.Message;

import Model.SecurityRunnable;
import Model.PassengersInQueue;
import Model.Producer;
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
	private Name passengerName;

	private Log log;

	public GUIController(PassengersInQueue q, EconomyCheckIn1Runnable d1, EconomyCheckIn2Runnable d2,
			EconomyCheckIn3Runnable d3, FirstandBusinessCheckInRunnable f, SecurityRunnable s, BoardingRunnable b,
			GUIMain v1, GUIReport v2) {

		// passenger = p;
		passengersinqueue = q;
		desk1 = d1;
		desk2 = d2;
		desk3 = d3;
		FirstDesk = f;
		security = s;
		boarding = b;
		view1 = v1;
		view2 = v2;

		// prod = pd;
		// this.log = log;
		view1.addGUIMainListener(new GUIMainController());
		// view2.addGUIReportListener(new GUIReportController());

	}

	class GUIMainController implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("THE BUTTON HAS BEEN PRESSED");
			
			view1.disableProcessButton();

			SwingUtilities.invokeLater(new Runnable() {

				public void run() {

					SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {

						// we need this but i dunno what to put in it yet
						@Override
						protected void done() {

							System.out.println("ALL PROCESSES COMPLETE");

						}

						// this appends what gets published during doInBackground to the
						// GUI field. This will need modified when appending to the different fields
						// as well as the appendText function in GUIMain. 
						@Override
						protected void process(List<String> chunks) {

							for (String chunk : chunks) {
								view1.appendText(chunk + "\n");

							}
						}

						// this is where the magic is happening - starts the threads in the background on the EDT
						protected Void doInBackground() throws Exception {
							System.out.println("BANANAS BANANANS BANANANANANANANANAS IM IN THE BACKGROUND");

							Thread thread1 = new Thread(new Producer(passengersinqueue));
							Thread thread2 = new Thread(new EconomyCheckIn1Runnable(passengersinqueue));
							Thread thread3 = new Thread(new FirstandBusinessCheckInRunnable(passengersinqueue));
							Thread thread4 = new Thread(new EconomyCheckIn2Runnable(passengersinqueue));
							Thread thread5 = new Thread(new EconomyCheckIn3Runnable(passengersinqueue));
							Thread thread6 = new Thread(new SecurityRunnable(passengersinqueue));
							Thread thread7 = new Thread(new BoardingRunnable(passengersinqueue));

							thread1.start();
							thread2.start();
							thread3.start();
							thread4.start();
							thread5.start();
							thread6.start();
							thread7.start();

							// this is the only place I've figured I can put this where it will publish something
							// but it just publishes the first person in the Q over and over
							// so obviously not working correctly
							// will also need changed as it's only looking at the one Q
							// I think we need an overall list like I had done before - (see below)
							// but if you run that it only runs the first guy but at least it only runs it once
							// but that overall list would also help when appending to the correct field
							
							while (!isCancelled()) {

								for (Passenger passenger : passengersinqueue.getQ1()) {
									System.out.println("I GOT HERE");
									publish(passenger.getFullName());
									System.out.println("I'M BEING PUBLISHED..." + passenger.getFullName());
								}
							}
							
//							while (!isCancelled()) {
//
//								for (Message message : passengersinqueue.getMessages()) {
//									System.out.println("I GOT HERE");
//									publish(message.getContents());
//									System.out.println("I'M BEING PUBLISHED..." + message.getContents());
//								}
//							}

							try {

								thread1.join();
								thread2.join();
								thread3.join();
								thread4.join();
								thread5.join();
								thread6.join();
								thread7.join();

							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							return null;
						}

					};
					worker.execute();
				}
			});
		}
	}

	class GUIReportController implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}

	}

}
