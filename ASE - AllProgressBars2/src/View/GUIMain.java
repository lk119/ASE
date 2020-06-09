package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.*;

import Model.Airport;
import Model.BoardingRunnable;
//import Model.Consumer;
import Model.EcoProducer;
import Model.EconomyCheckIn1Runnable;
import Model.EconomyCheckIn2Runnable;
import Model.EconomyCheckIn3Runnable;
import Model.FirstProducer;
import Model.FirstandBusinessCheckInRunnable;

import Model.Passenger;
import Model.PassengersInQueue;
import Model.SecurityRunnable;

public class GUIMain extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private PassengersInQueue passengersinqueue;
	private Passenger passenger;
	private EconomyCheckIn1Runnable ec1;
	private EconomyCheckIn2Runnable ec2;
	private EconomyCheckIn3Runnable ec3;
	private FirstandBusinessCheckInRunnable fc;
	private SecurityRunnable s;
	private BoardingRunnable b;
	private EcoProducer ecoprod;
	private FirstProducer firstprod;

	private String reportC;
	private String reportS;
	private String reportB;
	private String reportFC;
	private String reportEC1;
	private String reportEC2;
	private String reportEC3;

	// private JTextField ;
	// private JLabel ;
	// private ImageIcon ;
	private JPanel northPanel, centerPanel, southPanel, FirstandBusinessDesk, economyDesk1, economyDesk2, economyDesk3,processButtonPanel,
			buttonPanel1, buttonPanel2, buttonPanel3, buttonPanel4, checkinQueue, firstcheckinQueue, securityQueue, boardingQueue, checkInPanel, 
			checkinLabelPanel, secBoardPanel, queueGroupPanel, emiratesDisplay,baDisplay,qatarDisplay, baProgressBarPanel,
			baVolumeProgresBarPanel, baWeightProgresBarPanel, qaProgressBarPanel, qaVolumeProgresBarPanel, qaWeightProgresBarPanel, 
			eaProgressBarPanel, eaVolumeProgresBarPanel, eaWeightProgresBarPanel, baPassengerProgresBarPanel, qaPassengerProgresBarPanel, eaPassengerProgresBarPanel;
	private JButton openBtn1, closeBtn1, openBtn2, closeBtn2, openBtn3, closeBtn3, openBtn4, closeBtn4, processButton;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuReport, menuStart;
	private JTextArea desk1TxtArea, ecoDesk1TxtArea, ecoDesk2TxtArea, ecoDesk3TxtArea, checkinQueueTxtArea,
			firstcheckinQueueTxtArea, securityQueueTxtArea, boardingQueueTxtArea,emiratesTxtArea, baTxtArea, qatarTxtArea;
	private ImageIcon plane;
	private JScrollPane checkinQueueScroll, firstcheckinQueueScroll, securityQueueScroll, boardingQueueScroll;
	private JLabel desk1Lbl, ecoDesk1Label, ecoDesk2Label, ecoDesk3Label, checkinQueuelabel, firstcheckinQueuelabel,
			securityQueuelabel, boardingQueueLabel,fbGroupLabel, emiratesLabel, baLabel, qatarLabel, baVolumeLabel,
			baWeightLabel, qaVolumeLabel,qaWeightLabel, eaVolumeLabel,eaWeightLabel, baPassengerLabel, qaPassengerLabel, eaPassengerLabel ;

	//setup travel pigeon colour
	private Color cyan = new Color(0,206,209);
	//setup boarder for all boxes
	private BevelBorder bevelBoarder = new BevelBorder(BevelBorder.LOWERED, null, null, null, null);
	//icons for the different airlines
	private ImageIcon qatarIcon = new ImageIcon(GUIMain.class.getResource("/view/qa.png"));
	private ImageIcon baIcon = new ImageIcon(GUIMain.class.getResource("/view/ba.png")); 
	private ImageIcon emiratesIcon = new ImageIcon(GUIMain.class.getResource("/view/em.png"));
	

	private JProgressBar baWeight, baVolume, qaWeight, qaVolume, eaWeight, eaVolume, baPassenger, eaPassenger, qaPassenger;
	
	/**
	 * Create the frame.
	 * 
	 * @param pqcheckinQueue,
	 * @param p
	 * @param ps
	 */

	public GUIMain(PassengersInQueue pq, Passenger p, EconomyCheckIn1Runnable model2, EconomyCheckIn2Runnable model3,
			EconomyCheckIn3Runnable model4, FirstandBusinessCheckInRunnable model5, SecurityRunnable model6,
			BoardingRunnable model7, EcoProducer model8, FirstProducer model9) {

		passengersinqueue = pq;
		passenger = p;
		pq.addObserver(this);
		ec1 = model2;
		ec2 = model3;
		ec3 = model4;
		fc = model5;
		this.s = model6;
		this.b = model7;
		ecoprod = model8;
		firstprod = model9;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1000, 1000);

		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createLineBorder(cyan));
		setContentPane(contentPane);

		
//code for menu bar - now redundant		
//		menu = new JMenu("Menu");
//
//		menuBar = new JMenuBar();
//		menuBar.setForeground(cyan);
//		// menuBar.setMnemonic(KeyEvent.VK_A);
//		menuBar.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
//
//		// report item for menu
//		menuReport = new JMenuItem("Flight Capacity Report", KeyEvent.VK_T);
//		menuReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
//		menuReport.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
//		menu.add(menuReport);
//
//		// add menu action listener for report
//		menuReport.addActionListener(new ActionListener() {
//
//			// action listener for this menu item
//			public void actionPerformed(ActionEvent e) {
//				GUIReport GUIReport = new GUIReport(pq);
//				GUIReport.setVisible(true);
//				dispose();
//			}
//		});
//
//		// report item for menu
//				menuStart = new JMenuItem("Start Check-In", KeyEvent.VK_T);
//				menuStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_, ActionEvent.ALT_MASK));
//				menuStart.getAccessibleContext().setAccessibleDescription("Starts the Check-In");
//				menu.add(menuStart);
//		
//		
//		menuBar.add(menu);

		
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

	public GUIMain(String string) {
		// TODO Auto-generated constructor stub
	}

	private void setupNorthPanel() {

		// create the basic structure of north panel for checkin of passengers 
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		//northPanel.setSize(10, 10);
		
		//northPanel.setBorder(BorderFactory.createLineBorder(cyan, 10));
		contentPane.add(northPanel, BorderLayout.NORTH);

		// create the button to initiate the programme
		processButtonPanel = new JPanel();
		processButtonPanel.setBackground(cyan);
		processButton = new JButton("Open Check-In");
		processButton.setPreferredSize(new Dimension(550,20));
		processButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		processButtonPanel.setBorder(BorderFactory.createMatteBorder(10,10,5,10,cyan));
		processButtonPanel.add(processButton);
		
		
		northPanel.add(processButtonPanel,BorderLayout.NORTH);

		queueGroupPanel= new JPanel();
		queueGroupPanel.setLayout(new GridLayout(0,2,0,0));
		
		northPanel.add(queueGroupPanel, BorderLayout.CENTER);
		
		northPanel.isPaintingTile();
		/////////////////////////
		// CHECK IN QUEUE ECONOMY
		/////////////////////////
		
		// set up passengers in check-in queue panel
		checkinQueue = new JPanel();
		checkinQueue.setBorder(bevelBoarder);
		checkinQueue.setBorder(BorderFactory.createMatteBorder(5,10,10,5,cyan));
		queueGroupPanel.add(checkinQueue, BorderLayout.CENTER);
		checkinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		checkinQueuelabel = new JLabel("Economy Class Passengers in Check-In Queue");
		checkinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		checkinQueue.add(checkinQueuelabel, BorderLayout.NORTH);

		// create text area
		checkinQueueTxtArea = new JTextArea(5,20);
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
		//CHECK IN FIRST & BUISNESS QUEUE
		///////////////////////////
		
		// set up passengers in check-in queue panel
		firstcheckinQueue = new JPanel();
		firstcheckinQueue.setBorder(bevelBoarder);
		firstcheckinQueue.setBorder(BorderFactory.createMatteBorder(5,5,10,10,cyan));
		queueGroupPanel.add(firstcheckinQueue, BorderLayout.CENTER);
		firstcheckinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		firstcheckinQueuelabel = new JLabel("First and Busincess Class Passengers in Check-In Queue");
		firstcheckinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		firstcheckinQueue.add(firstcheckinQueuelabel, BorderLayout.NORTH);

		// create text area
		firstcheckinQueueTxtArea = new JTextArea(5,20);
		firstcheckinQueueTxtArea.setLineWrap(true);
		firstcheckinQueueTxtArea.setWrapStyleWord(true);
		firstcheckinQueueTxtArea.setEditable(false);
		firstcheckinQueueTxtArea.setSize(100, 100);

		firstcheckinQueue.add(firstcheckinQueueTxtArea, BorderLayout.CENTER);

		// create scroll bar and add it to the first class and business panel, set to always on
		firstcheckinQueueScroll = new JScrollPane();
		firstcheckinQueue.add(firstcheckinQueueScroll);
		firstcheckinQueueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		firstcheckinQueueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		firstcheckinQueueScroll.setViewportView(firstcheckinQueueTxtArea);

		
		
	}

	private void setupCenterPanel() {

		//the main panel for the center section
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		/////////////////////////
		// Arrangement setup
		/////////////////////////
		
		
		//create & setup JPanel for checkin items to be added to
		checkInPanel = new JPanel();
		checkInPanel.setLayout(new GridLayout(0,4,0,0));
		checkInPanel.setBorder(bevelBoarder);
		
		//creates an outer JPanel to add all queue items to allow an overall label to be added 
		checkinLabelPanel = new JPanel();
		checkinLabelPanel.setBorder(bevelBoarder);
		checkinLabelPanel.setBorder(BorderFactory.createMatteBorder(5,10,5,10,cyan));
		fbGroupLabel = new JLabel("Check-In Desks");
		checkinLabelPanel.setLayout(new BorderLayout());
		checkinLabelPanel.add(fbGroupLabel, BorderLayout.NORTH);
		//adds the checkinPanel JPanel to this panel
		checkinLabelPanel.add(checkInPanel);
		
		//create & setup a JPanel that contains all security and boarding related items
		secBoardPanel = new JPanel();
		secBoardPanel.setLayout(new GridLayout(0,2,0,0));
		secBoardPanel.setBorder(bevelBoarder);
		secBoardPanel.setBorder(BorderFactory.createLineBorder(cyan, 10));
		
		// adds checkin, security and boarding elements to the central pane
		centerPanel.add(checkinLabelPanel);
		centerPanel.add(secBoardPanel);
				
		/////////////////////////
		//ECONOMY CHECK-IN 1 DESK
		/////////////////////////	
		
		//setup economy desk 1 JPanel
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

		ecoDesk1TxtArea.append(reportEC1);

		economyDesk1.add(ecoDesk1TxtArea);

//		buttonPanel2 = new JPanel();
//		openBtn2 = new JButton("Open");
//		closeBtn2 = new JButton("Close");
//		buttonPanel2.add(openBtn2);
//		buttonPanel2.add(closeBtn2);
//		EconomyDesk1.add(buttonPanel2, BorderLayout.SOUTH);


		/////////////////////////
		//ECONOMY CHECK-IN 2 DESK
		/////////////////////////	
		
		//setup economy desk 2 JPanel
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

		ecoDesk2TxtArea.append(reportEC2);

		economyDesk2.add(ecoDesk2TxtArea);

//		buttonPanel3 = new JPanel();
//		openBtn3 = new JButton("Open");
//		closeBtn3 = new JButton("Close");
//		buttonPanel3.add(openBtn3);
//		buttonPanel3.add(closeBtn3);
//		EconomyDesk2.add(buttonPanel3, BorderLayout.SOUTH);

		/////////////////////////
		//ECONOMY CHECK-IN 3 DESK
		/////////////////////////		
		
		//setup economy desk 3 JPanel
		economyDesk3 = new JPanel();
		economyDesk3.setBorder(bevelBoarder);
		checkInPanel.add(economyDesk3);
		economyDesk3.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
		ecoDesk3Label = new JLabel("Economy 3");
		ecoDesk3Label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		economyDesk3.add(ecoDesk3Label, BorderLayout.NORTH);

		// add text area to the desk, where passengers names will be printed
		ecoDesk3TxtArea = new JTextArea();
		ecoDesk3TxtArea.setSize(60, 60);
		ecoDesk3TxtArea.setEditable(false);

		ecoDesk3TxtArea.append(reportEC3);

		economyDesk3.add(ecoDesk3TxtArea);

//		buttonPanel4 = new JPanel();
//		openBtn4 = new JButton("Open");
//		closeBtn4 = new JButton("Close");
//		buttonPanel4.add(openBtn4);
//		buttonPanel4.add(closeBtn4);
//		EconomyDesk3.add(buttonPanel4, BorderLayout.SOUTH);

		
		/////////////////////////////////
		//FIRST & BUSINESS CHECK-IN DESK
		////////////////////////////////	
		
		//setup business and first class check-in JPanel
		FirstandBusinessDesk = new JPanel();
		FirstandBusinessDesk.setBorder(bevelBoarder);
		checkInPanel.add(FirstandBusinessDesk);
		FirstandBusinessDesk.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
		desk1Lbl = new JLabel("First & Business");
		desk1Lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		FirstandBusinessDesk.add(desk1Lbl, BorderLayout.NORTH);
		
		// add text area to the desk, where passengers names will be printed
		desk1TxtArea = new JTextArea();
		desk1TxtArea.setSize(60, 60);
		desk1TxtArea.setEditable(false);

		desk1TxtArea.append(reportFC);

		FirstandBusinessDesk.add(desk1TxtArea);

//		buttonPanel1 = new JPanel();
//		openBtn1 = new JButton("Open");
//		closeBtn1 = new JButton("Close");
//		buttonPanel1.add(openBtn1);
//		buttonPanel1.add(closeBtn1);
//		FirstandBusinessDesk.add(buttonPanel1, BorderLayout.SOUTH);
		
		///////////////////////////
		//SECURITY CHECK
		///////////////////////////
		
		// set up passengers in security queue panel
		securityQueue = new JPanel();
		securityQueue.setBorder(bevelBoarder);
		secBoardPanel.add(securityQueue);
		securityQueue.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
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
		//PLANE BOARDING 
		///////////////////////////

		// set up passengers in boarding queue JPanel
		boardingQueue = new JPanel();
		boardingQueue.setBorder(bevelBoarder);
		secBoardPanel.add(boardingQueue);
		boardingQueue.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
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


	}

	private void setupSouthPanel() {

		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 3, 0, 0));
		southPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10,cyan));
		southPanel.setPreferredSize(new Dimension(180,170));

		////////////////////////////
		// setup first flight panel
		////////////////////////////
		
		emiratesDisplay = new JPanel();
		emiratesDisplay.setBorder(bevelBoarder);
		southPanel.add(emiratesDisplay);
		emiratesDisplay.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
		emiratesLabel = new JLabel("Emirates Airlines");
		emiratesLabel.setIcon(emiratesIcon);
		emiratesLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		emiratesDisplay.add(emiratesLabel, BorderLayout.NORTH);

		emiratesTxtArea = new JTextArea(5, 10);
		emiratesTxtArea.setEditable(false);
		emiratesDisplay.add(emiratesTxtArea, BorderLayout.SOUTH);

		eaProgressBarPanel = new JPanel();
		eaProgressBarPanel.setLayout(new GridLayout(0,1,0,0));
		emiratesDisplay.add(eaProgressBarPanel, BorderLayout.SOUTH);
		eaProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.WHITE));

		
		// create panels for JProgessBars for customers
		eaPassengerProgresBarPanel = new JPanel();
		eaPassengerProgresBarPanel.setBackground(Color.WHITE);
		eaPassengerProgresBarPanel.setLayout(new BorderLayout(0,0));
		eaPassengerLabel = new JLabel("Flight Capacity");
		eaPassengerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		eaPassengerProgresBarPanel.add(eaPassengerLabel, BorderLayout.NORTH);
		eaPassenger = new JProgressBar(SwingConstants.HORIZONTAL); 
		eaPassenger.setValue(0); 
		eaPassenger.setMaximum(300);
		eaPassenger.setStringPainted(true);

		
		//

		eaProgressBarPanel.add(eaPassengerProgresBarPanel);
		eaPassengerProgresBarPanel.add(eaPassenger, BorderLayout.CENTER);
		
		// create panels for JProgessBars
		eaVolumeProgresBarPanel = new JPanel();
		eaVolumeProgresBarPanel.setBackground(Color.WHITE);
		eaVolumeProgresBarPanel.setLayout(new BorderLayout(0,0));
		eaVolumeLabel = new JLabel("Hold Volume Capacity");
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
		eaWeightProgresBarPanel.setLayout(new BorderLayout(0,0));
		eaWeightLabel = new JLabel("Hold Weight Capacity");
		eaWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		eaWeightProgresBarPanel.add(eaWeightLabel, BorderLayout.NORTH);
		eaWeight = new JProgressBar(SwingConstants.HORIZONTAL); 
		eaWeight.setValue(0); 
		eaWeight.setStringPainted(true);
		
		//

		eaProgressBarPanel.add(eaWeightProgresBarPanel, BorderLayout.SOUTH);
		eaWeightProgresBarPanel.add(eaWeight, BorderLayout.CENTER);
		
		/////////////////////////////
		// setup second flight panel
		/////////////////////////////
		
		baDisplay = new JPanel();
		baDisplay.setBorder(bevelBoarder);
		southPanel.add(baDisplay);
		baDisplay.setLayout(new BorderLayout(0, 0));

		// creates a label for the Panel - adds it above using border layout and sets the font
		baLabel = new JLabel("British Airways");
		baLabel.setIcon(baIcon);
		baLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baDisplay.add(baLabel, BorderLayout.NORTH);
		
		
		baProgressBarPanel = new JPanel();
		baProgressBarPanel.setLayout(new GridLayout(0,1,0,0));
		baDisplay.add(baProgressBarPanel, BorderLayout.SOUTH);
		baProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.WHITE));
		
		
		// create panels for JProgessBars for customers
		baPassengerProgresBarPanel = new JPanel();
		baPassengerProgresBarPanel.setBackground(Color.WHITE);
		baPassengerProgresBarPanel.setLayout(new BorderLayout(0,0));
		baPassengerLabel = new JLabel("Flight Capacity");
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
		baVolumeProgresBarPanel.setLayout(new BorderLayout(0,0));
		baVolumeLabel = new JLabel("Hold Volume Capacity");
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
		baWeightProgresBarPanel.setLayout(new BorderLayout(0,0));
		baWeightLabel = new JLabel("Hold Weight Capacity");
		baWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		baWeightProgresBarPanel.add(baWeightLabel, BorderLayout.NORTH);
		baWeight = new JProgressBar(SwingConstants.HORIZONTAL); 
		baWeight.setValue(0); 
		baWeight.setStringPainted(true);
		

		baProgressBarPanel.add(baWeightProgresBarPanel);
		baWeightProgresBarPanel.add(baWeight, BorderLayout.CENTER);
		
		
		////////////////////////////
		// setup third flight panel
		////////////////////////////
		
		qatarDisplay = new JPanel();
		southPanel.add(qatarDisplay);
		qatarDisplay.setLayout(new BorderLayout(0, 0));
		qatarDisplay.setBorder(bevelBoarder);

		// creates a label for the Panel - adds it above using border layout and sets the font
		qatarLabel = new JLabel("Qatar Airways");
		qatarLabel.setIcon(qatarIcon);
		qatarLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qatarDisplay.add(qatarLabel, BorderLayout.NORTH);

		
		qaProgressBarPanel = new JPanel();
		qaProgressBarPanel.setLayout(new GridLayout(0,1,0,0));
		qatarDisplay.add(qaProgressBarPanel, BorderLayout.SOUTH);
		qaProgressBarPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, Color.WHITE));

		// create panels for JProgessBars for customers
		qaPassengerProgresBarPanel = new JPanel();
		qaPassengerProgresBarPanel.setBackground(Color.WHITE);
		qaPassengerProgresBarPanel.setLayout(new BorderLayout(0,0));
		qaPassengerLabel = new JLabel("Flight Capacity");
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
		qaVolumeProgresBarPanel.setLayout(new BorderLayout(0,0));
		qaVolumeLabel = new JLabel("Hold Volume Capacity");
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
		qaWeightProgresBarPanel.setLayout(new BorderLayout(0,0));
		qaWeightLabel = new JLabel("Hold Weight Capacity");
		qaWeightLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		qaWeightProgresBarPanel.add(qaWeightLabel, BorderLayout.NORTH);
		qaWeight = new JProgressBar(SwingConstants.HORIZONTAL); 
		qaWeight.setValue(0); 
		qaWeight.setStringPainted(true);
		
		//

		qaProgressBarPanel.add(qaWeightProgresBarPanel, BorderLayout.SOUTH);
		qaWeightProgresBarPanel.add(qaWeight, BorderLayout.CENTER);


	}

	// when called will update the progress bars of each case. 
	public  synchronized void updateFlightVolumes(String carrier) {
		switch(carrier) 
		{
		case "British Airways":
			int bavolume = (int)passengersinqueue.BAtotalVolumeCapacity();
			baVolume.setValue(bavolume);
			int bacapacity = (int)passengersinqueue.BAtotalWeightCapacity();
			baWeight.setValue(bacapacity);
			int bapassenger = (int)passengersinqueue.getQ5().size();
			baPassenger.setValue(bapassenger);
		    break;
		case "Qatar Airways":
			int qavolume = (int)passengersinqueue.QAtotalVolumeCapacity();
			qaVolume.setValue(qavolume);
			int qacapacity = (int)passengersinqueue.QAtotalWeightCapacity();
			qaWeight.setValue(qacapacity);
			int qapassenger = (int)passengersinqueue.getQ4().size();
			qaPassenger.setValue(qapassenger);
		    break;

		case "Emirates Airlines":
			int eavolume = (int)passengersinqueue.EAtotalVolumeCapacity();
			eaVolume.setValue(eavolume);
			int eacapacity = (int)passengersinqueue.EAtotalWeightCapacity();
			eaWeight.setValue(eacapacity);
			int eapassenger = (int)passengersinqueue.getQ6().size();
			eaPassenger.setValue(eapassenger);
		    break;

		};
		 
		
		
		
		
	}
	
	/////////////////////////////////////////////
	//MVC pattern - allows listeners to be added
	////////////////////////////////////////////
	
	public void addGUIMainListener(ActionListener al) {

		processButton.addActionListener(al);

	}

	public void disableProcessButton() {

		processButton.setEnabled(false);

		//Thread thread = new Thread(new Airport(passengersinqueue));
		//thread.start();

		SwingWorker<Void, LinkedList<Passenger>> EconomyWorker = new SwingWorker<Void, LinkedList<Passenger>>() {

			@Override
			protected Void doInBackground()  {
				while (!isCancelled()) {
			          try {
			        		LinkedList<Passenger>p=passengersinqueue.economyQueueputi();
						/*
						 * for(Passenger p1:p) { String name=p1.getFullName(); publish(name); }
						 */
						publish(p);	
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				return null;
			}

			@Override
			protected void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> geco1 = chunks.get(chunks.size() - 1);
				int count=0;
				for(Passenger p:geco1) {
					count++;
			checkinQueueTxtArea.setText(new Date()+"\n"+p.getFullName()+" has joined the line"+System.lineSeparator()+"CheckedIn?:"+p.getCheckInStatus()+"\n"+geco1.size()+" are now in the line"+"\n");
			}
					
				
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
	
		
			
			
		};

		SwingWorker<Void, LinkedList<Passenger>> FirstWorker = new SwingWorker<Void, LinkedList<Passenger>>() {

			@Override
			protected Void doInBackground() throws InterruptedException  {
				while (!isCancelled()) {
			          try {
			        		LinkedList<Passenger>p=passengersinqueue.firstbusinessQueueputi();
						/*
						 * for(Passenger p1:p) { String name=p1.getFullName(); publish(name); }
						 */
						publish(p);	
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				return null;
			}

			@Override
			protected void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger> geco1 = chunks.get(chunks.size() - 1);
				for(Passenger p:geco1) {
					firstcheckinQueueTxtArea.setText(new Date()+"\n"+p.getFullName()+" has joined the line"+System.lineSeparator()+"CheckedIn?:"+p.getCheckInStatus()+"\n"+geco1.size()+" are now in the line");
			
			}
					
				
			}
		};

		SwingWorker<Passenger, LinkedList<Passenger>> SecurityWorker = new SwingWorker<Passenger, LinkedList<Passenger>>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				Thread.sleep(3000);
				while (!isCancelled()) {
					
                   LinkedList<Passenger>ls=passengersinqueue.securityPicker();
					publish(ls);	

				
			}
				return null;}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<LinkedList<Passenger>> chunks) {
				LinkedList<Passenger>  gsecurity = chunks.get(chunks.size() - 1);
				for(Passenger p:gsecurity) {
				securityQueueTxtArea.setText(p.getFullName()+" is now at security"+System.lineSeparator()+"\n"+"CheckedIn?: "+p.getCheckInStatus());
			
			}
				
			}
		};

		SwingWorker<Passenger, LinkedList<Passenger> > BoardingWorker = new SwingWorker<Passenger, LinkedList<Passenger> >() {

			@Override
			protected Passenger doInBackground() throws Exception {
			
					
                           Thread.sleep(4000);


					while (!isCancelled()) {
						
		                   LinkedList<Passenger>ls=passengersinqueue.boardingPicker();
							publish(ls);	

						
					}
						
				
			
				return null;}
			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<LinkedList<Passenger> > chunks) {
				LinkedList<Passenger>  gboarding = chunks.get(chunks.size() - 1);
				int count=0;
				for(Passenger p:gboarding) {
					count++;
					boardingQueueTxtArea.append(p.getFullName()+"\t"+" is now boarding flight: "+"\t"+p.getflightCode()+"\t"+count+System.lineSeparator());
				    if(count==164) {
				    	//boardingQueueTxtArea.setText("All aboard:"+ count);
				    	ecoDesk1TxtArea.setText("");
				    	desk1TxtArea.setText("");
				    	securityQueueTxtArea.setText("");
				    	firstcheckinQueueTxtArea.setText("");
				    	checkinQueueTxtArea.setText("");
				    }
				}
				
			}
		};

		
		SwingWorker<Passenger, Passenger> FirstCheckinWorker = new SwingWorker<Passenger, Passenger>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				Thread.sleep(2000);

				while (!isCancelled()) {

					Passenger p=passengersinqueue.BusinessFirstPicker();
				    publish(p);
				    updateFlightVolumes("British Airways");
					updateFlightVolumes("Qatar Airways");
					updateFlightVolumes("Emirates Airlines");
				//publish(p.getFullName());
				//String name=p.getFullName();
			      // publish(name+" is now checking in at economy ");
		}
			return null;}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<Passenger> chunks) {
				Passenger gecocheckin = chunks.get(chunks.size() - 1);
				try {
				desk1TxtArea.setText(new Date()+"\n"+gecocheckin.getFullName() +"is now checking"+"\n"+"flight: "+gecocheckin.getflightCode());
				}catch(NullPointerException u) {
					
				}
				}
		};

		SwingWorker<Passenger, Passenger> EconomyCheckinWorker1 = new SwingWorker<Passenger, Passenger>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				Thread.sleep(2000);
				while (!isCancelled()) {

					Passenger p=	passengersinqueue.EconomyPicker();
				    publish(p);
				    updateFlightVolumes("British Airways");
					updateFlightVolumes("Qatar Airways");
					updateFlightVolumes("Emirates Airlines");
				//publish(p.getFullName());
				//String name=p.getFullName();
			      // publish(name+" is now checking in at economy ");
		}
			return null;}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<Passenger> chunks) {
				Passenger gecocheckin = chunks.get(chunks.size() - 1);
				try {
				ecoDesk1TxtArea.setText(new Date()+"\n"+gecocheckin.getFullName() +"is now checking"+"\n"+"flight: "+gecocheckin.getflightCode());
				}catch(NullPointerException u) {
					
				}
				}
		};
		
		SwingWorker<Passenger, String> EconomyCheckinWorker2 = new SwingWorker<Passenger, String>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				while (!isCancelled()) {
					try {
						passengersinqueue.EconomyPicker1();
					    String gecocheckin = "";
						gecocheckin = gecocheckin + (passengersinqueue.EconomyPicker().getFullName());
						//used to update the progress bars, will replace with get method for flight carrier
						updateFlightVolumes("British Airways");
						updateFlightVolumes("Qatar Airways");
						updateFlightVolumes("Emirates Airlines");
						publish(gecocheckin);
					} catch (NullPointerException | InterruptedException N) {
						//System.out.println("The first and business class check-in desk is empty");
					}
				}
				return passenger;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<String> chunks) {
				String gecocheckin = chunks.get(chunks.size() - 1);
				ecoDesk2TxtArea.setText(gecocheckin);
				}
		};
		
		SwingWorker<Passenger, String> EconomyCheckinWorker3 = new SwingWorker<Passenger, String>() {

			@Override
			protected Passenger doInBackground() throws Exception {
				while (!isCancelled()) {
					try {
						passengersinqueue.EconomyPicker2();
					    String gecocheckin = "";
						gecocheckin = gecocheckin + (passengersinqueue.EconomyPicker2().getFullName());
						//used to update the progress bars, will replace with get method for flight carrier
						updateFlightVolumes("British Airways");
						updateFlightVolumes("Qatar Airways");
						updateFlightVolumes("Emirates Airlines");
						publish(gecocheckin);
					} catch (NullPointerException | InterruptedException N) {
						//System.out.println("The first and business class check-in desk is empty");
					}
				}
				return passenger;
			}

			// this takes the chunks of string sent from the publish() method whilst the
			// thread is running
			@Override
			protected void process(List<String> chunks) {
				String gecocheckin = chunks.get(chunks.size() - 1);
				ecoDesk3TxtArea.setText(gecocheckin);
				}
		};
		
		// starts all the swing workers runnning 
		EconomyWorker.execute();
		FirstWorker.execute();
		SecurityWorker.execute();
		BoardingWorker.execute();
		FirstCheckinWorker.execute();
		EconomyCheckinWorker1.execute();
		//EconomyCheckinWorker2.execute();
	//	EconomyCheckinWorker3.execute();
	}

	// this update method needs to be fixed at this moment it is non-functional
	@Override
	public void update(Observable o, Object arg) {

		// reportB = passengersinqueue.getBoardingReport();

	}
	public void appendString(StringBuilder builder, String value) {
	    builder.append(value + System.lineSeparator());
	}

	// The idea of the following two methods updateTextAre() and
	// redirectSystemStreams() is to direct the text printed on the console to the
	// GUI text area
	// But both of the following do not seem to work
	// It has been suggested to declare redirectSystemStreams() as an independent
	// thread which needed to be tested out

}