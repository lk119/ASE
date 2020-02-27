
//import toolkits
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
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

public class TPBaggage extends JFrame implements ActionListener {

	/**
	 * Serialises the class TPBaggage by defining the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// the lists for baggage, flight and passengers
	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;

	// GUI components
	private JTextField heightField, widthField, DepthField, bookingRefField, bookingNameField, weightField;
	private JLabel heightLabel, widthLabel, DepthLabel, imagePin, enterDetailsLabel, bookingRefLabel, weightLabel,
			bookingNameLabel;
	private ImageIcon baggage, plane;
	private JPanel northPanel, centerPanel, centerTopPanel, centerBottomPanel, heightPanel, DepthPanel, widthPanel,
			eastPanel, westPanel, southPanel, bookingDetails, bookingRef, bookingName, containerSouthPanel, weightPanel;
	private JButton submitCalcButton;
	private String bookingReference, bookingLastName;

	/**
	 * Creates TPBaggage GUI that allows the user to enter their Last Name and
	 * Booking Reference Number. Disables the standard close button.
	 * 
	 * @param passengers the passenger set
	 * @param flight     the flight list
	 * @param baggage    the baggage list
	 */
	public TPBaggage(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		// setup the main panels
		setupNorthPanel();
		setupEastPanel();
		setupCenterPanel();
		setupWestPanel();
		setupSouthPanel();

		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.foreground", new Color(0, 206, 209));

		// setup the JFrame title, size, colours, and visibility
		// sets location of GUI to middle of screen
		setTitle("TravelPigeon Check-in");
		setBackground(Color.WHITE);
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
				JFrame frame = new JFrame();
				int confirmed = JOptionPane.showConfirmDialog(frame,
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
	 * Sets up the north panel to hold text fields for booking reference and booking
	 * name. Contains JPanel, JTextField and JLabel and adds them to the top of the
	 * border layout on the JFrame.
	 */
	private void setupNorthPanel() {
		// setup north panel
		northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setLayout(new BorderLayout(0, 0));

		// Setup booking details panel
		bookingDetails = new JPanel();
		bookingDetails.setBackground(Color.WHITE);
		bookingDetails.setAlignmentX(1.0f);
		bookingDetails.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Setup booking ref panel
		bookingRef = new JPanel();
		bookingRef.setLayout(new BorderLayout(0, 0));
		bookingRef.setForeground(new Color(0, 206, 209));
		bookingRef.setBackground(Color.WHITE);

		bookingRefLabel = new JLabel("Booking Reference");
		bookingRefLabel.setForeground(new Color(0, 206, 209));

		bookingRefField = new JTextField();
		bookingRefField.setEditable(false);
		bookingRefField.setBackground(Color.WHITE);
		bookingRefField.setColumns(1);

		// add components to booking ref panel
		bookingRef.add(bookingRefLabel, BorderLayout.NORTH);
		bookingRef.add(bookingRefField, BorderLayout.SOUTH);

		// Setup booking name panel
		bookingName = new JPanel();
		bookingName.setLayout(new BorderLayout(0, 0));
		bookingName.setBackground(Color.WHITE);

		bookingNameLabel = new JLabel("Booking Name");
		bookingNameLabel.setForeground(new Color(0, 206, 209));

		bookingNameField = new JTextField();
		bookingNameField.setEditable(false);
		bookingNameField.setBackground(Color.WHITE);
		bookingNameField.setColumns(1);
		bookingNameField.setText(getBookingLastName());

		// add components to booking name panel
		bookingName.add(bookingNameLabel, BorderLayout.NORTH);
		bookingName.add(bookingNameField, BorderLayout.SOUTH);

		// add components to booking details panel
		bookingDetails.add(bookingRef, BorderLayout.NORTH);
		bookingDetails.add(bookingName, BorderLayout.SOUTH);

		// Setup container south panel
		containerSouthPanel = new JPanel();
		containerSouthPanel.setBackground(Color.WHITE);
		containerSouthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Setup enter details label
		enterDetailsLabel = new JLabel("Please Enter Your Baggage Details Below");
		enterDetailsLabel.setForeground(new Color(0, 206, 209));
		enterDetailsLabel.setBackground(Color.WHITE);

		// add components to container south panel
		containerSouthPanel.add(enterDetailsLabel);

		// add components to north panel
		northPanel.add(bookingDetails, BorderLayout.NORTH);
		northPanel.add(containerSouthPanel, BorderLayout.SOUTH);

		// add north panel to main border layout
		this.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Sets up the centre panel to hold text fields for user to enter baggage weight
	 * and dimensions. Contains JPanel, JTextField and JLabel and an image and adds
	 * them to the centre of the border layout on the JFrame.
	 */
	private void setupCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout(0, 0));

		// setup centre top panel
		centerTopPanel = new JPanel();
		centerTopPanel.setBackground(Color.WHITE);
		centerTopPanel.setAlignmentX(1.0f);
		centerTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// setup height sub-panel
		heightPanel = new JPanel();
		heightPanel.setBackground(Color.WHITE);
		heightPanel.setLayout(new BorderLayout(0, 0));

		heightLabel = new JLabel("Height(in)");
		heightLabel.setForeground(new Color(0, 206, 209));

		heightField = new JTextField();
		heightField.setToolTipText("Height(in)");
		heightField.setColumns(1);

		// add components to height panel
		heightPanel.add(heightLabel, BorderLayout.NORTH);
		heightPanel.add(heightField, BorderLayout.SOUTH);

		// setup width sub-panel
		widthPanel = new JPanel();
		widthPanel.setBackground(Color.WHITE);
		widthPanel.setLayout(new BorderLayout(0, 0));

		widthLabel = new JLabel("Width(in)");
		widthLabel.setForeground(new Color(0, 206, 209));

		widthField = new JTextField();
		widthField.setToolTipText("Height(in)");
		widthField.setColumns(1);

		// add components to width panel
		widthPanel.add(widthLabel, BorderLayout.NORTH);
		widthPanel.add(widthField, BorderLayout.SOUTH);

		// setup Depth sub-panel
		DepthPanel = new JPanel();
		DepthPanel.setBackground(Color.WHITE);
		DepthPanel.setLayout(new BorderLayout(0, 0));

		DepthLabel = new JLabel("Depth(in)");
		DepthLabel.setForeground(new Color(0, 206, 209));

		DepthField = new JTextField();
		DepthField.setToolTipText("Height(in)");
		DepthField.setColumns(1);

		// add components to Depth panel
		DepthPanel.add(DepthLabel, BorderLayout.NORTH);
		DepthPanel.add(DepthField, BorderLayout.SOUTH);

		// setup weight panel
		weightPanel = new JPanel();
		weightPanel.setBackground(Color.WHITE);
		weightPanel.setLayout(new BorderLayout(0, 0));

		weightLabel = new JLabel("Weight(kg)");
		weightLabel.setForeground(new Color(0, 206, 209));

		weightField = new JTextField();
		weightField.setToolTipText("Weight(kg)");
		weightField.setColumns(1);

		weightPanel.add(weightLabel, BorderLayout.NORTH);
		weightPanel.add(weightField, BorderLayout.SOUTH);

		// add components to centre top panel
		centerTopPanel.add(heightPanel);
		centerTopPanel.add(widthPanel);
		centerTopPanel.add(DepthPanel);
		centerTopPanel.add(weightPanel);

		// setup centre bottom panel
		centerBottomPanel = new JPanel();
		centerBottomPanel.setBackground(Color.WHITE);
		centerBottomPanel.setLayout(new BorderLayout(0, 0));

		baggage = new ImageIcon(getClass().getResource("suitcase2.png"));
		imagePin = new JLabel(baggage);

		// add components to centre bottom panel
		centerBottomPanel.add(imagePin);

		// add components to centre panel
		centerPanel.add(centerTopPanel, BorderLayout.NORTH);
		centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);

		// add centre panel to main border layout
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up the east panel. Is empty but helps with GUI aesthetics.
	 */
	private void setupEastPanel() {
		eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setAlignmentX(1.0f);
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// add east panel to main border layout
		this.add(eastPanel, BorderLayout.EAST);
	}

	/**
	 * Sets up the west panel. Is empty but helps with GUI aesthetics.
	 */
	private void setupWestPanel() {
		westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setAlignmentX(1.0f);
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// add west panel to main border layout
		this.add(westPanel, BorderLayout.WEST);
	}

	/**
	 * Sets up the south panel to hold a JButton for users to select to submit their
	 * baggage details. Contains JPanel and JButton and adds them to the bottom of
	 * the border layout on the JFrame.
	 */
	private void setupSouthPanel() {
		// setup south panel
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		// setup submit button
		submitCalcButton = new JButton("Proceed to Check-In");
		submitCalcButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		submitCalcButton.setForeground(Color.WHITE);
		submitCalcButton.setBackground(new Color(0, 206, 209));
		submitCalcButton.setActionCommand("Proceed to Check-In");
		// add action listener to button
		submitCalcButton.addActionListener(this);

		// add components to south panel
		southPanel.add(submitCalcButton);

		// add south panel to main border layout
		this.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Sets up the action to be performed on the button press. Calls the
	 * excessFeeCheck method.
	 * 
	 * @param e the event to be processed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitCalcButton) {
			excessFeeCheck();
		}
	}

	/**
	 * Returns the booking reference number
	 * 
	 * @return the booking reference number
	 */
	public String getBookingReference() {
		return bookingReference;
	}

	/**
	 * Returns the booking name
	 * 
	 * @return the booking name
	 */
	public String getBookingLastName() {
		return bookingLastName;
	}

	/**
	 * Set the booking reference field and booking name field with the booking
	 * reference and booking name of the associated passenger.
	 * 
	 * @exception NoMatchingLastNameException		if passenger not found.
	 * 
	 */
	public void setBookingName(String bookingLastName, String ref) throws NoMatchingLastNameException {
		HashMap<String, String> pasengers = passengerSet.createAMap();
		for (Map.Entry<String, String> fentry : pasengers.entrySet()) {
			this.bookingLastName = pasengers.get(ref);
			this.bookingReference = ref;
			bookingRefField.setText(ref);
			bookingNameField.setText(pasengers.get(ref));
		}
		if (bookingLastName.equalsIgnoreCase(pasengers.get(ref))) {
			// throws exception if name and booking ref don't match
		} else {
			throw new NoMatchingLastNameException(bookingLastName);
		}
	}

	/**
	 * This method takes the user's inputed values for the baggage dimensions and
	 * weight and uses them to create a new baggage object.
	 * 
	 * @exception NumberFormatException		if any of the required input is empty.
	 * 
	 */
	private void input() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText().trim();
		String bookingName = bookingNameField.getText().trim();

		// find the passenger
		Passenger p = passengerSet.findBooking(bookingRef, bookingName);
		try {
			// if passenger is found then get the input details and add to baggage object
			if (p != null) {
				String flightCode = p.getflightCode();
				Flight f = flightList.findByCode(flightCode);

				// get inputs
				String weightString = weightField.getText().trim();
				String heightString = heightField.getText().trim();
				String widthString = widthField.getText().trim();
				String DepthString = DepthField.getText().trim();

				// convert string inputs to double
				double weight = Double.parseDouble(weightString);
				double height = Double.parseDouble(heightString);
				double width = Double.parseDouble(widthString);
				double Depth = Double.parseDouble(DepthString);

				// create baggage object and add to baggage list
				Baggage b = new Baggage(weight, height, width, Depth, p, f);
				baggageList.addBaggage(b);

				// generate report
				baggageList.flightReport();
			}
		} catch (NumberFormatException nf) {
			// display if any of the input fields are empty
			JOptionPane.showMessageDialog(null, "One or more feilds may be empty, please provide all the dimensions");
		}
	}

	/**
	 * This method takes completes the check-in procedure. First it checks if
	 * passenger is already checked-in and if not changes the passengers check-in
	 * status to true. Displays a message confirming check-in and restarts the
	 * check-in application from the TPWelcome GUI.
	 * 
	 */
	private void checkIn() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();

		// find the passenger
		Passenger p = passengerSet.findBooking(bookingRef, bookingName);

		// if passenger if found the check check-in status
		if (p != null) {
			// check if passenger already checked-in and display message if so
			if (p.getCheckInStatus() != false) {
				JOptionPane.showMessageDialog(null, "You are already checked-in");
				baggageList.flightReport();
			} else {
				// change check in status to true
				p.setCheckInStatus(true);
				// load image for message dialog
				plane = new ImageIcon(getClass().getResource("Spin.gif"));
				// display confirmation message confirming check-in successful
				JOptionPane.showMessageDialog(null,
						"Check-In Complete. \n\n Please head to your departure gate.\n\n Travel Pigeon wishes you a safe journey",
						"Check-In Complete", JOptionPane.INFORMATION_MESSAGE, plane);
				// produce flight report
				baggageList.flightReport();
				// create new instance of TPWelcome GUI, set visible, and dispose this GUI
				TPWelcome TPWelcome = new TPWelcome(passengerSet, flightList, baggageList);
				TPWelcome.setVisible(true);
				TPWelcome.setLocationRelativeTo(null);
				dispose();
			}
		}
	}

	/**
	 * This method calculates if the passenger has excess baggage weight from the
	 * inputed values depending on the passengers designated class.
	 * 
	 * @return amount of excess weight
	 */
	private double excessWeightFee() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();
		String weightString = weightField.getText().trim();

		// convert string inputs to double
		double weight = Double.parseDouble(weightString);

		double excess = 0;
		double max = 0;
		double excessFee = 0;

		// find the passenger
		Passenger p = passengerSet.findBooking(bookingRef, bookingName);
		// if passenger found
		if (p != null) {
			// find the passenger's designated class
			String passengerClass = p.getpClass().trim().toUpperCase();
			// get the max allowed weight for designated class
			for (MaxBaggage mx : MaxBaggage.values()) {
				if (mx.name().equalsIgnoreCase(passengerClass)) {
					max = mx.getweight();
				}
			}
		}
		// if weight less that max allowed weight calculate excess
		if (weight > max) {
			excess = Math.abs(max - weight);
		}
		// calculate excess fee
		excessFee = excess * 50;
		return excessFee;
	}

	/**
	 * This method calculates if the passenger has excess baggage volume from the
	 * inputed values depending on the passengers designated class.
	 * 
	 * @return amount of excess volume
	 */
	private double excessVolumeFee() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();
		String heightString = heightField.getText().trim();
		String widthString = widthField.getText().trim();
		String depthString = DepthField.getText().trim();

		// convert string inputs to double
		double height = Double.parseDouble(heightString);
		double width = Double.parseDouble(widthString);
		double depth = Double.parseDouble(depthString);

		double volume = width * height * depth;
		double excess = 0;
		double max = 0;
		double excessFee = 0;

		// find the passenger
		Passenger p = passengerSet.findBooking(bookingRef, bookingName);

		// if passenger found
		if (p != null) {
			// find the passenger's designated class
			String passengerClass = p.getpClass().trim().toUpperCase();
			// get the max allowed volume for designated class
			for (MaxBaggage mx : MaxBaggage.values()) {
				if (mx.name().equalsIgnoreCase(passengerClass)) {
					max = mx.getvolume();
				}
			}
		}
		// if volume less that max allowed volume calculate excess
		if (volume > max) {
			excess = Math.abs(max - volume);
		}
		// calculate excess volume fee
		if (excess > 0) {
			excessFee = 60;
		}
		return excessFee;
	}

	/**
	 * This method returns the total excess baggage fee
	 * 
	 * @return total excess baggage fee
	 */
	private double excessBaggageFee() {
		double excessFee = 0;
		double excessWeight = excessWeightFee();
		double excessVolume = excessVolumeFee();
		excessFee = excessWeight + excessVolume;
		return excessFee;
	}

	/**
	 * This method checks if the passenger has any excess baggage fees due and warns
	 * the user if so. First it checks to see if the baggage input dimensions and
	 * weight are within an allowed criteria. If not then a info message is
	 * displayed with the allowed dimensions and weight. If the input is valid the
	 * user receives a message with a warning they may receive a baggage charge and
	 * to agree with this. If the use is to be charged a fee they will be provided
	 * with the option to proceed to check in or re-enter their baggage details. If
	 * they proceed to check-in the check-in method is called.
	 * 
	 * @exception NumberFormatException		if any of the required input is empty.
	 * 
	 */
	private void excessFeeCheck() {
		// get input and trim to remove additional spaces
		String weightString = weightField.getText().trim();
		String heightString = heightField.getText().trim();
		String widthString = widthField.getText().trim();
		String depthString = DepthField.getText().trim();

		try {
			// convert string inputs to double
			double weight = Double.parseDouble(weightString);
			double height = Double.parseDouble(heightString);
			double width = Double.parseDouble(widthString);
			double depth = Double.parseDouble(depthString);

			// get excess baggage fee
			double excessFee = excessBaggageFee();
			
			// check if the dimensions entered are allowed
			if (height < 30 && height > 0 && width < 10 && width > 0 && depth < 6 && depth > 0 
					&& weight < 60 && weight > 0) {
				JOptionPane.showMessageDialog(null,
						"Please note you may be charged if your volume and/or weight is above the airline threshold.");
				// if excess fees are due give them option to check-in or re-enter
				if (excessFee > 0) {
					Object[] options = { "Confirm and Check-In", "Return to Baggage Details" };
					int choice = JOptionPane.showOptionDialog(null,
							"Excess Baggage Charge Due: " + excessFee + " GBP"
									+ ". Please confirm below to proceed with Check-In.",
							"Excess Baggage Fee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, options[0]);
					// if yes option input baggage details and check in
					if (choice == JOptionPane.YES_OPTION) {
						input();
						checkIn();
					} else {
						JOptionPane.showMessageDialog(null,
								"Please re-enter your baggage details in order to check-in.");
					}
				// if no excess baggage go straight to input and check-in
				} else {
					input();
					checkIn();
				}
			} else {
				// display if dimensions or weight exceeded
				JOptionPane.showMessageDialog(null, "You have exceeded baggage dimensions." + "\n" + "Baggage dimension limits are:"
						+ "\n" +"Height : less than 30 inches" + "\n" + "Width : less than 10 inches" + "\n" + "Depth : less than 6 inches"  + "\n" + "Weight : less than"
						+ " 60 Kgs." + "\n" + "If you have exceeded any of these dimensions, please visit the customer service counter.");
			}
		} catch (NumberFormatException nf) {
			// display if required input fields empty
			JOptionPane.showMessageDialog(null, "One or more feilds may be empty, please provide all the dimensions");
		}
	}
}
