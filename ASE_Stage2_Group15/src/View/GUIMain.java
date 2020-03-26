package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.FlightCounterThread;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;

public class GUIMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain frame = new GUIMain();
					frame.setBackground(new Color(0, 206, 209));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// private JTextField ;
	// private JLabel ;
	// private ImageIcon ;
	private JPanel northPanel, centerPanel, southPanel, desk1, desk2, desk3, desk4, buttonPanel1, buttonPanel2,
			buttonPanel3, buttonPanel4, flightDisplay1, flightDisplay2, flightDisplay3;
	private JButton openBtn1, closeBtn1, openBtn2, closeBtn2, openBtn3, closeBtn3, openBtn4, closeBtn4;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuReport, menuSecurity, menuBoarding;
	private JTextArea desk1TxtArea, desk2TextArea, desk3TextArea, desk4TextArea, queueTxtArea;
	private ImageIcon em, ba, qa, plane;
	private JScrollPane queueScroll;
	private JLabel queueLbl, desk1Lbl, desk2lbl, desk3lbl, desk4lbl, flight1label, flight2label, flight3label;
	private JTextArea flight1txtarea, flight2txtarea, flight3txtarea; 
	
	/**
	 * Create the frame.
	 */

	public GUIMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);

		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 206, 209));
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(0, 206, 209), 10));
		setContentPane(contentPane);

		menu = new JMenu("Menu");
		// menuBar.setMnemonic(KeyEvent.VK_A);
		menuBar.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

		// report item for menu
		menuReport = new JMenuItem("Overall Passenger Report", KeyEvent.VK_T);
		menuReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuReport.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuReport);

		// add menu action listener for report
		menuReport.addActionListener(new ActionListener() {

			// action listener for this menu item
			public void actionPerformed(ActionEvent e) {
				GUIReport GUIReport = new GUIReport();
				GUIReport.setVisible(true);
				dispose();
			}
		});

		// security item for menu
		menuSecurity = new JMenuItem("View Customers In Security", KeyEvent.VK_T);
		menuSecurity.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuSecurity.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuSecurity);

		menuSecurity.addActionListener(new ActionListener() {

			// action listener for this menu item
			public void actionPerformed(ActionEvent e) {
				GUISecurity GUISecurity = new GUISecurity();
				GUISecurity.setVisible(true);
				dispose();
			}
		});

		// passenger boarding item for menu
		menuBoarding = new JMenuItem("View Passengers In Boarding", KeyEvent.VK_T);
		menuBoarding.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuBoarding.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuBoarding);

		// add menu action listener for report
		menuBoarding.addActionListener(new ActionListener() {

			// action listener for this menu item
			public void actionPerformed(ActionEvent e) {
				GUIBoarding GUIBoarding = new GUIBoarding();
				GUIBoarding.setVisible(true);
				dispose();
			}
		});

		menuBar.add(menu);

		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		addWindowListener(new WindowAdapter() {
			/**
			 * Overrides the standard close operation with JOptionPane allowing the user to
			 * return to the application if the NO option is selected. If the YES option is
			 * selected then the application will write the final flight report and then
			 * exit
			 * 
			 * @param e the event to be processed
			 */
			public void windowClosing(WindowEvent e) {
				JFrame frame = new JFrame();
				plane = new ImageIcon(getClass().getResource("plane3.gif"));
				int confirmed = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Programme",
						JOptionPane.YES_NO_OPTION, 1, plane);
				// if user selects yes option - produce flight report and exit
				if (confirmed == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

	}

	public GUIMain(FlightCounterThread model) {
		// TODO Auto-generated constructor stub
	}

	private void setupNorthPanel() {

		northPanel = new JPanel();
		northPanel.setSize(150, 150);
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		queueScroll = new JScrollPane();
		northPanel.add(queueScroll);
		queueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		queueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		queueTxtArea = new JTextArea(5, 20);
		queueTxtArea.setLineWrap(true);
		queueTxtArea.setWrapStyleWord(true);
		queueScroll.setViewportView(queueTxtArea);
		queueTxtArea.setEditable(false);
		queueTxtArea.setSize(100, 100);

		queueLbl = new JLabel("Check-In Queue");
		queueLbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		queueLbl.setHorizontalAlignment(SwingConstants.LEFT);
		northPanel.add(queueLbl, BorderLayout.NORTH);
	}

	private void setupCenterPanel() {

		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 4, 0, 0));

		// desk 1
		desk1 = new JPanel();

		desk1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(desk1);
		desk1.setLayout(new BorderLayout(0, 0));

		desk1Lbl = new JLabel("Check-In Desk 1");
		desk1Lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		desk1.add(desk1Lbl, BorderLayout.NORTH);
		desk1TxtArea = new JTextArea();
		desk1TxtArea.setSize(150, 150);
		desk1TxtArea.setEditable(false);
		desk1.add(desk1TxtArea, BorderLayout.CENTER);

		buttonPanel1 = new JPanel();
		openBtn1 = new JButton("Open");
		closeBtn1 = new JButton("Close");
		buttonPanel1.add(openBtn1);
		buttonPanel1.add(closeBtn1);
		desk1.add(buttonPanel1, BorderLayout.SOUTH);

		// desk 2
		desk2 = new JPanel();
		desk2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(desk2);
		desk2.setLayout(new BorderLayout(0, 0));

		desk2lbl = new JLabel("Check-In Desk 2");
		desk2lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		desk2.add(desk2lbl, BorderLayout.NORTH);

		desk2TextArea = new JTextArea();
		desk2TextArea.setSize(150, 150);
		desk2TextArea.setEditable(false);
		desk2.add(desk2TextArea);

		buttonPanel2 = new JPanel();
		openBtn2 = new JButton("Open");
		closeBtn2 = new JButton("Close");
		buttonPanel2.add(openBtn2);
		buttonPanel2.add(closeBtn2);
		desk2.add(buttonPanel2, BorderLayout.SOUTH);

		// desk 3
		desk3 = new JPanel();
		desk3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(desk3);
		desk3.setLayout(new BorderLayout(0, 0));

		desk3lbl = new JLabel("Check-In Desk 3");
		desk3lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		desk3.add(desk3lbl, BorderLayout.NORTH);

		desk3TextArea = new JTextArea();
		desk3TextArea.setSize(150, 150);
		desk3TextArea.setEditable(false);
		desk3.add(desk3TextArea);

		buttonPanel3 = new JPanel();
		openBtn3 = new JButton("Open");
		closeBtn3 = new JButton("Close");
		buttonPanel3.add(openBtn3);
		buttonPanel3.add(closeBtn3);
		desk3.add(buttonPanel3, BorderLayout.SOUTH);

		// desk 4
		desk4 = new JPanel();
		desk4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(desk4);
		desk4.setLayout(new BorderLayout(0, 0));

		desk4lbl = new JLabel("Check-In Desk 4");
		desk4lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		desk4.add(desk4lbl, BorderLayout.NORTH);

		desk4TextArea = new JTextArea();
		desk4TextArea.setSize(150, 150);
		desk4TextArea.setEditable(false);
		desk4.add(desk4TextArea);

		buttonPanel4 = new JPanel();
		openBtn4 = new JButton("Open");
		closeBtn4 = new JButton("Close");
		buttonPanel4.add(openBtn4);
		buttonPanel4.add(closeBtn4);
		desk4.add(buttonPanel4, BorderLayout.SOUTH);

	}

	private void setupSouthPanel() {

		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// setup first flight panel
		flightDisplay1 = new JPanel();
		flightDisplay1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay1);
		flightDisplay1.setLayout(new BorderLayout(0, 0));
		flightDisplay1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay1.setLayout(new BorderLayout(0, 0));

		flight1label = new JLabel("Emirates Airlines");
		flight1label.setIcon(new ImageIcon(GUIMain.class.getResource("/View/em.png")));
		flight1label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay1.add(flight1label, BorderLayout.NORTH);

		flight1txtarea = new JTextArea(5, 10);
		flightDisplay1.add(flight1txtarea, BorderLayout.SOUTH);

		// setup second flight panel
		flightDisplay2 = new JPanel();
		flightDisplay2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay2);
		flightDisplay2.setLayout(new BorderLayout(0, 0));
		flightDisplay2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay2.setLayout(new BorderLayout(0, 0));

		flight2label = new JLabel("British Airways");		flight2label.setIcon(new ImageIcon(GUIMain.class.getResource("/View/ba.png")));

		flight2label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay2.add(flight2label, BorderLayout.NORTH);

		flight2txtarea = new JTextArea(5, 10);
		flightDisplay2.add(flight2txtarea, BorderLayout.SOUTH);

		// setup third flight panel
		flightDisplay3 = new JPanel();
		flightDisplay3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay3);
		flightDisplay3.setLayout(new BorderLayout(0, 0));
		flightDisplay3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay3.setLayout(new BorderLayout(0, 0));

		flight3label = new JLabel("Qatar Airways");
		flight3label.setIcon(new ImageIcon(GUIMain.class.getResource("/View/qa.png")));
		flight3label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay3.add(flight3label, BorderLayout.NORTH);

		flight3txtarea = new JTextArea(5, 10);
		flightDisplay3.add(flight3txtarea, BorderLayout.SOUTH);
	}

}