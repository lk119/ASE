
// import toolkits
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Advanced Software Engineering - Coursework. GUI - Details GUI allows
 * passenger to enter their Last Name and Booking Reference Number. Uses
 * JTextFields to allow user input. If the passenger's details are inputted
 * correctly and passenger exists on passenger input file then the next GUI will
 * be displayed.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TPDetails extends JFrame implements ActionListener {

	/**
	 * Serialises the class TPDetails by defining the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// the lists for baggage, flight and passengers
	private PassengerSet passengerSet;
	private FlightList flightList;
	private BaggageList baggageList;

	// GUI components
	private JTextField bookingRefField, lastNameField;
	private JButton submitButton;
	private ImageIcon boardingPass;
	private JLabel imagePin;

	/**
	 * Creates TPDetails GUI that allows the user to enter their Last Name and
	 * Booking Reference Number. Disables the standard close button.
	 * 
	 * @param passengers the passenger set
	 * @param flight     the flight list
	 * @param baggage    the baggage list
	 */
	public TPDetails(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		// setup the main panels
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		// setup the JFrame title, size, colours, and visibility
		// sets location of GUI to middle of screen
		setTitle("TravelPigeon Check-in");
		setSize(600, 500);
		setLocationRelativeTo(null);
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
						"Are you sure you want to exit? Your check-in is incomplete, you will not be checked-in.",
						"Exit Programme", JOptionPane.YES_NO_OPTION);
				// if user selects yes option - produce flight report and exit
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
	 * Sets up the north panel to hold a label instructing the user. Contains JPanel
	 * and JTextField and adds them to the top of the border layout on the JFrame.
	 */
	private void setupNorthPanel() {
		// setup north panel
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		northPanel.setForeground(Color.CYAN);
		northPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// setup panel for label
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		// setup label
		JLabel label = new JLabel("Please Enter Your Details Below:");
		label.setForeground(new Color(0, 206, 209));
		label.setBackground(Color.WHITE);

		// add components to north panel
		northPanel.add(panel);
		northPanel.add(label);

		// add north panel to border layout
		this.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Sets up the centre panel to hold JTextFields for users to input their Booking
	 * Reference Number and Last Name and show an image. Contains JPanel, JTextField
	 * and JLabel and adds them to the centre of the border layout on the JFrame.
	 */
	private void setupCenterPanel() {
		// setup centre panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// setup DetailsPanel
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBackground(Color.WHITE);
		detailsPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// setup booking ref label
		JLabel bookingRefLabel = new JLabel("Booking Reference");
		bookingRefLabel.setForeground(new Color(0, 206, 209));

		// setup booking ref text field
		bookingRefField = new JTextField();
		bookingRefField.setToolTipText("Enter your Booking reference here");
		bookingRefField.setColumns(1);

		// setup last name label
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(new Color(0, 206, 209));

		// setup last name text field
		lastNameField = new JTextField();
		lastNameField.setToolTipText("Enter your last name here");
		lastNameField.setColumns(1);
		lastNameField.setBackground(Color.WHITE);

		// setup spacer labels
		JLabel spacerLabel = new JLabel("");
		JLabel spacerLabel2 = new JLabel("");

		// add components to details panel
		detailsPanel.add(bookingRefLabel);
		detailsPanel.add(bookingRefField);
		detailsPanel.add(spacerLabel);
		detailsPanel.add(lastNameLabel);
		detailsPanel.add(lastNameField);
		detailsPanel.add(spacerLabel2);

		// setup imagePanel and load image
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setLayout(new GridLayout(0, 3, 0, 0));
		boardingPass = new ImageIcon(getClass().getResource("Tic2.png"));
		imagePin = new JLabel(boardingPass);

		// setup spacer panel
		JPanel spacerPanel = new JPanel();
		spacerPanel.setBackground(Color.WHITE);

		// add components to image panel
		imagePanel.add(spacerPanel);
		imagePanel.add(imagePin);

		// add components to centre panel
		centerPanel.add(detailsPanel);
		centerPanel.add(imagePanel);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up the south panel to hold a JButton for users to select to confirm
	 * their details. Contains JPanel and JButton and adds them to the bottom of the
	 * border layout on the JFrame.
	 */
	private void setupSouthPanel() {
		// setup south panel
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));

		// setup submit button
		submitButton = new JButton("Submit");
		submitButton.setForeground(Color.WHITE);
		submitButton.setBackground(new Color(0, 206, 209));

		// add action listener to submit button
		submitButton.addActionListener(this);

		// add components to south panel
		southPanel.add(submitButton);

		// add south panel to border layout
		this.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Sets up the action to be performed on the button press. Calls the search
	 * method.
	 * 
	 * @param e the event to be processed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			search();
		}
	}

	/**
	 * First checks that user has input values in the text fields and if not then
	 * displays a message to the user asking for the required input. Then searches
	 * for the passenger using the provided booking reference number and last name.
	 * If the passenger isn't found an exception is thrown and the user is given a
	 * message. Checks if the passenger is already checked-in and if so displays a
	 * message saying so and does not allow the application to proceed. If the
	 * passenger has not checked-in, the application will create a new instance of
	 * the TPBaggage GUI, set the booking reference number and last name of the
	 * passenger for the TPBaggage GUI, set the TPBaggage GUI visible and dispose
	 * this GUI.
	 * 
	 * @exception NoMatchingLastNameException	if passenger not found.
	 * 
	 */
	private void search() {
		// get booking reference and booking name
		String bookingRef = getBookingRef();
		String bookingName = getBookingName();
		try {
			// check the input has a value
			if (bookingRef.length() > 0) {
				// check the input has a value
				if (bookingName.length() > 0) {
					// find the passenger using booking ref and booking name
					Passenger p = passengerSet.findBooking(bookingRef, bookingName);
					// if passenger is found check their check-in status
					if (p != null) {
						// if checked-in display message saying so
						if (p.getCheckInStatus() != false && bookingName.equalsIgnoreCase(p.getLastName())) {
							JOptionPane.showMessageDialog(null, "You are already checked-in");
						// else proceed to baggage details GUI
						} else {
							TPBaggage baggage = new TPBaggage(passengerSet, flightList, baggageList);
							baggage.setBookingName(getBookingName(), getBookingRef());
							baggage.setVisible(true);
							baggage.setLocationRelativeTo(null);
							this.dispose();
						}
					}
				} else {
					// display if last name not entered
					JOptionPane.showMessageDialog(null, "Please enter your Last Name");
				}
			} else {
				// display if booking ref not entered
				JOptionPane.showMessageDialog(null, "Please enter your Booking Reference");
			}
		} catch (NoMatchingLastNameException nme) {
			// display if passenger not found
			JOptionPane.showMessageDialog(null, "Name or the number entered may be incorrect. Please try again.");
		}
	}

	/**
	 * Identifies the user's input in the booking reference JTextfield
	 * 
	 * @return the booking reference number
	 */
	private String getBookingRef() {
		// get input value and trim to remove any additional spaces
		String bookingRef = bookingRefField.getText().trim();
		return bookingRef;
	}

	/**
	 * Identifies the user's input in the booking reference JTextfield
	 * 
	 * @return the passenger's last name
	 */
	private String getBookingName() {
		// get input value and trim to remove any additional spaces
		String bookingName = lastNameField.getText().trim();
		return bookingName;
	}
}