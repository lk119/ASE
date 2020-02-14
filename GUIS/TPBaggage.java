import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TPBaggage extends JFrame {

	private JTextField heightField, widthField, lengthField;
	private JLabel heightLabel, widthLabel, lengthLabel, imagePin, enterDetailsLabel;
	private ImageIcon baggage, plane;
	private JPanel northPanel, centerPanel, centerTopPanel, centerBottomPanel, heightPanel, lengthPanel, widthPanel,
					eastPanel, westPanel, southPanel;
	private JButton submitCalcButton;
	
	
	public TPBaggage() {
		// set colours of frame
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(600,500);
		
		// set colours of dialog box
		
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.foreground", new Color(0, 206, 209));
		
		
		
		
		/// Setup north panel
		
		northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		enterDetailsLabel = new JLabel("Please Enter Your Details Below");
		enterDetailsLabel.setForeground(new Color(0, 206, 209));
		northPanel.add(enterDetailsLabel);
		enterDetailsLabel.setBackground(Color.WHITE);
		
		
		
		/// Setup center panel
		
		centerPanel = new JPanel();
		centerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		centerPanel.setBackground(Color.WHITE);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
			// setup center top panel
			
		centerTopPanel = new JPanel();
		centerPanel.add(centerTopPanel, BorderLayout.NORTH);
		centerTopPanel.setBackground(Color.WHITE);
		centerTopPanel.setAlignmentX(1.0f);
		centerTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			// setup height sub-panel
		
		heightPanel = new JPanel();
		centerTopPanel.add(heightPanel);
		heightPanel.setBackground(Color.WHITE);
		heightPanel.setLayout(new BorderLayout(0, 0));
		
		heightLabel = new JLabel("Height");
		heightLabel.setForeground(new Color(0, 206, 209));
		heightPanel.add(heightLabel, BorderLayout.NORTH);
		
		heightField = new JTextField();
		heightPanel.add(heightField, BorderLayout.SOUTH);
		heightField.setToolTipText("Height");
		heightField.setColumns(1);
		
			// setup width sub-panel
		
		widthPanel = new JPanel();
		centerTopPanel.add(widthPanel);
		widthPanel.setBackground(Color.WHITE);
		widthPanel.setLayout(new BorderLayout(0, 0));
		
		widthLabel = new JLabel("Width");
		widthLabel.setForeground(new Color(0, 206, 209));
		widthPanel.add(widthLabel, BorderLayout.NORTH);
		
		widthField = new JTextField();
		widthField.setToolTipText("Height");
		widthField.setColumns(1);
		widthPanel.add(widthField, BorderLayout.SOUTH);
		
			// setup length sub-panel
		
		lengthPanel = new JPanel();
		centerTopPanel.add(lengthPanel);

		lengthPanel.setBackground(Color.WHITE);
		lengthPanel.setLayout(new BorderLayout(0, 0));
		
		lengthLabel = new JLabel("Length");
		lengthLabel.setForeground(new Color(0, 206, 209));
		lengthPanel.add(lengthLabel, BorderLayout.NORTH);
		
		lengthField = new JTextField();
		lengthField.setToolTipText("Height");
		lengthField.setColumns(1);
		lengthPanel.add(lengthField, BorderLayout.SOUTH);
		
		
			// setup center bottom panel 
		
		centerBottomPanel = new JPanel();
		centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);
		centerBottomPanel.setBackground(Color.WHITE);
		centerBottomPanel.setLayout(new BorderLayout(0, 0));
		
		baggage = new ImageIcon(getClass().getResource("suitcase2.png"));
		imagePin = new JLabel(baggage);
		centerBottomPanel.add(imagePin);
		
		
		
		/// setup east panel
		
		eastPanel = new JPanel();
		
		eastPanel.setBackground(Color.WHITE);
		eastPanel.setAlignmentX(1.0f);
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		/// setup west panel
		
		westPanel = new JPanel();
		
		westPanel.setBackground(Color.WHITE);
		westPanel.setAlignmentX(1.0f);
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		///setup south panel
		
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		submitCalcButton = new JButton("Submit & Calculate");
		submitCalcButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		submitCalcButton.setForeground(new Color(0, 206, 209));
		submitCalcButton.setBackground(new Color(0, 206, 209));
		submitCalcButton.setActionCommand("Submit & Calculate");

		southPanel.add(submitCalcButton);
		submitCalcButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				plane = new ImageIcon(getClass().getResource("Plane2.png"));
				//imagePin = new JLabel(baggage)
				
				JOptionPane.showMessageDialog(null,
					    "Check-In Successfull. \nPlease head to your departure gate.", 
					    "Check-In Successfull",
					    JOptionPane.INFORMATION_MESSAGE, plane);
						dispose();
			}
		});
		
		


	}
	}

