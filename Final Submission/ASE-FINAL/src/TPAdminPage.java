
// import toolkits
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Advanced Software Engineering - Coursework. GUI - Welcome GUI allows
 * user to access an admin page to view the flight report.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TPAdminPage extends JFrame implements ActionListener {

	/**
	 * Serialises the class TPWelcome by defining the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// the lists for baggage, flight and passengers
	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;

	// GUI components
	private JTextArea flightReport;
	private JButton backButton;
	private JPanel northPanel, centerPanel, southPanel;
	private JLabel iconPin;
	private ImageIcon TPLogo;

	// initiate lists
	List<Baggage> BaggageList = new ArrayList<Baggage>();
	List<Baggage> BaggageList2 = new ArrayList<Baggage>();
	List<Baggage> BaggageList3 = new ArrayList<Baggage>();

	/**
	 * Creates TPAdmin GUI that allows the users with admin privilages to view the
	 * flight report in the GUI.
	 * 
	 * @param passengers the passenger set
	 * @param flight     the flight list
	 * @param baggage    the baggage list
	 */
	public TPAdminPage(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		// setup the main panels
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		// setup the JFrame title, size,
		setSize(600, 500);
		// disable standard close button to allow confirmation for closing
		// show JOptionpane confirmation when close button pressed
		addWindowListener(new WindowAdapter() {
			/**
			 * Overrides the standard close operation with JOptionPane allowing the user to
			 * return to the application if the NO option is selected. If the YES option is
			 * selected then the application will write the final flight report and then
			 * exit
			 * 
			 * @param e the event to be processed
			 */
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Programme",
						JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					baggageList.flightReport();
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	/**
	 * Sets up the north panel to hold an image. Contains JPanel and an image and
	 * adds them to the top of the border layout on the JFrame.
	 */
	private void setupNorthPanel() {
		// setup north panel
		northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setLayout(new BorderLayout());

		// load image
		TPLogo = new ImageIcon(getClass().getResource("TPSmall.png"));
		iconPin = new JLabel(TPLogo);

		// add components to centre panel
		northPanel.add(iconPin, BorderLayout.WEST);

		// add centre panel to border layout
		this.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Sets up the centre panel to hold the flight report. Contains JPanel and
	 * JTextArea and adds them to the centre of the border layout on the JFrame.
	 */
	private void setupCenterPanel() {
		// setup centre panel
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);

		// setup text area
		flightReport = new JTextArea();
		flightReport.setEditable(false);

		// setup scroll
		JScrollPane scroll = new JScrollPane(flightReport);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// add components to centre panel
		centerPanel.add(scroll, flightReport);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.CENTER);

		// the report setup
		String WoL = "Weight of Luggage (Kg)|" + "\n" + "---------------------";
		String VoB = "Volume of Baggage (In3)|" + "\n" + "---------------------";
		String LoF = "Total excess Baggage Fees (GBP)   |" + "\n" + "-----------------------------------";
		String LoP = "Total Passengers Checked-In   |" + "\n" + "-----------------------------";

		String WoL2 = baggageList.calcWeight(BaggageList).toString().replace('[', ' ').replace(']', ' ').replace(",",
				"");
		String VoB2 = baggageList.calcVolume(BaggageList2).toString().replace('[', ' ').replace(']', ' ').replace(",",
				"");
		String LoF2 = baggageList.calcFees(BaggageList3).toString().replace('[', ' ').replace(']', ' ').replace(',',
				' ');
		String LoP2 = baggageList.passengerCapacity().toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');

		String FRW = "Travel Pigeon Flight Report of Checked In Customers";

		String report = FRW + "\n" + "----------------------------------------------------------------------------"
				+ "\n" + WoL + "\n" + WoL2 + "\n" + "\n"
				+ "----------------------------------------------------------------------------" + "\n" + VoB + "\n"
				+ VoB2 + "\n" + "\n" + "----------------------------------------------------------------------------"
				+ "\n" + LoF + "\n" + LoF2 + "\n" + "\n"
				+ "----------------------------------------------------------------------------" + "\n" + LoP + "\n"
				+ LoP2 + "\n" + "\n" + "----------------------------------------------------------------------------";

		// append the report
		flightReport.append(report);
		flightReport.setBounds(10, 30, 300, 300);
	}

	/**
	 * Sets up the south panel to hold the JButton. Contains JPanel and JButton and
	 * adds them to the bottom of the border layout on the JFrame.
	 */
	private void setupSouthPanel() {
		// setup south panel
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		// setup back button
		backButton = new JButton("Back to Check-In Mode");
		backButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(0, 206, 209));
		backButton.setActionCommand("Proceed to Checkin");

		// add action listener to button
		backButton.addActionListener(this);

		// add components to south panel
		southPanel.add(backButton);

		// add south panel to main border layout
		this.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Sets up the action to be performed depending on button press. Creates new
	 * instance of TPWelcome when user presses backButton and disposes this JFrame.
	 * 
	 * @param e the event to be processed
	 * @Override
	 */
	public void actionPerformed(ActionEvent e) {
		TPWelcome TPWelcome = new TPWelcome(passengerSet, flightList, baggageList);
		TPWelcome.setVisible(true);
		TPWelcome.setLocationRelativeTo(null);
		dispose();
	}
}
