import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.midi.SysexMessage;
import javax.swing.*;

public class TPDetails extends JFrame implements ActionListener {

	private PassengerSet passengerSet;
	private FlightList flightList;
	private BaggageList baggageList;

	private JTextField bookingRefField, lastNameField;
	private JButton submitButton;
	private ImageIcon boardingPass;
	private JLabel imagePin;

	public TPDetails(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		setTitle("TravelPigeon Check-in");
		setSize(600, 500);
		setLocationRelativeTo(null); // sets position of JFrame to middle of screen

		// disable standard close button
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		// show JOptionpane confirmation when close button pressed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit? You're check-in is incomplete, you will not be checked-in.",
						"Exit Programme", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					baggageList.flightReport();
					dispose();
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	// setup north panel
	private void setupNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		northPanel.setForeground(Color.CYAN);
		northPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel label = new JLabel("Please Enter Your Details Below:");
		label.setForeground(new Color(0, 206, 209));
		label.setBackground(Color.WHITE);

		northPanel.add(panel);
		northPanel.add(label);

		this.add(northPanel, BorderLayout.NORTH);
	}

	// setup center panel
	private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// setupDetailsPanel
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBackground(Color.WHITE);
		detailsPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel bookingRefLabel = new JLabel("Booking Reference");
		bookingRefLabel.setForeground(new Color(0, 206, 209));

		bookingRefField = new JTextField();
		bookingRefField.setToolTipText("Enter your Booking reference here");
		bookingRefField.setColumns(1);

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(new Color(0, 206, 209));

		lastNameField = new JTextField();
		lastNameField.setToolTipText("Enter your last name here");
		lastNameField.setColumns(1);
		lastNameField.setBackground(Color.WHITE);

		JLabel spacerLabel = new JLabel("");
		JLabel spacerLabel2 = new JLabel("");

		detailsPanel.add(bookingRefLabel);
		detailsPanel.add(bookingRefField);
		detailsPanel.add(spacerLabel);
		detailsPanel.add(lastNameLabel);
		detailsPanel.add(lastNameField);
		detailsPanel.add(spacerLabel2);

		// setup imagePanel
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setLayout(new GridLayout(0, 3, 0, 0));

		boardingPass = new ImageIcon(getClass().getResource("Tic2.png"));
		imagePin = new JLabel(boardingPass);

		JPanel spacerPanel = new JPanel();
		spacerPanel.setBackground(Color.WHITE);

		imagePanel.add(spacerPanel);
		imagePanel.add(imagePin);

		centerPanel.add(detailsPanel);
		centerPanel.add(imagePanel);

		this.add(centerPanel, BorderLayout.CENTER);
	}

	// setup south panel
	private void setupSouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));

		submitButton = new JButton("Submit");
		submitButton.setForeground(new Color(0, 206, 209));
		submitButton.addActionListener(this);

		southPanel.add(submitButton);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			search();
		}
	}

	private void search() {
		// get input and trim to remove additional spaces
		String bookingRef = getBookingRef();
		String bookingName = getBookingName();

		// check the input has a value
		try {
			if (bookingRef.length() > 0) {
				if (bookingName.length() > 0) {

					Passenger p = passengerSet.findBooking2(bookingRef, bookingName);
					if (p != null) {
						System.out.println(p.getBookingReferenceNum());
						if (p.getCheckInStatus() != false && bookingName.equalsIgnoreCase(p.getLastName())) {
							JOptionPane.showMessageDialog(null, "You are already checked-in");
							// System.exit(0);
						} else {
							TPBaggage baggage = new TPBaggage(passengerSet, flightList, baggageList);
							// baggage.setBookingReference(getBookingRef());
							baggage.setBookingName(getBookingName(), getBookingRef());
							baggage.setVisible(true);
							baggage.setLocationRelativeTo(null);
							System.out.println(baggage.getBookingReference());
							System.out.println(baggage.getBookingLastName());
							this.dispose();
						}
					}
				}
			}
		} catch (NoMatchingLastNameException nme) {
			JOptionPane.showMessageDialog(null,
					"Name or the number entered may be incorrect please try again,Thank you");
			// nme.printStackTrace();
		}
	}

	private String getBookingRef() {
		String bookingRef = bookingRefField.getText().trim();
		return bookingRef;
	}

	private String getBookingName() {
		String bookingName = lastNameField.getText().trim();
		return bookingName;
	}
}