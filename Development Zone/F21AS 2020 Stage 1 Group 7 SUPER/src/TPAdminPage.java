import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class TPAdminPage extends JFrame implements ActionListener{

	private BaggageList baggageList;
	private PassengerSet passengerSet;
	private FlightList flightList;
	
	private JTextArea flightReport;
	private JButton backButton; 
	private JPanel northPanel,centerPanel,southPanel; 
	private JLabel iconPin;
	private ImageIcon TPLogo;
	
	public TPAdminPage(PassengerSet passengers, FlightList flight, BaggageList baggage) {
		this.passengerSet = passengers;
		this.flightList = flight;
		this.baggageList = baggage;
		
		setupNorthPanel();
		setupCenterPanel(); 
		setupSouthPanel();
		
		setSize(600, 500);
		
		
	}

	
	private void setupNorthPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setLayout(new BorderLayout());
		TPLogo = new ImageIcon(getClass().getResource("TPSmall.png"));
		iconPin = new JLabel(TPLogo);

		// add components to centre panel
		northPanel.add(iconPin, BorderLayout.WEST);

		// add centre panel to border layout
		this.add(northPanel, BorderLayout.NORTH);
	}
	

	List<Baggage> BaggageList = new ArrayList<Baggage>();
	List<Baggage> BaggageList2 = new ArrayList<Baggage>();
	List<Baggage> BaggageList3 = new ArrayList<Baggage>();
	
	
	private void setupCenterPanel() {
		centerPanel = new JPanel();
		flightReport = new JTextArea();
		flightReport.setEditable(false);
		centerPanel.setBackground(Color.WHITE);
		JScrollPane scroll = new JScrollPane (flightReport);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// add components to centre panel
		centerPanel.add(scroll, flightReport);

		// add centre panel to border layout
		this.add(centerPanel, BorderLayout.CENTER);
		
			

		String WoL = "Weight of Luggage (Kg)|"              + "\n" + "---------------------" ;
		String VoB = "Volume of Baggage (In3)|"              + "\n" + "---------------------" ;
		String LoF = "Total excess Baggage Fees (GBP)   |"      + "\n" + "-----------------------------------" ;
        String LoP = "Total Passengers Checked-In   |"    + "\n" + "-----------------------------";
		
		
        String WoL2 = baggageList.calcWeight(BaggageList).toString().replace('[', ' ').replace(']', ' ').replace(",", "");
		String VoB2 = baggageList.calcVolume(BaggageList2).toString().replace('[', ' ').replace(']', ' ').replace(",", "");
		String LoF2 = baggageList.calcFees(BaggageList3).toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');
		String LoP2 = baggageList.passengerCapacity().toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');

		String FRW = "Travel Pigeon Flight Report of Checked In Customers";
		
		String report = 
		FRW + "\n" +
		        "----------------------------------------------------------------------------"
				+ "\n" + WoL  + "\n" + WoL2 + "\n"
				+ "\n" + "----------------------------------------------------------------------------" 
				+ "\n" + VoB  + "\n" + VoB2 + "\n"
				+ "\n" + "----------------------------------------------------------------------------"
				+ "\n" + LoF  + "\n" + LoF2 + "\n"
				+ "\n" + "----------------------------------------------------------------------------"
				+ "\n" + LoP  + "\n" + LoP2 + "\n"
				+ "\n" + "----------------------------------------------------------------------------";
		
	 flightReport.append(report);
     flightReport.setBounds(10,30, 300,300);

	}
     
	
	private void setupSouthPanel() {

		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);

		backButton = new JButton("Back to Check-In Mode");
		backButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(0, 206, 209));
		backButton.setActionCommand("Proceed to Checkin");
		backButton.addActionListener(this);
		//backButton.setPreferredSize(new Dimension(40, 100));

		// add components to south panel
		southPanel.add(backButton);

		// add south panel to main border layout
		this.add(southPanel, BorderLayout.SOUTH);
		
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
     
	@Override
	public void actionPerformed(ActionEvent e) {
		TPWelcome TPWelcome = new TPWelcome(passengerSet, flightList, baggageList);
		TPWelcome.setVisible(true);
		TPWelcome.setLocationRelativeTo(null);
		dispose();
		
	}

}


