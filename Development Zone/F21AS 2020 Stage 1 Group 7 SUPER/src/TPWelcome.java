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

public class TPWelcome extends JFrame implements ActionListener {

	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;

	private JLabel iconPin;
	private JButton CIButton, adminButton;
	private ImageIcon TPLogo;
	private JPanel centerPanel, southPanel;

	public TPWelcome(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;

		setupCenterPanel();
		setupSouthPanel();
		setupNorthPanel();

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
					baggageList.flightReport();
					dispose();
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}

	
	private void setupNorthPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(7,3));
		centerPanel.setBackground(Color.WHITE);
		adminButton = new JButton("Admin Area"); 
		adminButton.setPreferredSize(new Dimension(150, 20));
		adminButton.setForeground(Color.WHITE);
		adminButton.setBackground(new Color(0, 206, 209));
		
		adminButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JPasswordField pwd = new JPasswordField(10);
		    	int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
		    	char[] password = pwd.getPassword();
		    	char[] correctPass = new char[] {'s', 'e', 'c', 'r', 'e', 't'};
		    	
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
		  );
		// add components to centre panel
		centerPanel.add(adminButton);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.NORTH);
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