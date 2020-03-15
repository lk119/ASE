package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.KeyStroke;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUISecurity extends JFrame {
	
	private JPanel contentPane;

	// private JTextField ;
	private JLabel queueLbl;
	// private ImageIcon ;
	private JPanel northPanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuMain, menuReport, menuBoarding;
	private ImageIcon plane;
	private JScrollPane queueScroll; 
	private JTextArea queueTxtArea;

	/**
	 * Create the frame.
	 */

	public GUISecurity() {
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

		// Main item for menu
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

		// report item for menu
		menuReport = new JMenuItem("Overall Passenger Report", KeyEvent.VK_T);
		menuReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
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

		setupCenterPanel();
	}

	private void setupCenterPanel() {

		northPanel = new JPanel();
		northPanel.setSize(150, 150);
		contentPane.add(northPanel, BorderLayout.CENTER);
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

		queueLbl = new JLabel("Passengers Currently In Security");
		queueLbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		queueLbl.setHorizontalAlignment(SwingConstants.LEFT);
		northPanel.add(queueLbl, BorderLayout.NORTH);
	}

}