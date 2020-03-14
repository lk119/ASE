
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.KeyStroke;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridLayout;

public class GUIBoarding extends JFrame {
	private JPanel contentPane;

	// private JTextField ;
	private JLabel flight1label, flight2label, flight3label;
	// private ImageIcon ;
	private JPanel northPanel, southPanel, flightDisplay1, flightDisplay2, flightDisplay3;
	// private JButton ;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuReport, menuSecurity, menuMain;
	private ImageIcon plane;
	private JTextArea flight1txtarea, flight2txtarea, flight3txtarea;

	/**
	 * Create the frame.
	 */

	public GUIBoarding() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);

		setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 206, 209));
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(0, 206, 209), 10));
		setContentPane(contentPane);

		menu = new JMenu("Menu");
		// menuBar.setMnemonic(KeyEvent.VK_A);
		menuBar.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

		// main item for menu
		menuMain = new JMenuItem("Main Display", KeyEvent.VK_T);
		menuMain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuMain.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuMain);

		// add menu action listener for report
		menuMain.addActionListener(new ActionListener() {

			// action listener for this menu item
			public void actionPerformed(ActionEvent e) {
				GUIMain GUIMain = new GUIMain();
				GUIMain.setVisible(true);
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

		// report item for menu
		menuReport = new JMenuItem("Overall Passenger Report", KeyEvent.VK_T);
		menuReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
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

		menuBar.add(menu);

		setupNorthPanel();
		setupCenterPanel();

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

	private void setupNorthPanel() {

		JLabel mainLabel = new JLabel("Passengers Currently Boarding");
		northPanel = new JPanel();
		northPanel.add(mainLabel);
		contentPane.add(northPanel, BorderLayout.NORTH);
	}

	private void setupCenterPanel() {

		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.CENTER);
		southPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// setup first flight panel
		flightDisplay1 = new JPanel();
		flightDisplay1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay1);
		flightDisplay1.setLayout(new BorderLayout(0, 0));
		flightDisplay1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay1.setLayout(new BorderLayout(0, 0));

		flight1label = new JLabel("Emirates Airlines");
		flight1label.setIcon(new ImageIcon(GUIMain.class.getResource("/view/em.png")));
		flight1label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay1.add(flight1label, BorderLayout.NORTH);

		flight1txtarea = new JTextArea(5, 10);
		flight1txtarea.setEditable(false);
		flightDisplay1.add(flight1txtarea, BorderLayout.CENTER);

		// setup second flight panel
		flightDisplay2 = new JPanel();
		flightDisplay2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay2);
		flightDisplay2.setLayout(new BorderLayout(0, 0));
		flightDisplay2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay2.setLayout(new BorderLayout(0, 0));

		flight2label = new JLabel("British Airways");
		flight2label.setIcon(new ImageIcon(GUIMain.class.getResource("/view/ba.png")));
		flight2label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay2.add(flight2label, BorderLayout.NORTH);

		flight2txtarea = new JTextArea(5, 10);
		flight2txtarea.setEditable(false);
		flightDisplay2.add(flight2txtarea, BorderLayout.CENTER);

		// setup third flight panel
		flightDisplay3 = new JPanel();
		flightDisplay3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(flightDisplay3);
		flightDisplay3.setLayout(new BorderLayout(0, 0));
		flightDisplay3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flightDisplay3.setLayout(new BorderLayout(0, 0));

		flight3label = new JLabel("Qatar Airways");
		flight3label.setIcon(new ImageIcon(GUIMain.class.getResource("/view/qa.png")));
		flight3label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		flightDisplay3.add(flight3label, BorderLayout.NORTH);

		flight3txtarea = new JTextArea(5, 10);
		flight3txtarea.setEditable(false);
		flightDisplay3.add(flight3txtarea, BorderLayout.CENTER);
	}

}
