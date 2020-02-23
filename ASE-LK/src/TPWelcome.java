import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class TPWelcome extends JFrame implements ActionListener {

	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;
	
	private JLabel iconPin;
	private JButton CIButton;
	private ImageIcon TPLogo;
	private JPanel centerPanel, southPanel;

	public TPWelcome(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		setupCenterPanel();
		setupSouthPanel();

		setTitle("TravelPigeon Check-in");
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);

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

	// setup center panel
	private void setupCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		TPLogo = new ImageIcon(getClass().getResource("TP.png"));
		iconPin = new JLabel(TPLogo);

		// add components to centre panel
		centerPanel.add(iconPin);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.CENTER);
	}

	// setup south panel
	private void setupSouthPanel() {
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		CIButton = new JButton("Click to Begin Check-In");
		CIButton.setForeground(new Color(0, 206, 209));
		CIButton.addActionListener(this);

		southPanel.add(CIButton);

		this.add(southPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		TPDetails TPDetails = new TPDetails(passengerSet, flightList, baggageList);
		TPDetails.setVisible(true);
		TPDetails.setLocationRelativeTo(null);
		dispose();
	}
}
