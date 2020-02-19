import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import lekrws1rrp3Assignment2.Competitor;


public class TPDetails extends JFrame {
	private JTextField bookingRefField, lastNameField;
	private JButton submitButton;
	private ImageIcon boardingPass;
	private JLabel imagePin;
	
	public TPDetails() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		setSize(600,500);
		
		// setup north panel
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		northPanel.setForeground(Color.CYAN);
		
		
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		northPanel.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel label = new JLabel("Please Enter Your Details Below:");
		northPanel.add(label);
		label.setForeground(new Color(0, 206, 209));
		label.setBackground(Color.WHITE);
		
		// setup center panel
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			// setupDetailsPanel
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBackground(Color.WHITE);
		centerPanel.add(detailsPanel);
		detailsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel bookingRefLabel = new JLabel("Booking Reference");
		bookingRefLabel.setForeground(new Color(0, 206, 209));
		detailsPanel.add(bookingRefLabel);
		
		bookingRefField = new JTextField();
		bookingRefField.setToolTipText("Enter your Booking reference here");
		bookingRefField.setColumns(1);
		detailsPanel.add(bookingRefField);
		
		JLabel spacerLabel = new JLabel("");
		detailsPanel.add(spacerLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setForeground(new Color(0, 206, 209));
		detailsPanel.add(lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setToolTipText("Enter your last name here");
		lastNameField.setColumns(1);
		lastNameField.setBackground(Color.WHITE);
		detailsPanel.add(lastNameField);
		
		JLabel spacerLabel2 = new JLabel("");
		detailsPanel.add(spacerLabel2);
		
			// setup imagePanel
		JPanel imagePanel = new JPanel();
		centerPanel.add(imagePanel);
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		boardingPass = new ImageIcon(getClass().getResource("Tic2.png"));
		imagePin = new JLabel(boardingPass);
		
		JPanel spacerPanel = new JPanel();
		spacerPanel.setBackground(Color.WHITE);
		imagePanel.add(spacerPanel);


		imagePanel.add(imagePin);
		
		
		// setup south panel
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		submitButton = new JButton("Submit");
		submitButton.setForeground(new Color(0, 206, 209));
		southPanel.add(submitButton);
		
		
		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TPBaggage TPBaggage = new TPBaggage();
				TPBaggage.setVisible(true);
				TPBaggage.setLocationRelativeTo(null);
				dispose();
			}
		});
	
	}
	/**
	 * Identifies the user's input and searches for the passenger last name and booking reference number.
	 * 
	 */
	private void search() {
		JTextComponent searchField;
		// get input and trim to remove additional spaces
		String searchString = searchField.getText().trim();

		// check the input is has a value
		if (searchString.length() > 0) {

			
			String bookingRefernceNum = searchString;
			
					
			// return the passenger object
			Passenger p = PassengerSet.findBookingRefNum(bookingRefernceNum);
			
			

		}
	}
}

