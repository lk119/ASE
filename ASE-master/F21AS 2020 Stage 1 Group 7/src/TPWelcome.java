import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TPWelcome extends JFrame {

	//private textPanel textPanel;
	private JLabel iconPin;
	private JButton CIButton;
	private ImageIcon TPLogo;
	private JPanel centerPanel, southPanel; 
	
	public TPWelcome() {
		super("Travel Pigeon Check-In");
		
		setLayout(new BorderLayout(0,0));
		
		// setup center panel
		
		centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		TPLogo = new ImageIcon(getClass().getResource("TP.png"));
		iconPin = new JLabel(TPLogo);
		getContentPane().setBackground(Color.WHITE);
		centerPanel.add(iconPin);

		//setup south panel
		
		southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		CIButton = new JButton("Click to Begin Check-In");
		CIButton.setForeground(new Color(0, 206, 209));
		CIButton.setBackground(new Color(0, 206, 209));
		
		centerPanel.add(CIButton);
		
		CIButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TPDetails TPDetails = new TPDetails(null);
				TPDetails.setVisible(true);
				TPDetails.setLocationRelativeTo(null);
				dispose();
	
				
			}
			
		});
		
		add(iconPin, BorderLayout.CENTER);
		add(CIButton, BorderLayout.SOUTH);
		
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	
}
}