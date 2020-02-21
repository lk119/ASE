import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class TPBaggage extends JFrame implements ActionListener {

	private JTextField heightField, widthField, lengthField, bookingRefField, bookingNameField;
	private JLabel heightLabel, widthLabel, lengthLabel, imagePin, enterDetailsLabel, bookingRefLabel, bookingNameLabel;
	private ImageIcon baggage, plane;
	private JPanel northPanel, centerPanel, centerTopPanel, centerBottomPanel, heightPanel, lengthPanel, widthPanel,
			eastPanel, westPanel, southPanel, bookingDetails, bookingRef, bookingName, containerSouthPanel;
	private JButton submitCalcButton;
	private String bookingReference, bookingLastName;

	public TPBaggage() {

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

		pack();
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

		// add components to centre top panel
		centerTopPanel.add(heightPanel);
		centerTopPanel.add(widthPanel);
		centerTopPanel.add(lengthPanel);

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
	
}

