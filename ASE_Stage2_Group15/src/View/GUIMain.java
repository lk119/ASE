package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Passenger;
import Model.PassengerSet;
import Model.PassengersInQueue;

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
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

public class GUIMain extends JFrame implements Observer{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Passenger passenger;
	private PassengerSet passengerset;
	private PassengersInQueue passengersinqueue;
	


	
	
	
	// private JTextField ;
	// private JLabel ;
	// private ImageIcon ;
	private JPanel northPanel, centerPanel, southPanel, FirstandBusinessDesk, EconomyDesk1, EconomyDesk2, EconomyDesk3,
			buttonPanel1, buttonPanel2, buttonPanel3, buttonPanel4, checkinQueue, securityQueue, bQ;
	private JButton openBtn1, closeBtn1, openBtn2, closeBtn2, openBtn3, closeBtn3, openBtn4, closeBtn4, processButton;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuReport, menuSecurity, menuBoarding;
	private JTextArea desk1TxtArea, desk2TextArea, desk3TextArea, desk4TextArea, checkinQueueTxtArea,
			securityQueueTxtArea, bTxtArea;
	private ImageIcon em, ba, qa, plane;
	private JScrollPane checkinQueueScroll, securityQueueScroll, bQS;
	private JLabel desk1Lbl, desk2lbl, desk3lbl, desk4lbl, checkinQueuelabel, securityQueuelabel,
			bQL;
	

	/**
	 * Create the frame.
	 * @param pq 
	 * @param p 
	 * @param ps 
	 */
	
	public GUIMain (PassengersInQueue pq) {
		  this.passengersinqueue = pq;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1500, 1500);

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
		menuReport = new JMenuItem("Flight Capacity Report", KeyEvent.VK_T);
		menuReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuReport.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuReport);

		// add menu action listener for report
		menuReport.addActionListener(new ActionListener() {

			// action listener for this menu item
			public void actionPerformed(ActionEvent e) {
				GUIReport GUIReport = new GUIReport(pq);
				GUIReport.setVisible(true);
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

	
	private void setupNorthPanel() {

		// create the basic structure of north panel
		northPanel = new JPanel();
		//northPanel.setSize(0, 0);
		contentPane.add(northPanel, BorderLayout.NORTH);

		// create the button to initiate the programme
		processButton = new JButton("Open Check-In");
		processButton.setSize(1, 1);
		northPanel.add(processButton);

	}

	private void setupCenterPanel() {

		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 4, 0, 0));

		// desk 1
		FirstandBusinessDesk = new JPanel();
		FirstandBusinessDesk.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(FirstandBusinessDesk);
		FirstandBusinessDesk.setLayout(new BorderLayout(0, 0));

		desk1Lbl = new JLabel("First & Business Check-In Desk 1");
		desk1Lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		FirstandBusinessDesk.add(desk1Lbl, BorderLayout.NORTH);
		desk1TxtArea = new JTextArea();
		desk1TxtArea.setSize(60, 60);
		desk1TxtArea.setEditable(false);
		FirstandBusinessDesk.add(desk1TxtArea, BorderLayout.CENTER);

		buttonPanel1 = new JPanel();
		openBtn1 = new JButton("Open");
		closeBtn1 = new JButton("Close");
		buttonPanel1.add(openBtn1);
		buttonPanel1.add(closeBtn1);
		FirstandBusinessDesk.add(buttonPanel1, BorderLayout.SOUTH);
		
		// add info to be displayed
		//passengersinqueue.BusinessPicker();
		//passengersinqueue.firstPicker();

		// desk 2
		EconomyDesk1 = new JPanel();
		EconomyDesk1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(EconomyDesk1);
		EconomyDesk1.setLayout(new BorderLayout(0, 0));

		desk2lbl = new JLabel("Economy Check-In Desk 1");
		desk2lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		EconomyDesk1.add(desk2lbl, BorderLayout.NORTH);

		desk2TextArea = new JTextArea();
		desk2TextArea.setSize(60, 60);
		desk2TextArea.setEditable(false);
		EconomyDesk1.add(desk2TextArea);

		buttonPanel2 = new JPanel();
		openBtn2 = new JButton("Open");
		closeBtn2 = new JButton("Close");
		buttonPanel2.add(openBtn2);
		buttonPanel2.add(closeBtn2);
		EconomyDesk1.add(buttonPanel2, BorderLayout.SOUTH);
		
		// add info to be displayed
		//passengersinqueue.EconomyPicker();

		// desk 3
		EconomyDesk2 = new JPanel();
		EconomyDesk2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(EconomyDesk2);
		EconomyDesk2.setLayout(new BorderLayout(0, 0));

		desk3lbl = new JLabel("Economy Check-In Desk 2");
		desk3lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		EconomyDesk2.add(desk3lbl, BorderLayout.NORTH);

		desk3TextArea = new JTextArea();
		desk3TextArea.setSize(60, 60);
		desk3TextArea.setEditable(false);
		EconomyDesk2.add(desk3TextArea);

		buttonPanel3 = new JPanel();
		openBtn3 = new JButton("Open");
		closeBtn3 = new JButton("Close");
		buttonPanel3.add(openBtn3);
		buttonPanel3.add(closeBtn3);
		EconomyDesk2.add(buttonPanel3, BorderLayout.SOUTH);
		
		// add info to be displayed
		//passengersinqueue.EconomyPicker();

		// desk 4
		EconomyDesk3 = new JPanel();
		EconomyDesk3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		centerPanel.add(EconomyDesk3);
		EconomyDesk3.setLayout(new BorderLayout(0, 0));

		desk4lbl = new JLabel("Economy Check-In Desk 3");
		desk4lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		EconomyDesk3.add(desk4lbl, BorderLayout.NORTH);

		desk4TextArea = new JTextArea();
		desk4TextArea.setSize(60, 60);
		desk4TextArea.setEditable(false);
		EconomyDesk3.add(desk4TextArea);

		buttonPanel4 = new JPanel();
		openBtn4 = new JButton("Open");
		closeBtn4 = new JButton("Close");
		buttonPanel4.add(openBtn4);
		buttonPanel4.add(closeBtn4);
		EconomyDesk3.add(buttonPanel4, BorderLayout.SOUTH);
		
		// add info to be displayed
		//passengersinqueue.EconomyPicker();

	}

	private void setupSouthPanel() {

		southPanel = new JPanel();
		southPanel.setSize(600, 600);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 3, 0, 0));

		// set up passengers in check-in queue panel
		checkinQueue = new JPanel();
		checkinQueue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(checkinQueue);
		checkinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		checkinQueuelabel = new JLabel("Passengers in Check-In Queue");
		checkinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		checkinQueue.add(checkinQueuelabel, BorderLayout.NORTH);

		// create text area
		checkinQueueTxtArea = new JTextArea(40, 50);
		checkinQueueTxtArea.setLineWrap(true);
		checkinQueueTxtArea.setWrapStyleWord(true);
		checkinQueueTxtArea.setEditable(false);
		checkinQueueTxtArea.setSize(100, 100);
		checkinQueue.add(checkinQueueTxtArea, BorderLayout.CENTER);

		// create scroll bar
		checkinQueueScroll = new JScrollPane();
		checkinQueue.add(checkinQueueScroll);
		checkinQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		checkinQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkinQueueScroll.setViewportView(checkinQueueTxtArea);
		
		
		//add info to be displayed
		//passengersinqueue.puti();
		
		
		

		// set up passengers in security queue panel
		securityQueue = new JPanel();
		securityQueue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(securityQueue);
		securityQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		securityQueuelabel = new JLabel("Passengers in Security Queue");
		securityQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		securityQueue.add(securityQueuelabel, BorderLayout.NORTH);

		// create text area
		securityQueueTxtArea = new JTextArea(15, 20);
		securityQueueTxtArea.setLineWrap(true);
		securityQueueTxtArea.setWrapStyleWord(true);
		securityQueueTxtArea.setEditable(false);
		securityQueueTxtArea.setSize(100, 100);
		securityQueue.add(securityQueueTxtArea, BorderLayout.CENTER);

		// create scroll bar
		securityQueueScroll = new JScrollPane();
		securityQueue.add(securityQueueScroll);
		securityQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		securityQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		securityQueueScroll.setViewportView(securityQueueTxtArea);
		
		// add info to be displayed
		//passengersinqueue.PickerforSecurity();
		
		

		// set up passengers in boarding queue
		bQ = new JPanel();
		bQ.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(bQ);
		bQ.setLayout(new BorderLayout(0, 0));

		bQL = new JLabel("Passengers in Boarding Queue");
		bQL.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		bQ.add(bQL, BorderLayout.NORTH);

		bTxtArea = new JTextArea(15, 20);
		bTxtArea.setLineWrap(true);
		bTxtArea.setWrapStyleWord(true);
		bTxtArea.setEditable(false);
		bTxtArea.setSize(100, 100);
		bQ.add(bTxtArea, BorderLayout.CENTER);

		bQS = new JScrollPane();
		bQ.add(bQS);
		bQS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bQS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		bQS.setViewportView(bTxtArea);
		
		// add info to be displayed
		//passengersinqueue.PickerforBoarding();

	}

/////////////////////////////////////////////////////
//MVC pattern - allows listeners to be added
	public void addGUIMainListener(ActionListener al) {
		processButton.addActionListener(al);
	}

	public void disableProcessButton() {
		processButton.setEnabled(false);

	}


	@Override
	public void update(Observable o, Object arg) {
		passengersinqueue.puti();
		
	}

}
