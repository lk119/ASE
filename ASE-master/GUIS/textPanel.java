import java.awt.BorderLayout;

import javax.swing.*;


public class textPanel extends JPanel {
	
	private JTextArea textArea;
	
	public textPanel() {
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public void appendText(String text) {
		textArea.append(text);
		
	}
	
}
