package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import Controller.GUIController;
import Model.Baggage;
import Model.Passenger;
import Model.PassengersInQueue;

/**
 * Advanced Software Engineering Assignment Stage 2 GUI Class - Creates GUI that
 * initiates the check-in process and displays all relevant sections of
 * information for the simulation of passengers queueing for and boarding a
 * flight.
 * 
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */
public class GUIMain extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private PassengersInQueue passengersinqueue;

	private GUIController Gcontroller;

	private Passenger passenger;

	private Date myDate = new Date();

	private JPanel northPanel, centerPanel, southPanel, FirstandBusinessDesk, economyDesk1, economyDesk2, economyDesk3,
			processButtonPanel, checkinQueue, firstcheckinQueue, securityQueue, boardingQueue, checkInPanel,
			checkinLabelPanel, secBoardPanel, queueGroupPanel, emiratesDisplay, baDisplay, qatarDisplay,
			baProgressBarPanel, baVolumeProgresBarPanel, baWeightProgresBarPanel, qaProgressBarPanel,
			qaVolumeProgresBarPanel, qaWeightProgresBarPanel, eaProgressBarPanel, eaVolumeProgresBarPanel,
			eaWeightProgresBarPanel, baPassengerProgresBarPanel, qaPassengerProgresBarPanel, eaPassengerProgresBarPanel,
			timerPanel, eaFeesArea, qaFeesArea, baFeesArea, countdownPanel;

	private JButton processButton;

	private JTextArea desk1TxtArea, ecoDesk1TxtArea, ecoDesk2TxtArea, ecoDesk3TxtArea, checkinQueueTxtArea,
			firstcheckinQueueTxtArea, securityQueueTxtArea, boardingQueueTxtArea, timeArea;

	private ImageIcon plane;
	private JScrollPane checkinQueueScroll, firstcheckinQueueScroll, securityQueueScroll, boardingQueueScroll, Scroll;
	private JLabel desk1Lbl, ecoDesk1Label, ecoDesk2Label, ecoDesk3Label, checkinQueuelabel, firstcheckinQueuelabel,
			securityQueuelabel, boardingQueueLabel, fbGroupLabel, emiratesLabel, baLabel, qatarLabel, baVolumeLabel,
			baWeightLabel, qaVolumeLabel, qaWeightLabel, eaVolumeLabel, eaWeightLabel, baPassengerLabel,
			qaPassengerLabel, eaPassengerLabel, timerLabel, eaFeeAreaLabel, baFeeAreaLabel, qaFeeAreaLabel, eaFeesLabel,
			baFeesLabel, qaFeesLabel, countdownLabel;

	public JSlider timerSlider;

	private Color cyan = new Color(177, 231, 240);

	private BevelBorder bevelBoarder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);

	private ImageIcon qatarIcon = new ImageIcon(GUIMain.class.getResource("qa.png"));
	private ImageIcon baIcon = new ImageIcon(GUIMain.class.getResource("ba.png"));
	private ImageIcon emiratesIcon = new ImageIcon(GUIMain.class.getResource("em.png"));

	public JProgressBar baWeight, baVolume, qaWeight, qaVolume, eaWeight, eaVolume, baPassenger, eaPassenger,
			qaPassenger;

	public int time = 110, timeForClosing = 110;

	/**
	 * Create the frame.
	 * 
	 * @param pq Passengers in check-in Queue
	 * @param p  Passengers
	 * @param c  GUI controller
	 */
	public GUIMain(PassengersInQueue pq, Passenger p, GUIController c) {

		passengersinqueue = pq;
		passenger = p;
		Gcontroller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 1000);

		// sets the look and feel of the frame
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
		}

		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createLineBorder(cyan));
		setContentPane(contentPane);

		// add the panels to the main frame
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

	/**
	 * Sets up the north panel to hold the button that initiates/opens the check-in
	 * process as well as the text areas to display both economy and first/business
	 * passengers currently in the check-in queue. Contains JPanel, JButton,
	 * JTextField and JScrollPane and adds them to the top section of the layout on
	 * the JFrame
	 * 
	 */
	private void setupNorthPanel() {

		// create the basic structure of north panel for checkin of passengers
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		// northPanel.setSize(10, 10);

		// northPanel.setBorder(BorderFactory.createLineBorder(cyan, 10));
		contentPane.add(northPanel, BorderLayout.NORTH);

		// create the button to initiate the programme
		processButtonPanel = new JPanel();
		processButtonPanel.setBackground(cyan);
		processButton = new JButton("Open Check-In");
		processButton.setPreferredSize(new Dimension(550, 20));
		processButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		processButtonPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 5, 10, cyan));
		processButtonPanel.add(processButton);

		northPanel.add(processButtonPanel, BorderLayout.NORTH);

		queueGroupPanel = new JPanel();
		queueGroupPanel.setLayout(new GridLayout(0, 2, 0, 0));

		northPanel.add(queueGroupPanel, BorderLayout.CENTER);

		northPanel.isPaintingTile();
		/////////////////////////
		// CHECK IN QUEUE ECONOMY
		/////////////////////////

		// set up passengers in check-in queue panel
		checkinQueue = new JPanel();
		checkinQueue.setBorder(bevelBoarder);
		checkinQueue.setBorder(BorderFactory.createMatteBorder(5, 10, 10, 5, cyan));
		queueGroupPanel.add(checkinQueue, BorderLayout.CENTER);
		checkinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		checkinQueuelabel = new JLabel("Economy Class Passengers in Check-In Queue", JLabel.CENTER);
		checkinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		checkinQueue.add(checkinQueuelabel, BorderLayout.NORTH);

		// create text area
		checkinQueueTxtArea = new JTextArea(5, 20);
		checkinQueueTxtArea.setLineWrap(true);
		checkinQueueTxtArea.setWrapStyleWord(true);
		checkinQueueTxtArea.setEditable(false);
		checkinQueueTxtArea.setSize(50, 50);
		checkinQueue.add(checkinQueueTxtArea);

		// create scroll bar and add it to the check-in panel, set to always on
		checkinQueueScroll = new JScrollPane();
		checkinQueue.add(checkinQueueScroll);
		checkinQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		checkinQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkinQueueScroll.setViewportView(checkinQueueTxtArea);

		///////////////////////////
		// CHECK IN FIRST & BUISNESS QUEUE
		///////////////////////////

		// set up passengers in check-in queue panel
		firstcheckinQueue = new JPanel();
		firstcheckinQueue.setBorder(bevelBoarder);
		firstcheckinQueue.setBorder(BorderFactory.createMatteBorder(5, 5, 10, 10, cyan));
		queueGroupPanel.add(firstcheckinQueue, BorderLayout.CENTER);
		firstcheckinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		firstcheckinQueuelabel = new JLabel("First and Busincess Class Passengers in Check-In Queue", JLabel.CENTER);
		firstcheckinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		firstcheckinQueue.add(firstcheckinQueuelabel, BorderLayout.NORTH);

		// create text area
		firstcheckinQueueTxtArea = new JTextArea(5, 20);
		firstcheckinQueueTxtArea.setLineWrap(true);
		firstcheckinQueueTxtArea.setWrapStyleWord(true);
		firstcheckinQueueTxtArea.setEditable(false);
		firstcheckinQueueTxtArea.setSize(100, 100);

		firstcheckinQueue.add(firstcheckinQueueTxtArea, BorderLayout.CENTER);

		// create scroll bar and add it to the first class and business panel, set to
		// always on
		firstcheckinQueueScroll = new JScrollPane();
		firstcheckinQueue.add(firstcheckinQueueScroll);
		firstcheckinQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		firstcheckinQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		firstcheckinQueueScroll.setViewportView(firstcheckinQueueTxtArea);

	}

	/**
	 * Sets up the centre panel which contains all relevant sections for boarding
	 * and security such as total excess baggage fees collected, time remaining
	 * until check-in desks close and those passengers who are currently at the
	 * check-in desks as well as the passengers who have checked-in and are now
	 * either in the queue for security or boarding.
	 * 
	 * Contains JPanel, JLabel, JTextArea and JScrollPane and adds these to the
	 * central section of the JFrame.
	 * 
	 */
	private void setupCenterPanel() {

		// the main panel for the center section
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		/////////////////////////
		// Arrangement setup
		/////////////////////////

		// create & setup JPanel for checkin items to be added to
		checkInPanel = new JPanel();
		checkInPanel.setLayout(new GridLayout(0, 4, 0, 0));
		checkInPanel.setBorder(bevelBoarder);

		// creates an outer JPanel to add all queue items to allow an overall label to
		// be added
		checkinLabelPanel = new JPanel();
		checkinLabelPanel.setBorder(bevelBoarder);
		checkinLabelPanel.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 10, cyan));
		fbGroupLabel = new JLabel("Check-In Desks", JLabel.CENTER);
		checkinLabelPanel.setLayout(new BorderLayout());
		checkinLabelPanel.add(fbGroupLabel, BorderLayout.NORTH);
		// adds the checkinPanel JPanel to this panel
		checkinLabelPanel.add(checkInPanel);

		// create & setup a JPanel that contains all security and boarding related items
		secBoardPanel = new JPanel();
		secBoardPanel.setLayout(new GridLayout(0, 3, 0, 0));
		secBoardPanel.setBorder(bevelBoarder);
		secBoardPanel.setBorder(BorderFactory.createLineBorder(cyan, 10));

		// adds checkin, security and boarding elements to the central pane
		centerPanel.add(checkinLabelPanel);
		centerPanel.add(secBoardPanel);

		/////////////////////////
		// ECONOMY CHECK-IN 1 DESK
		/////////////////////////

		// setup economy desk 1 JPanel
		economyDesk1 = new JPanel();
		economyDesk1.setBorder(bevelBoarder);
		checkInPanel.add(economyDesk1);
		economyDesk1.setLayout(new BorderLayout(0, 0));

		// add label to the desk with custom font
		ecoDesk1Label = new JLabel("Economy 1");
		ecoDesk1Label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		economyDesk1.add(ecoDesk1Label, BorderLayout.NORTH);

		// add text area to the desk, where passengers names will be printed
		ecoDesk1TxtArea = new JTextArea();
		ecoDesk1TxtArea.setSize(60, 60);
		ecoDesk1TxtArea.setEditable(false);

		economyDesk1.add(ecoDesk1TxtArea);

		// create scroll bar

		Scroll = new JScrollPane();
		economyDesk1.add(Scroll);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		Scroll.setViewportView(ecoDesk1TxtArea);

		/////////////////////////
		// ECONOMY CHECK-IN 2 DESK
		/////////////////////////

		// setup economy desk 2 JPanel
		economyDesk2 = new JPanel();
		economyDesk2.setBorder(bevelBoarder);
		checkInPanel.add(economyDesk2);
		economyDesk2.setLayout(new BorderLayout(0, 0));

		// add label to the desk with custom font
		ecoDesk2Label = new JLabel("Economy 2");
		ecoDesk2Label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		economyDesk2.add(ecoDesk2Label, BorderLayout.NORTH);

		// add text area to the desk, where passengers names will be printed
		ecoDesk2TxtArea = new JTextArea();
		ecoDesk2TxtArea.setSize(60, 60);
		ecoDesk2TxtArea.setEditable(false);
		economyDesk2.add(ecoDesk2TxtArea);

		// create scroll bar

		Scroll = new JScrollPane();
		economyDesk2.add(Scroll);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		Scroll.setViewportView(ecoDesk2TxtArea);

		/////////////////////////
		// ECONOMY CHECK-IN 3 DESK
		/////////////////////////

		// setup economy desk 3 JPanel
		economyDesk3 = new JPanel();
		economyDesk3.setBorder(bevelBoarder);
		checkInPanel.add(economyDesk3);
		economyDesk3.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		ecoDesk3Label = new JLabel("Economy 3");
		ecoDesk3Label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		economyDesk3.add(ecoDesk3Label, BorderLayout.NORTH);

		// add text area to the desk, where passengers names will be printed
		ecoDesk3TxtArea = new JTextArea();
		ecoDesk3TxtArea.setSize(60, 60);
		ecoDesk3TxtArea.setEditable(false);

		economyDesk3.add(ecoDesk3TxtArea);

		// create scroll bar

		Scroll = new JScrollPane();
		economyDesk3.add(Scroll);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		Scroll.setViewportView(ecoDesk3TxtArea);

		/////////////////////////////////
		// FIRST & BUSINESS CHECK-IN DESK
		////////////////////////////////

		// setup business and first class check-in JPanel
		FirstandBusinessDesk = new JPanel();
		FirstandBusinessDesk.setBorder(bevelBoarder);
		checkInPanel.add(FirstandBusinessDesk);
		FirstandBusinessDesk.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		desk1Lbl = new JLabel("First & Business");
		desk1Lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		FirstandBusinessDesk.add(desk1Lbl, BorderLayout.NORTH);

		// add text area to the desk, where passengers names will be printed
		desk1TxtArea = new JTextArea();
		desk1TxtArea.setSize(60, 60);
		desk1TxtArea.setEditable(false);

		FirstandBusinessDesk.add(desk1TxtArea);

		// create scroll bar

		Scroll = new JScrollPane();
		FirstandBusinessDesk.add(Scroll);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		Scroll.setViewportView(desk1TxtArea);

		///////////////////////////
		// SECURITY CHECK
		///////////////////////////

		// set up passengers in security queue panel
		securityQueue = new JPanel();
		securityQueue.setBorder(bevelBoarder);
		secBoardPanel.add(securityQueue);
		securityQueue.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		securityQueuelabel = new JLabel("Passengers in Security Queue");
		securityQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		securityQueue.add(securityQueuelabel, BorderLayout.NORTH);

		// create text area - will display what customers are passing through security
		securityQueueTxtArea = new JTextArea(15, 20);
		securityQueueTxtArea.setLineWrap(true);
		securityQueueTxtArea.setWrapStyleWord(true);
		securityQueueTxtArea.setEditable(false);
		securityQueueTxtArea.setSize(100, 100);

		securityQueue.add(securityQueueTxtArea, BorderLayout.CENTER);

		// create scroll bar and add it to the security panel, set to always on
		securityQueueScroll = new JScrollPane();
		securityQueue.add(securityQueueScroll);
		securityQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		securityQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		securityQueueScroll.setViewportView(securityQueueTxtArea);

		///////////////////////////
		// PLANE BOARDING
		///////////////////////////

		// set up passengers in boarding queue JPanel
		boardingQueue = new JPanel();
		boardingQueue.setBorder(bevelBoarder);
		secBoardPanel.add(boardingQueue);
		boardingQueue.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		boardingQueueLabel = new JLabel("Passengers in Boarding Queue");
		boardingQueueLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		boardingQueue.add(boardingQueueLabel, BorderLayout.NORTH);

		// create text area - will display what customers are boarding
		boardingQueueTxtArea = new JTextArea(15, 20);
		boardingQueueTxtArea.setLineWrap(true);
		boardingQueueTxtArea.setWrapStyleWord(true);
		boardingQueueTxtArea.setEditable(false);
		boardingQueueTxtArea.setSize(100, 100);

		// create scroll bar and add it to the boarding panel, set to always on
		boardingQueue.add(boardingQueueTxtArea, BorderLayout.CENTER);
		boardingQueueScroll = new JScrollPane();
		boardingQueue.add(boardingQueueScroll);
		boardingQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		boardingQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		boardingQueueScroll.setViewportView(boardingQueueTxtArea);

		///////////////////////////
		// BOARDING TIMER
		///////////////////////////

		timerPanel = new JPanel();
		timerPanel.setLayout(new BorderLayout());
		timerPanel.setBorder(bevelBoarder);
		timeArea = new JTextArea(50, 33);
		timeArea.setForeground(Color.RED);
		// secBoardPanel.add(boardingQueue);

		timerLabel = new JLabel("Time", JLabel.CENTER);

		timerPanel.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		timerPanel.add(timerLabel, BorderLayout.NORTH);
		timerPanel.add(timeArea, BorderLayout.CENTER);

		countdownPanel = new JPanel();
		timerPanel.add(countdownPanel, BorderLayout.SOUTH);

		countdownLabel = new JLabel("Set Boarding Timer");
		countdownPanel.add(countdownLabel);

		timerSlider = new JSlider();
		timerSlider.setToolTipText("Slide the bar left or right to set the countdown timer");
		timerSlider.setMinorTickSpacing(10);
		timerSlider.setMajorTickSpacing(20);
		timerSlider.setSnapToTicks(true);
		timerSlider.setValue(time);
		timerSlider.setMinimum(110);
		timerSlider.setMaximum(170);
		timerSlider.setPaintTicks(true);
		timerSlider.setPaintLabels(true);
		countdownPanel.add(timerSlider);
		countdownPanel.setPreferredSize(new Dimension(20, 70));

		secBoardPanel.add(timerPanel);

	}

	/**
	 * Sets up the south panel to hold specific information about each flight such
	 * as total flight capacity, total hold volume capacity and total weight volume
	 * capacity. These details are updated as each passenger and their associated
	 * baggage board their respective flight.
	 * 
	 * Contains JPanel, JLabel, JTextArea and JProgressBar and adds them to the
	 * bottom part of the JFrame
	 */

	private void setupSouthPanel() {

		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 3, 0, 0));
		southPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, cyan));
		southPanel.setPreferredSize(new Dimension(180, 220));

		//////////////////////////// 9
		// setup first flight panel
		////////////////////////////

		emiratesDisplay = new JPanel();
		emiratesDisplay.setBorder(bevelBoarder);
		southPanel.add(emiratesDisplay);
		emiratesDisplay.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		emiratesLabel = new JLabel("Emirates Airlines");
		emiratesLabel.setIcon(emiratesIcon);
		emiratesLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		emiratesDisplay.add(emiratesLabel, BorderLayout.NORTH);

		eaProgressBarPanel = new JPanel();
		eaProgressBarPanel.setLayout(new GridLayout(0, 1, 0, 0));
		emiratesDisplay.add(eaProgressBarPanel, BorderLayout.CENTER);
		eaProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

		// create panels for JProgessBars for customers
		eaPassengerProgresBarPanel = new JPanel();
		eaPassengerProgresBarPanel.setBackground(Color.WHITE);
		eaPassengerProgresBarPanel.setLayout(new BorderLayout(0, 0));
		eaPassengerLabel = new JLabel("Flight Passenger Capacity");
		eaPassengerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		eaPassengerProgresBarPanel.add(eaPassengerLabel, BorderLayout.NORTH);
		eaPassenger = new JProgressBar(SwingConstants.HORIZONTAL);
		eaPassenger.setValue(0);
		eaPassenger.setMaximum(150);
		eaPassenger.setStringPainted(true);

		//

		eaProgressBarPanel.add(eaPassengerProgresBarPanel);
		eaPassengerProgresBarPanel.add(eaPassenger, BorderLayout.CENTER);

		// create panels for JProgessBars
		eaVolumeProgresBarPanel = new JPanel();
		eaVolumeProgresBarPanel.setBackground(Color.WHITE);
		eaVolumeProgresBarPanel.setLayout(new BorderLayout(0, 0));
		eaVolumeLabel = new JLabel("Baggage Hold Volume Capacity");
		eaVolumeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		eaVolumeProgresBarPanel.add(eaVolumeLabel, BorderLayout.NORTH);
		eaVolume = new JProgressBar(SwingConstants.HORIZONTAL);
		eaVolume.setValue(0);
		eaVolume.setStringPainted(true);

		eaProgressBarPanel.add(eaVolumeProgresBarPanel, BorderLayout.CENTER);
		eaVolumeProgresBarPanel.add(eaVolume, BorderLayout.CENTER);

		// create panels for JProgessBars
		eaWeightProgresBarPanel = new JPanel();
		eaWeightProgresBarPanel.setBackground(Color.WHITE);
		eaWeightProgresBarPanel.setLayout(new BorderLayout(0, 0));
		eaWeightLabel = new JLabel("Baggage Hold Weight Capacity");
		eaWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		eaWeightProgresBarPanel.add(eaWeightLabel, BorderLayout.NORTH);
		eaWeight = new JProgressBar(SwingConstants.HORIZONTAL);
		eaWeight.setValue(0);
		eaWeight.setStringPainted(true);

		//

		eaProgressBarPanel.add(eaWeightProgresBarPanel, BorderLayout.SOUTH);
		eaWeightProgresBarPanel.add(eaWeight, BorderLayout.CENTER);

		eaFeesArea = new JPanel();
		eaFeesArea.setLayout(new BorderLayout());

		eaFeeAreaLabel = new JLabel("Total Excess Baggage Fees");
		eaFeesLabel = new JLabel("No Collected Fees");
		eaFeesLabel.setOpaque(true);
		eaFeesLabel.setBackground(Color.WHITE);

		eaFeesArea.add(eaFeeAreaLabel, BorderLayout.NORTH);
		eaFeesArea.add(eaFeesLabel, BorderLayout.CENTER);

		eaFeesArea.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.WHITE));
		emiratesDisplay.add(eaFeesArea, BorderLayout.SOUTH);

		/////////////////////////////
		// setup second flight panel
		/////////////////////////////

		baDisplay = new JPanel();
		baDisplay.setBorder(bevelBoarder);
		southPanel.add(baDisplay);
		baDisplay.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		baLabel = new JLabel("British Airways");
		baLabel.setIcon(baIcon);
		baLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baDisplay.add(baLabel, BorderLayout.NORTH);

		baProgressBarPanel = new JPanel();
		baProgressBarPanel.setLayout(new GridLayout(0, 1, 0, 0));
		baDisplay.add(baProgressBarPanel, BorderLayout.CENTER);
		baProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

		// create panels for JProgessBars for customers
		baPassengerProgresBarPanel = new JPanel();
		baPassengerProgresBarPanel.setBackground(Color.WHITE);
		baPassengerProgresBarPanel.setLayout(new BorderLayout(0, 0));
		baPassengerLabel = new JLabel("Flight Passenger Capacity");
		baPassengerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baPassengerProgresBarPanel.add(baPassengerLabel, BorderLayout.NORTH);
		baPassenger = new JProgressBar(SwingConstants.HORIZONTAL);
		baPassenger.setValue(0);
		baPassenger.setMaximum(150);
		baPassenger.setStringPainted(true);

		//

		baProgressBarPanel.add(baPassengerProgresBarPanel);
		baPassengerProgresBarPanel.add(baPassenger, BorderLayout.CENTER);

		// create panels for JProgessBar for volume
		baVolumeProgresBarPanel = new JPanel();
		baVolumeProgresBarPanel.setBackground(Color.WHITE);
		baVolumeProgresBarPanel.setLayout(new BorderLayout(0, 0));
		baVolumeLabel = new JLabel("Baggage Hold Volume Capacity");
		baVolumeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baVolumeProgresBarPanel.add(baVolumeLabel, BorderLayout.NORTH);
		baVolume = new JProgressBar(SwingConstants.HORIZONTAL);
		baVolume.setValue(0);
		baVolume.setStringPainted(true);

		baProgressBarPanel.add(baVolumeProgresBarPanel);
		baVolumeProgresBarPanel.add(baVolume, BorderLayout.CENTER);

		// create panels for JProgessBars for weight
		baWeightProgresBarPanel = new JPanel();
		baWeightProgresBarPanel.setBackground(Color.WHITE);
		baWeightProgresBarPanel.setLayout(new BorderLayout(0, 0));
		baWeightLabel = new JLabel("Baggage Hold Weight Capacity");
		baWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baWeightProgresBarPanel.add(baWeightLabel, BorderLayout.NORTH);
		baWeight = new JProgressBar(SwingConstants.HORIZONTAL);
		baWeight.setValue(0);
		baWeight.setStringPainted(true);

		baProgressBarPanel.add(baWeightProgresBarPanel);
		baWeightProgresBarPanel.add(baWeight, BorderLayout.CENTER);

		baFeesArea = new JPanel();
		baFeesArea.setLayout(new BorderLayout());

		baFeeAreaLabel = new JLabel("Total Excess Baggage Fees");
		baFeesLabel = new JLabel("No Collected Fees");
		baFeesLabel.setOpaque(true);
		baFeesLabel.setBackground(Color.WHITE);

		baFeesArea.add(baFeeAreaLabel, BorderLayout.NORTH);
		baFeesArea.add(baFeesLabel, BorderLayout.CENTER);

		baFeesArea.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.WHITE));
		baDisplay.add(baFeesArea, BorderLayout.SOUTH);

		////////////////////////////
		// setup third flight panel
		////////////////////////////

		qatarDisplay = new JPanel();
		southPanel.add(qatarDisplay);
		qatarDisplay.setLayout(new BorderLayout());
		qatarDisplay.setBorder(bevelBoarder);

		// creates a label for the Panel - adds it above using border layout and sets
		// the font
		qatarLabel = new JLabel("Qatar Airways");
		qatarLabel.setIcon(qatarIcon);
		qatarLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qatarDisplay.add(qatarLabel, BorderLayout.NORTH);

		qaProgressBarPanel = new JPanel();
		qaProgressBarPanel.setLayout(new GridLayout(0, 1, 0, 0));
		qatarDisplay.add(qaProgressBarPanel, BorderLayout.CENTER);
		qaProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

		// create panels for JProgessBars for customers
		qaPassengerProgresBarPanel = new JPanel();
		qaPassengerProgresBarPanel.setBackground(Color.WHITE);
		qaPassengerProgresBarPanel.setLayout(new BorderLayout(0, 0));
		qaPassengerLabel = new JLabel("Flight Passenger Capacity");
		qaPassengerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qaPassengerProgresBarPanel.add(qaPassengerLabel, BorderLayout.NORTH);
		qaPassenger = new JProgressBar(SwingConstants.HORIZONTAL);
		qaPassenger.setValue(0);
		qaPassenger.setMaximum(550);
		qaPassenger.setStringPainted(true);

		//

		qaProgressBarPanel.add(qaPassengerProgresBarPanel);
		qaPassengerProgresBarPanel.add(qaPassenger, BorderLayout.CENTER);

		// create panels for JProgessBars
		qaVolumeProgresBarPanel = new JPanel();
		qaVolumeProgresBarPanel.setBackground(Color.WHITE);
		qaVolumeProgresBarPanel.setLayout(new BorderLayout(0, 0));
		qaVolumeLabel = new JLabel("Hold Baggage Volume Capacity");
		qaVolumeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qaVolumeProgresBarPanel.add(qaVolumeLabel, BorderLayout.NORTH);
		qaVolume = new JProgressBar(SwingConstants.HORIZONTAL);
		qaVolume.setValue(0);
		qaVolume.setStringPainted(true);

		qaProgressBarPanel.add(qaVolumeProgresBarPanel, BorderLayout.CENTER);
		qaVolumeProgresBarPanel.add(qaVolume, BorderLayout.CENTER);

		// create panels for JProgessBars
		qaWeightProgresBarPanel = new JPanel();
		qaWeightProgresBarPanel.setBackground(Color.WHITE);
		qaWeightProgresBarPanel.setLayout(new BorderLayout(0, 0));
		qaWeightLabel = new JLabel("Hold Baggage Weight Capacity");
		qaWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qaWeightProgresBarPanel.add(qaWeightLabel, BorderLayout.NORTH);
		qaWeight = new JProgressBar(SwingConstants.HORIZONTAL);
		qaWeight.setValue(0);
		qaWeight.setStringPainted(true);

		//

		qaProgressBarPanel.add(qaWeightProgresBarPanel, BorderLayout.SOUTH);
		qaWeightProgresBarPanel.add(qaWeight, BorderLayout.CENTER);

		qaFeesArea = new JPanel();
		qaFeesArea.setLayout(new BorderLayout());

		qaFeeAreaLabel = new JLabel("Total Excess Baggage Fees");
		qaFeesLabel = new JLabel("No Collected Fees");
		qaFeesLabel.setOpaque(true);
		qaFeesLabel.setBackground(Color.WHITE);

		qaFeesArea.add(qaFeeAreaLabel, BorderLayout.NORTH);
		qaFeesArea.add(qaFeesLabel, BorderLayout.CENTER);

		qaFeesArea.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.WHITE));
		qatarDisplay.add(qaFeesArea, BorderLayout.SOUTH);

	}

	/**
	 * This method when called - after a passenger checks-in their baggage at the
	 * check-in desk and boards the flight after joining the boarding queue -
	 * updates the progress bar for the volume/hold capacity of the relevant flight
	 * 
	 * @param carrier British Airways/Qatar Airways/Emirates Airlines
	 */
	// when called will update the progress bars of each case.
	public synchronized void updateFlightVolumes(String carrier) {
		String carrier2 = carrier.substring(0, 2);

		switch (carrier2) {

		case "BA":
			int bavolume = (int) passengersinqueue.BAtotalVolumeCapacity();
			baVolume.setValue(bavolume);
			int bacapacity = (int) passengersinqueue.BAtotalWeightCapacity();
			baWeight.setValue(bacapacity);
			int bapassenger = (int) passengersinqueue.getQ5().size();
			baPassenger.setValue(bapassenger);
			String baFees = "\t\t" + passengersinqueue.BAtotalExcessFeesCollected() + " GBP";
			baFeesLabel.setText(baFees);
			break;

		case "QA":
			int qavolume = (int) passengersinqueue.QAtotalVolumeCapacity();
			qaVolume.setValue(qavolume);
			int qacapacity = (int) passengersinqueue.QAtotalWeightCapacity();
			qaWeight.setValue(qacapacity);
			int qapassenger = (int) passengersinqueue.getQ4().size();
			qaPassenger.setValue(qapassenger);
			String qaFees = "\t\t" + passengersinqueue.QAtotalExcessFeesCollected() + " GBP";
			qaFeesLabel.setText(qaFees);
			break;

		case "EA":
			int eavolume = (int) passengersinqueue.EAtotalVolumeCapacity();
			eaVolume.setValue(eavolume);
			int eacapacity = (int) passengersinqueue.EAtotalWeightCapacity();
			eaWeight.setValue(eacapacity);
			int eapassenger = (int) passengersinqueue.getQ6().size();
			eaPassenger.setValue(eapassenger);
			String eaFees = "\t\t" + passengersinqueue.EAtotalExcessFeesCollected() + " GBP";
			eaFeesLabel.setText(eaFees);
			break;

		}
		;
	}

	/////////////////////////////////////////////
	// MVC pattern - allows listeners to be added
	////////////////////////////////////////////

	/**
	 * In the event that the processButton is pressed, the Action Listener enables
	 * this event to be processed
	 * 
	 * 
	 * @param al the event to be processed
	 */
	public void addGUIMainListener(ActionListener al) {

		// GUIController action listers are added to processButton
		processButton.addActionListener(al);

	}

	/**
	 * In the event that the timer slider is set, the Change Listener enables to set
	 * the time for the boarding simulation
	 * 
	 * 
	 * @param cl set time for the boarding simulation programme
	 */
	public void addGUISliderListener(ChangeListener cl) {

		// GUIController action listers are added to processButton
		timerSlider.addChangeListener(cl);

	}

	/**
	 * Sets up the action to be performed once the processButton is pressed - start
	 * the swingworker thread and programme
	 * 
	 *
	 */

	// method to start the programme
	public void disableProcessButton() {

		// pressing the button will start the swing worker and the programme
		processButton.setEnabled(false);
		timerSlider.setEnabled(false);
		startDisplay();

	}

	// method using swing worker to execute the threads in the background and print
	// the indicated info on the GUI
	public void startDisplay() {

		// different swing workers running different threads
		SwingWorker<Void, LinkedList<Passenger>> EconomyWorker = new SwingWorker<Void, LinkedList<Passenger>>() {

			@Override
			protected Void doInBackground() {
				while (!isCancelled()) {
					try {
						LinkedList<Passenger> p = passengersinqueue.economyQueueputi();
						synchronized (p) {
							publish(p);
						}
					} catch (InterruptedException | ConcurrentModificationException e) {
					}

				}
				return null;
			}

			@Override
			protected synchronized void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> geco1 = chunks.get(chunks.size() - 1);
				synchronized(geco1) {
				for (int i = 0; i < geco1.size(); i++) {
					for (Passenger p : geco1) {

						try {
							checkinQueueTxtArea.setText(new Date() + "\n" + "\n" + p.getpClass() + " class passenger "
									+ p.getFullName() + ", has joined the check-in queue.");
						} catch (ConcurrentModificationException u) {
						}

					}
				}

				}

			}
		};

		SwingWorker<Void, LinkedList<Passenger>> FirstWorker = new SwingWorker<Void, LinkedList<Passenger>>() {

			@Override
			protected Void doInBackground() throws InterruptedException {
				while (!isCancelled()) {
					try {
						LinkedList<Passenger> p = passengersinqueue.firstbusinessQueueputi();
						synchronized (p) {
							publish(p);
						}
					} catch (InterruptedException e) {
					}

				}
				return null;
			}

			@Override
			protected synchronized void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> geco1 = chunks.get(chunks.size() - 1);
				synchronized (geco1) {
				for (int i = 0; i < geco1.size(); i++) {
					for (Passenger p : geco1) {
						try {
							firstcheckinQueueTxtArea.setText(new Date() + "\n" + "\n" + p.getpClass()
									+ " class passenger " + p.getFullName() + ", has joined the check-in queue.");

						} catch (ConcurrentModificationException u) {
						}
					}
				}
				}
			}
		};

		SwingWorker<Baggage, Baggage> FirstCheckinWorker = new SwingWorker<Baggage, Baggage>() {

			@Override
			protected Baggage doInBackground() throws Exception {
				Thread.sleep(2000);

				while (!isCancelled()) {

					Baggage p = passengersinqueue.BusinessFirstPicker();
					
					publish(p);
				
				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<Baggage> chunks) {
				Baggage gecocheckin = chunks.get(chunks.size() - 1);

				try {

					updateFlightVolumes(gecocheckin.getPassenger().getflightCode());

					desk1TxtArea.setText("" + new Date() + "\n" + "\n" + " Full Name: "
							+ gecocheckin.getPassenger().getFullName() + "\n" + " Travel Class: "
							+ gecocheckin.getPassenger().getpClass() + "\n" + " Booking number: "
							+ gecocheckin.getPassenger().getBookingReferenceNum() + "\n" + " Flight: "
							+ gecocheckin.getPassenger().getflightCode() + "\n" + " Baggage Dimesions:   "
							+ gecocheckin.getBreadth() + " X " + gecocheckin.getHeight() + " X "
							+ gecocheckin.getWidth() + "\n" + " Baggage Weight: " + gecocheckin.getWeight() + " Kg"
							+ "\n" + " Baggage volume exceeds by: " + gecocheckin.excessBaggageVolume()
							+ " Cubic Inches" + "\n" + " Baggage weight exceeds by: "
							+ gecocheckin.excessBaggageWeight() + " Kg" + "\n" + " Excess Volume fees: " + "\u00a3"
							+ gecocheckin.excessVolumeFee() + "\n" + " Excess Weight fees: " + "\u00a3"
							+ gecocheckin.excessWeightFee() + "\n" + " Total Excess fees due:   " + "\u00a3"
							+ gecocheckin.excessBaggageFee());

				} catch (NullPointerException | ConcurrentModificationException u) {
				}
			}
		};

		SwingWorker<Baggage, Baggage> EconomyCheckinWorker1 = new SwingWorker<Baggage, Baggage>() {

			@Override
			protected Baggage doInBackground() throws Exception {
				Thread.sleep(2000);
				while (!isCancelled()) {

					Baggage p = passengersinqueue.EconomyPicker();

					publish(p);

				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<Baggage> chunks) {
				Baggage gecocheckin = chunks.get(chunks.size() - 1);
				try {

					updateFlightVolumes(gecocheckin.getPassenger().getflightCode());

					ecoDesk1TxtArea.setText("" + new Date() + "\n" + "\n" + " Full Name: "
							+ gecocheckin.getPassenger().getFullName() + "\n" + " Travel Class: "
							+ gecocheckin.getPassenger().getpClass() + "\n" + " Booking number: "
							+ gecocheckin.getPassenger().getBookingReferenceNum() + "\n" + " Flight: "
							+ gecocheckin.getPassenger().getflightCode() + "\n" + " Baggage Dimesions:   "
							+ gecocheckin.getBreadth() + " X " + gecocheckin.getHeight() + " X "
							+ gecocheckin.getWidth() + "\n" + " Baggage Weight: " + gecocheckin.getWeight() + " Kg"
							+ "\n" + " Baggage volume exceeds by: " + gecocheckin.excessBaggageVolume()
							+ " Cubic Inches" + "\n" + " Baggage weight exceeds by: "
							+ gecocheckin.excessBaggageWeight() + " Kg" + "\n" + " Excess Volume fees: " + "\u00a3"
							+ gecocheckin.excessVolumeFee() + "\n" + " Excess Weight fees: " + "\u00a3"
							+ gecocheckin.excessWeightFee() + "\n" + " Total Excess fees due:   " + "\u00a3"
							+ gecocheckin.excessBaggageFee());

				} catch (NullPointerException | ConcurrentModificationException u) {

				}
			}
		};

		SwingWorker<Baggage, Baggage> EconomyCheckinWorker2 = new SwingWorker<Baggage, Baggage>() {

			@Override
			protected Baggage doInBackground() throws Exception {
				while (!isCancelled()) {
					Thread.sleep(2000);
					Baggage p = passengersinqueue.EconomyPicker();

					publish(p);

				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<Baggage> chunks) {
				Baggage gecocheckin = chunks.get(chunks.size() - 1);
				try {

					updateFlightVolumes(gecocheckin.getPassenger().getflightCode());

					ecoDesk2TxtArea.setText("" + new Date() + "\n" + "\n" + " Full Name: "
							+ gecocheckin.getPassenger().getFullName() + "\n" + " Travel Class: "
							+ gecocheckin.getPassenger().getpClass() + "\n" + " Booking number: "
							+ gecocheckin.getPassenger().getBookingReferenceNum() + "\n" + " Flight: "
							+ gecocheckin.getPassenger().getflightCode() + "\n" + " Baggage Dimesions:   "
							+ gecocheckin.getBreadth() + " X " + gecocheckin.getHeight() + " X "
							+ gecocheckin.getWidth() + "\n" + " Baggage Weight: " + gecocheckin.getWeight() + " Kg"
							+ "\n" + " Baggage volume exceeds by: " + gecocheckin.excessBaggageVolume()
							+ " Cubic Inches" + "\n" + " Baggage weight exceeds by: "
							+ gecocheckin.excessBaggageWeight() + " Kg" + "\n" + " Excess Volume fees: " + "\u00a3"
							+ gecocheckin.excessVolumeFee() + "\n" + " Excess Weight fees: " + "\u00a3"
							+ gecocheckin.excessWeightFee() + "\n" + " Total Excess fees due:   " + "\u00a3"
							+ gecocheckin.excessBaggageFee());

				} catch (NullPointerException | ConcurrentModificationException u) {

				}
			}
		};

		SwingWorker<Baggage, Baggage> EconomyCheckinWorker3 = new SwingWorker<Baggage, Baggage>() {

			@Override
			protected Baggage doInBackground() throws Exception {
				while (!isCancelled()) {
					Thread.sleep(2000);

					Baggage p = passengersinqueue.EconomyPicker();

					publish(p);

				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<Baggage> chunks) {
				Baggage gecocheckin = chunks.get(chunks.size() - 1);
				try {

					updateFlightVolumes(gecocheckin.getPassenger().getflightCode());

					ecoDesk3TxtArea.setText("" + new Date() + "\n" + "\n" + " Full Name: "
							+ gecocheckin.getPassenger().getFullName() + "\n" + " Travel Class: "
							+ gecocheckin.getPassenger().getpClass() + "\n" + " Booking number: "
							+ gecocheckin.getPassenger().getBookingReferenceNum() + "\n" + " Flight: "
							+ gecocheckin.getPassenger().getflightCode() + "\n" + " Baggage Dimesions:   "
							+ gecocheckin.getBreadth() + " X " + gecocheckin.getHeight() + " X "
							+ gecocheckin.getWidth() + "\n" + " Baggage Weight: " + gecocheckin.getWeight() + " Kg"
							+ "\n" + " Baggage volume exceeds by: " + gecocheckin.excessBaggageVolume()
							+ " Cubic Inches" + "\n" + " Baggage weight exceeds by: "
							+ gecocheckin.excessBaggageWeight() + " Kg" + "\n" + " Excess Volume fees: " + "\u00a3"
							+ gecocheckin.excessVolumeFee() + "\n" + " Excess Weight fees: " + "\u00a3"
							+ gecocheckin.excessWeightFee() + "\n" + " Total Excess fees due:   " + "\u00a3"
							+ gecocheckin.excessBaggageFee());

				} catch (NullPointerException | ConcurrentModificationException u) {

				}
			}
		};

		SwingWorker<Passenger, LinkedList<Passenger>> SecurityWorker = new SwingWorker<Passenger, LinkedList<Passenger>>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				Thread.sleep(3000);
				while (!isCancelled()) {

					LinkedList<Passenger> ls = passengersinqueue.securityPicker();
					synchronized (ls) {
						publish(ls);
					}
				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> gsecurity = chunks.get(chunks.size() - 1);
				// add try catch
				synchronized (gsecurity) {
				for (int i = 0; i < gsecurity.size(); i++) {
					
					for (Passenger p : gsecurity) {
						
						try {
							securityQueueTxtArea.setText(new Date() + "\n" + "\n" + p.getFullName()
									+ " is now at security" + System.lineSeparator() + "\n");
						} catch (ConcurrentModificationException u) {
						}
					}

				}
				}
			}
		};

		SwingWorker<Passenger, LinkedList<Passenger>> BoardingWorker = new SwingWorker<Passenger, LinkedList<Passenger>>() {

			@Override
			protected Passenger doInBackground() throws Exception {

				Thread.sleep(4000);

				while (!isCancelled()) {

					LinkedList<Passenger> ls = passengersinqueue.boardingPicker();
					synchronized (ls) {
						publish(ls);
					}
				}

				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected synchronized void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> gboarding = chunks.get(chunks.size() - 1);
                
				synchronized (gboarding) {
				for (Passenger p : gboarding) {
					
					try {

						boardingQueueTxtArea.append("" + new Date() + "\n" + "" + p.getFullName() + " is now boarding "
								+ p.getflightCode() + " flight." + "\n" + "\n");

					} catch (ConcurrentModificationException u) {
					}
				}
                
                }
			}
		};

		SwingWorker<Passenger, Date> TimeKeeper = new SwingWorker<Passenger, Date>() {

			@Override
			protected Passenger doInBackground() throws Exception {

				while (!isCancelled()) {
					Date currentDate = new Date();
					publish(currentDate);

				}
				return null;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<Date> chunks) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(myDate);
				cal.add(Calendar.SECOND, timeForClosing);
				Date dateAfter30Seconds = cal.getTime();
				Date currentDate = new Date();

				if (currentDate.after(dateAfter30Seconds)) {
					ecoDesk1TxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					ecoDesk1TxtArea.setForeground(Color.RED);
					desk1TxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					desk1TxtArea.setForeground(Color.RED);
					ecoDesk2TxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					ecoDesk2TxtArea.setForeground(Color.RED);
					ecoDesk3TxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					ecoDesk3TxtArea.setForeground(Color.RED);
					securityQueueTxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					securityQueueTxtArea.setForeground(Color.RED);
					firstcheckinQueueTxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					firstcheckinQueueTxtArea.setForeground(Color.RED);
					checkinQueueTxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					checkinQueueTxtArea.setForeground(Color.RED);
					boardingQueueTxtArea.setText(new Date() + "\n" + "\n" + "CLOSED");
					boardingQueueTxtArea.setForeground(Color.RED);
					timeArea.setText("ALL GATES HAVE BEEN CLOSED" + "\n" + "\n" + "Depature time: " + "\n"
							+ dateAfter30Seconds + "\n" + "\n" + "Current time: " + "\n" + new Date());
					timeArea.setForeground(Color.BLUE);

					EconomyWorker.cancel(true);
					FirstWorker.cancel(true);
					FirstCheckinWorker.cancel(true);
					EconomyCheckinWorker1.cancel(true);
					EconomyCheckinWorker2.cancel(true);
					EconomyCheckinWorker3.cancel(true);
					SecurityWorker.cancel(true);
					BoardingWorker.cancel(true);
					this.cancel(true);

				} else {

					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(currentDate);
					long milis = cal2.getTime().getTime();

					long milis2 = dateAfter30Seconds.getTime();

					long diifLong = milis2 - milis;

					timeArea.setText("TIME OF DEPATURE:" + "\n\t" + dateAfter30Seconds + "\n" + "CURRENT TIME:  "
							+ "\n\t" + currentDate + "\n" + "\n" + "You shall be ALERTED when time left= 100 seconds");

					if (diifLong < 100000) {
						try {
							timeArea.setText("Warning!!!!!!!" + "\n\n" + "TIME BEFORE GATES CLOSE: " + "\n\t"
									+ Long.toString(diifLong).substring(0, 2) + "   seconds" + "\n"
									+ "TIME OF DEPATURE: " + "\n\t" + dateAfter30Seconds + "\n" + "CURRENT TIME : "
									+ "\n\t" + currentDate);
						} catch (StringIndexOutOfBoundsException s) {
						}
					}
					if (diifLong < 12000) {
						timeArea.setText("GATES CLOSING IN 10 SECONDS" + "\n" + "Depature time: " + dateAfter30Seconds
								+ "\n" + "Current time: " + new Date());
					}

				}

			}
		};

		// starts all the swing workers runnning
		EconomyWorker.execute();
		FirstWorker.execute();
		FirstCheckinWorker.execute();
		EconomyCheckinWorker1.execute();
		EconomyCheckinWorker2.execute();
		EconomyCheckinWorker3.execute();
		SecurityWorker.execute();
		BoardingWorker.execute();
		TimeKeeper.execute();

	}

	public int getTimeForClosing() {
		return timeForClosing;
	}

	public void setTimeForClosing(int timeForClosing) {
		this.timeForClosing = timeForClosing;
	}

	// this update method needs to be fixed at this moment it is non-functional
	@Override
	public void update(Observable o, Object arg) {

		startDisplay();

	}

}