import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class TPBag extends JFrame {
	public TPBag() {
	}
	private JLabel iconPin;
	private JButton CIButton;
	private ImageIcon TPLogo;
	
	public TPWelcome() {
		//super("Travel Pigeon Check-In");
		
		getContentPane().setLayout(new BorderLayout()); 
		
		TPLogo = new ImageIcon(getClass().getResource("TP.png"));
		iconPin = new JLabel(TPLogo);
		iconPin.setForeground(Color.WHITE);
		
		
		CIButton = new JButton("Click to Begin Check-In");
		
		CIButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TPDetails TPDetails = new TPDetails();
				TPDetails.setVisible(true);
				dispose();
	
				
			}
			
		});
		
		getContentPane().add(iconPin, BorderLayout.CENTER);
		getContentPane().add(CIButton, BorderLayout.SOUTH);
		
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	
}
}
