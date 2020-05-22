package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstProducer;
import Model.FirstandBusinessCheckInRunnable;
import Model.Log;
import Model.BoardingRunnable;
import Model.EcoProducer;
import Model.SecurityRunnable;
import Model.PassengersInQueue;
import Model.Airport;
import Model.Passenger;
import Model.PassengerSet;

import View.GUIMain;
import View.GUIReport;


public class GUIController {

	private PassengersInQueue passengersinqueue;
	private PassengerSet ps;
	private EconomyCheckIn1Runnable desk1;
	private EconomyCheckIn2Runnable desk2;
	private EconomyCheckIn3Runnable desk3;
	private FirstandBusinessCheckInRunnable FirstDesk;
	private SecurityRunnable security;
	private BoardingRunnable boarding;
	private GUIMain view1;
	private GUIReport view2;
	private EcoProducer ecoprod;
	private FirstProducer firstprod;
	private Airport airport;
	private int numPassengers;
	private Log log;

	public GUIController(PassengersInQueue q, EconomyCheckIn1Runnable d1, EconomyCheckIn2Runnable d2,
			EconomyCheckIn3Runnable d3, FirstandBusinessCheckInRunnable f, SecurityRunnable s, BoardingRunnable b,
			EcoProducer ep, FirstProducer fp, GUIMain v1) {

		passengersinqueue = q;
		desk1 = d1;
		desk2 = d2;
		desk3 = d3;
		FirstDesk = f;
		security = s;
		boarding = b;
		view1 = v1;
		ecoprod = ep;
		firstprod = fp;

		// this.log = log;
		view1.addGUIMainListener(new GUIMainController());
		// view2.addGUIReportListener(new GUIReportController());

	}

//	public void update(LinkedList<Passenger> queue) {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			public void run() {
//				for (Passenger p : queue) {
//					String print = p.getFullName();
//					view1.appendText(print + "\n");
//					System.out.print("SIZE OF LIST IS THIS: " + queue.size());
//					System.out.print("SIZE OF THE OTHER LIST IS THIS: " + passengersinqueue.getQ1().size());
//				}
//
//			}
//		});
//	}

	class GUIMainController implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			System.out.println("THE BUTTON HAS BEEN PRESSED");
			
			List<String> queueList = new ArrayList<String>();

			view1.disableProcessButton();

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					Timer airportTimer = new Timer();
					Thread airportthread = new Thread(new Airport(passengersinqueue));

					airportTimer.schedule(new TimerTask() {

						@Override
						public void run() {

							System.out.println("GATES CLOSING SOON");

							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {
							}

							System.out.println("FINAL BOARDING CALL");
							System.out.println("GATES CLOSED");
	

							// Runtime.getRuntime().exit(0);
							// Runtime.getRuntime().halt(0);

						}

					}, 60000);

					airportthread.start();

					SwingWorker<List<String>, String> worker = new SwingWorker<List<String>, String>() {

						// we need this but i dunno what to put in it yet
						@Override
						protected void done() {

							try {
								List<String> queueList = get();
								System.out.println("ALL PROCESSES COMPLETE" + queueList.size() + " processed");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						// this appends what gets published during doInBackground to the
						// GUI field. This will need modified when appending to the different fields
						// as well as the appendText function in GUIMain.
						@Override
						protected void process(List<String> chunks) {

							for (String chunk : chunks) {

								view1.appendText(chunk + "\n");
								
								System.out.print("SIZE OF LIST IS THIS: " + chunks.size());
								System.out.print("SIZE OF THE OTHER LIST IS THIS: " + passengersinqueue.getQ().size());

							}

	//					String passengers = chunks.get(0);
	//					update(passengers);
						}

						// this is where the magic is happening - starts the threads in the background
						// on the EDT
						protected List<String> doInBackground() throws Exception {
							System.out.println("BANANAS BANANANS BANANANANANANANANAS IM IN THE BACKGROUND");



							while (!isCancelled()) {


								numPassengers = passengersinqueue.getQ().size();
										
								for (int i = 0; i < numPassengers; i++) {
								
									String report = ps.get(i).getQueueList();
									
									System.out.println("I GOT HERE");

								//	queueList.add(passenger);

									publish(report);
								//	System.out.println("I'M BEING PUBLISHED..." + passenger.getFullName());
								}
							}

//								while (!isCancelled()) {
							//
//									for (Message message : passengersinqueue.getMessages()) {
//										System.out.println("I GOT HERE");
//										publish(message.getContents());
//										System.out.println("I'M BEING PUBLISHED..." + message.getContents());
//									}
//								}

							return queueList;
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
