import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.*;

/**
 * Advanced Software Engineering - Coursework. GUI - Welcome GUI allows
 * passenger to begin the check-in process
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TPWelcome extends JFrame implements ActionListener {

	/**
	 * Serialises the class TPWelcome by defining the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// the lists for baggage, flight and passengers
	private BaggageList baggageList;
	private FlightList flightList;
	private PassengerSet passengerSet;

	// GUI components
	private JLabel iconPin;
	private JButton CIButton, adminButton;
	private ImageIcon TPLogo;
	private JPanel centerPanel, southPanel;

	/**
	 * Creates TPWelcome GUI that allows the user to begin the check-in process.
	 * Sets up the size, colour and visibility of the main JFrame and sets the
	 * location of the JFrame to the middle of the screen. Disables the standard
	 * close button to allow confirmation dialog.
	 * 
	 * @param passengers the passenger set
	 * @param flight     the flight list
	 * @param baggage    the baggage list
	 */
	public TPWelcome(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		// setup the main panels
		setupCenterPanel();
		setupSouthPanel();
		setupNorthPanel();
		
		// set exit dialog colors
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.foreground", new Color(0, 206, 209));
		
		// setup the JFrame title, size, colors, and visibility
		// sets location of GUI to middle of screen
		setTitle("TravelPigeon Check-in");
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
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
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit? You're check-in is incomplete, you will not be checked-in.",
						"Exit Programme", JOptionPane.YES_NO_OPTION);

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
	 * Sets up the north panel to button to access admin area to view report.
	 * Contains JPanel and JButton and adds them to the top of the border layout on
	 * the JFrame.
	 */
	private void setupNorthPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(7, 3));
		centerPanel.setBackground(Color.WHITE);

		// setup admin button
		adminButton = new JButton("Admin Area");
		adminButton.setPreferredSize(new Dimension(150, 20));
		adminButton.setForeground(Color.WHITE);
		adminButton.setBackground(new Color(0, 206, 209));

		// add action listener to admin button
		adminButton.addActionListener(this);

		// add components to centre panel
		centerPanel.add(adminButton);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.NORTH);
	}

	/**
	 * Sets up the centre panel to hold an image. Contains JPanel and JLabel and
	 * adds them to the centre of the border layout on the JFrame.
	 */
	private void setupCenterPanel() {
		// setup centre panel
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);

		// load image
		TPLogo = new ImageIcon(getClass().getResource("TP.png"));
		iconPin = new JLabel(TPLogo);

		// add components to centre panel
		centerPanel.add(iconPin);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.CENTER);
	}

	// setup south panel
	private void setupSouthPanel() {
		// setup south panel
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		// setup button
		CIButton = new JButton("Click to Begin Check-In");
		CIButton.setForeground(Color.WHITE);
		CIButton.setBackground(new Color(0, 206, 209));

		// add action listener to button
		CIButton.addActionListener(this);

		// add components to south panel
		southPanel.add(CIButton);

		// add south panel to border layout
		this.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Sets up the action to be performed depending on which button is pressed.
	 * CIButton creates new instance of TPDetails when user presses CIButton and
	 * disposes this JFrame. adnimButton calls the runAdmin method.
	 * 
	 * @param e the event to be processed
	 */
	public void actionPerformed(ActionEvent e) {
		// if CIButton is pressed
		if (e.getSource() == CIButton) {
			TPDetails TPDetails = new TPDetails(passengerSet, flightList, baggageList);
			TPDetails.setVisible(true);
			TPDetails.setLocationRelativeTo(null);
			dispose();
		// else if admin button pressed
		} else if (e.getSource() == adminButton) {
			runAdmin();
		}
	}

	/**
	 * This method sets the admin page to visible if the user enters the correct
	 * password.
	 * 
	 */
	private void runAdmin() {
		JPasswordField pwd = new JPasswordField(10);
		JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
		char[] password = pwd.getPassword();
		char[] correctPass = new char[] { 's', 'e', 'c', 'r', 'e', 't' };

		// if password is correct, continue to report area.
		if (Arrays.equals(password, correctPass)) {
			System.out.println("Password is correct");
			TPAdminPage AdminPage = new TPAdminPage(passengerSet, flightList, baggageList);
			AdminPage.setVisible(true);
			AdminPage.setLocationRelativeTo(null);
			dispose();
		}
		// if password incorrect, return to main
		else {
			System.out.println("Incorrect password");
			TPWelcome TPWelcome = new TPWelcome(passengerSet, flightList, baggageList);
			TPWelcome.setVisible(true);
			TPWelcome.setLocationRelativeTo(null);
			dispose();
		}
	}
}