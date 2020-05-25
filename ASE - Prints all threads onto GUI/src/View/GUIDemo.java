package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Model.EcoProducer;
import Model.FirstProducer;
import Model.Passenger;
import Model.PassengersInQueue;

public class GUIDemo extends JFrame{
	
	private JPanel contentPane;
	private JPanel northPanel, southPanel, EcocheckinQueue, FirstcheckinQueue;
	private JButton processButton;
	private JTextArea EcocheckinQueueTxtArea, FirstcheckinQueueTxtArea;
    private JLabel EcocheckinQueuelabel, FirstcheckinQueuelabel;
	
	PassengersInQueue q;
	Passenger p;
	
	
	
	public GUIDemo (String title) {
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1500, 1500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createLineBorder(new Color(0, 206, 209), 10));
		setContentPane(contentPane);

		setupNorthPanel();
		setupSouthPanel();

		
		
		
		
       setVisible(true);
		
		
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
		
		processButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
				
			}
			
		});
	}

	
	private void setupSouthPanel() {

		southPanel = new JPanel();
		southPanel.setSize(1200, 1200);
		contentPane.add(southPanel, BorderLayout.CENTER);
		southPanel.setLayout(new GridLayout(0, 2, 0, 0));

		// set up passengers in check-in queue panel
		EcocheckinQueue = new JPanel();
		EcocheckinQueue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		southPanel.add(EcocheckinQueue);
		EcocheckinQueue.setLayout(new BorderLayout(0, 0));

		// create relevant labels
		EcocheckinQueuelabel = new JLabel("Passengers in Check-In Queue");
		EcocheckinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		EcocheckinQueue.add(EcocheckinQueuelabel, BorderLayout.NORTH);

		// create text area
		EcocheckinQueueTxtArea = new JTextArea();
		EcocheckinQueueTxtArea.setLineWrap(true);
		EcocheckinQueueTxtArea.setWrapStyleWord(true);
		EcocheckinQueueTxtArea.setEditable(false);
		EcocheckinQueueTxtArea.setSize(100, 100);
	    EcocheckinQueue.add(EcocheckinQueueTxtArea, BorderLayout.CENTER);

	 // set up passengers in check-in queue panel
	 		FirstcheckinQueue = new JPanel();
	 		FirstcheckinQueue.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	 		southPanel.add(FirstcheckinQueue);
	 		FirstcheckinQueue.setLayout(new BorderLayout(0, 0));

	 		// create relevant labels
	 		FirstcheckinQueuelabel = new JLabel("Passengers in Check-In Queue");
	 		FirstcheckinQueuelabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	 		FirstcheckinQueue.add(FirstcheckinQueuelabel, BorderLayout.NORTH);

	 		// create text area
	 		FirstcheckinQueueTxtArea = new JTextArea();
	 		FirstcheckinQueueTxtArea.setLineWrap(true);
	 		FirstcheckinQueueTxtArea.setWrapStyleWord(true);
	 		FirstcheckinQueueTxtArea.setEditable(false);
	 		FirstcheckinQueueTxtArea.setSize(100, 100);
	 	    FirstcheckinQueue.add(FirstcheckinQueueTxtArea, BorderLayout.CENTER);
	 	    
	 	    
	 	   
	
	}
	
	
	private void start() {
		
		System.out.println("Start");
		
		SwingWorker<Void, Void> worker = new SwingWorker <Void, Void> (){

			@Override
			protected Void doInBackground() throws Exception {
				
				Thread thread1 = new Thread(new EcoProducer(q));
				Thread thread2 = new Thread(new FirstProducer(q));
				
				thread1.start();
				thread2.start();
				
				try {
					
					thread1.join();
					thread2.join();
								
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return null;
			}
			
		};
		
		worker.execute();
				
	}		
				
	
	
	
	
	

}
