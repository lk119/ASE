package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;

public class test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("Slide the bar left or right to set the countdown timer");
		slider.setMinorTickSpacing(25);
		slider.setMajorTickSpacing(50);
		slider.setSnapToTicks(true);
		slider.setValue(100);
		slider.setMaximum(200);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		contentPane.add(slider, BorderLayout.WEST);
	}

}
