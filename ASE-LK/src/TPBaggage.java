import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class TPBaggage extends JFrame implements ActionListener {

	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;

	private JTextField heightField, widthField, lengthField, bookingRefField, bookingNameField, weightField;
	private JLabel heightLabel, widthLabel, lengthLabel, imagePin, enterDetailsLabel, bookingRefLabel, weightLabel,
			bookingNameLabel;
	private ImageIcon baggage, plane;
	private JPanel northPanel, centerPanel, centerTopPanel, centerBottomPanel, heightPanel, lengthPanel, widthPanel,
			eastPanel, westPanel, southPanel, bookingDetails, bookingRef, bookingName, containerSouthPanel,
			weightPanel;
	private JButton submitCalcButton;
	private String bookingReference, bookingLastName;

	public TPBaggage(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		// set up window title

		setupNorthPanel();
		setupEastPanel();
		setupCenterPanel();
		setupWestPanel();
		setupSouthPanel();

		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.foreground", new Color(0, 206, 209));

		setTitle("TravelPigeon Check-in");
		setBackground(Color.WHITE);
		setSize(600, 500);
		setLocationRelativeTo(null); // sets position of JFrame to middle of screen

		// show JOptionpane confirmation when close button pressed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit? You're check-in is incomplete, you will not be checked-in.",
						"Exit Programme", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	// Setup north panel
	private void setupNorthPanel() {
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

	// Setup centre panel
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

		heightLabel = new JLabel("Height");
		heightLabel.setForeground(new Color(0, 206, 209));

		heightField = new JTextField();
		heightField.setToolTipText("Height");
		heightField.setColumns(1);

		// add components to height panel
		heightPanel.add(heightLabel, BorderLayout.NORTH);
		heightPanel.add(heightField, BorderLayout.SOUTH);

		// setup width sub-panel
		widthPanel = new JPanel();
		widthPanel.setBackground(Color.WHITE);
		widthPanel.setLayout(new BorderLayout(0, 0));

		widthLabel = new JLabel("Width");
		widthLabel.setForeground(new Color(0, 206, 209));

		widthField = new JTextField();
		widthField.setToolTipText("Height");
		widthField.setColumns(1);

		// add components to width panel
		widthPanel.add(widthLabel, BorderLayout.NORTH);
		widthPanel.add(widthField, BorderLayout.SOUTH);

		// setup length sub-panel
		lengthPanel = new JPanel();
		lengthPanel.setBackground(Color.WHITE);
		lengthPanel.setLayout(new BorderLayout(0, 0));

		lengthLabel = new JLabel("Length");
		lengthLabel.setForeground(new Color(0, 206, 209));

		lengthField = new JTextField();
		lengthField.setToolTipText("Height");
		lengthField.setColumns(1);

		// add components to length panel
		lengthPanel.add(lengthLabel, BorderLayout.NORTH);
		lengthPanel.add(lengthField, BorderLayout.SOUTH);
		
		// setup weight panel
		weightPanel = new JPanel();
		weightPanel.setBackground(Color.WHITE);
		weightPanel.setLayout(new BorderLayout(0, 0));

		weightLabel = new JLabel("Enter weight in kg");
		weightLabel.setForeground(new Color(0, 206, 209));

		weightField = new JTextField();
		weightField.setToolTipText("Weight in kg");
		weightField.setColumns(1);
		
		weightPanel.add(weightLabel, BorderLayout.NORTH);
		weightPanel.add(weightField, BorderLayout.SOUTH);

		// add components to centre top panel
		centerTopPanel.add(heightPanel);
		centerTopPanel.add(widthPanel);
		centerTopPanel.add(lengthPanel);
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

	// setup east panel
	private void setupEastPanel() {
		eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setAlignmentX(1.0f);
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// add east panel to main border layout
		this.add(eastPanel, BorderLayout.EAST);
	}

	// setup west panel
	private void setupWestPanel() {
		westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setAlignmentX(1.0f);
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// add west panel to main border layout
		this.add(westPanel, BorderLayout.WEST);
	}

	// setup south panel
	private void setupSouthPanel() {

		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		submitCalcButton = new JButton("Submit & Calculate");
		submitCalcButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		submitCalcButton.setForeground(new Color(0, 206, 209));
		submitCalcButton.setBackground(new Color(0, 206, 209));
		submitCalcButton.setActionCommand("Submit & Calculate");
		submitCalcButton.addActionListener(this);

		// add components to south panel
		southPanel.add(submitCalcButton);

		// add south panel to main border layout
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitCalcButton) {
			input2();
			excessFeeCheck2();
		}
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public String getBookingLastName() {
		return bookingLastName;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
		bookingRefField.setText(bookingReference);
		System.out.println(bookingReference);
	}

	public void setBookingName(String bookingLastName) {
		this.bookingLastName = bookingLastName;
		bookingNameField.setText(bookingLastName);
		System.out.println(bookingLastName);
	}

	private void input2() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();

		Passenger p = passengerSet.findBooking2(bookingRef, bookingName);
		if (p != null) {
			String flightCode = p.getflightCode();
			Flight f = flightList.findByCode(flightCode);

			String weightString = weightField.getText();
			String heightString = heightField.getText();
			String widthString = widthField.getText();
			String lengthString = lengthField.getText();
			
			double weight = Double.parseDouble(weightString);
			double height = Double.parseDouble(heightString);
			double width = Double.parseDouble(widthString);
			double length = Double.parseDouble(lengthString);

			Baggage b = new Baggage(weight, height, width, length, p, f);
			baggageList.addBaggage(b);
		}
	}

	private void checkIn2() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();

		Passenger p = passengerSet.findBooking2(bookingRef, bookingName);
		if (p != null) {
			if (p.getCheckInStatus() != false) {
				JOptionPane.showMessageDialog(null, "You are already checked-in");
				flightReport();
				System.exit(0);
			} else {
				p.setCheckInStatus(true);
				plane = new ImageIcon(getClass().getResource("Plane.png"));
				JOptionPane.showMessageDialog(null, "Check-In Complete. \nPlease head to your departure gate.",
						"Check-In Complete", JOptionPane.INFORMATION_MESSAGE, plane);
				flightReport();
				System.exit(0);
			}
		}
	}

	private void excessFeeCheck2() {
		// get input and trim to remove additional spaces
		String bookingRef = bookingRefField.getText();
		String bookingName = bookingNameField.getText();

		Passenger p = passengerSet.findBooking2(bookingRef, bookingName);
		if (p != null) {

			Baggage b = baggageList.findByPassenger(p);
			double excessFee = b.excessBaggageFee();

			if (excessFee > 0) {
				Object[] options = { "Confirm and Check-In", "Return to Baggage Details" };
				int choice = JOptionPane.showOptionDialog(null,
						"Excess Baggage Charge Due: £" + excessFee + ". Please confirm below to proceed with Check-In.",
						"Excess Baggage Fee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);

				if (choice == JOptionPane.YES_OPTION) {
					checkIn2();
				} else {
					JOptionPane.showMessageDialog(null, "Please re-enter your baggage details in order to check-in.");
				}
			} else {
				checkIn2();
			}
		}

	}

//	private void input() {
//		// get input and trim to remove additional spaces
//		String bookingRef = bookingRefField.getText();
//		String bookingName = bookingNameField.getText();
//
//		Passenger p;
//		try {
//			p = passengerSet.findBooking(bookingRef, bookingName);
//			if (p != null) {
//				String flightCode = p.getflightCode();
//				Flight f = flightList.findByCode(flightCode);
//
//				String weightString = weightField.getText();
//				String heightString = heightField.getText();
//				String widthString = widthField.getText();
//				String lengthString = lengthField.getText();
//
//				double weight = Double.parseDouble(weightString);
//				double height = Double.parseDouble(heightString);
//				double width = Double.parseDouble(widthString);
//				double length = Double.parseDouble(lengthString);
//
//				Baggage b = new Baggage(weight, height, width, length, p, f);
//				baggageList.addBaggage(b);
//			}
//		} catch (NoMatchingBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Booking Reference not found");
//		} catch (InvalidBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Invalid Booking Reference. Please use the form XX123456");
//		} catch (NoMatchingLastNameException e) {
//			JOptionPane.showMessageDialog(null, "No booking found for that name.");
//		}
//	}
//
//	private void checkIn() {
//		// get input and trim to remove additional spaces
//		String bookingRef = bookingRefField.getText();
//		String bookingName = bookingNameField.getText();
//
//		Passenger p;
//		try {
//			p = passengerSet.findBooking(bookingRef, bookingName);
//			if (p != null) {
//				if (p.getCheckInStatus() != false) {
//					JOptionPane.showMessageDialog(null, "You are already checked-in");
//					flightReport();
//					System.exit(0);
//				} else {
//					p.setCheckInStatus(true);
//					plane = new ImageIcon(getClass().getResource("Plane2.png"));
//					JOptionPane.showMessageDialog(null, "Check-In Complete. \nPlease head to your departure gate.",
//							"Check-In Complete", JOptionPane.INFORMATION_MESSAGE, plane);
//					flightReport();
//					System.exit(0);
//				}
//			}
//		} catch (NoMatchingBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Booking Reference not found");
//		} catch (InvalidBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Invalid Booking Reference. Please use the form XX123456");
//		} catch (NoMatchingLastNameException e) {
//			JOptionPane.showMessageDialog(null, "No booking found for that name.");
//		}
//
//	}
//
//	private void excessFeeCheck() {
//		// get input and trim to remove additional spaces
//		String bookingRef = bookingRefField.getText();
//		String bookingName = bookingNameField.getText();
//
//		Passenger p;
//		try {
//			p = passengerSet.findBooking(bookingRef, bookingName);
//			if (p != null) {
//
//				Baggage b = baggageList.findByPassenger(p);
//				double excessFee = b.excessBaggageFee();
//
//				if (excessFee > 0) {
//					Object[] options = { "Confirm and Check-In", "Return to Baggage Details" };
//					int choice = JOptionPane.showOptionDialog(null,
//							"Excess Baggage Charge Due: £" + excessFee
//									+ ". Please confirm below to proceed with Check-In.",
//							"Excess Baggage Fee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
//							options, options[0]);
//
//					if (choice == JOptionPane.YES_OPTION) {
//						checkIn();
//					} else {
//						JOptionPane.showMessageDialog(null,
//								"Please re-enter your baggage details in order to check-in.");
//					}
//				} else {
//					checkIn();
//				}
//			}
//		} catch (NoMatchingBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Booking Reference not found");
//		} catch (InvalidBookingReference e) {
//			JOptionPane.showMessageDialog(null, "Invalid Booking Reference. Please use the form XX123456");
//		} catch (NoMatchingLastNameException e) {
//			JOptionPane.showMessageDialog(null, "No booking found for that name.");
//		}
//	}

	private void flightReport() {
		String flightReport = baggageList.baggageCapacityAnalysis();
		writeToFile("FlightReport.txt", flightReport);
	}

	private void writeToFile(String Report, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(Report);
			fw.write("FLIGHT REPORT\n");
			fw.write(report);
			fw.close();
		}
		// if file not found give message and stop
		catch (FileNotFoundException fnf) {
			System.out.println(Report + " not found ");
			System.exit(0);
		}
		// stack trace here
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
}
