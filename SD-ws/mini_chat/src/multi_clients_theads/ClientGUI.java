package multi_clients_theads;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame{
	
	Client client;
	
	JPanel mainPanel;
	JTextField textField;
	JTextArea textArea;
	
	public ClientGUI() {
		setup();
		//client = new Client();
	}
	
	private void setup()
	{
		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		textField = new JTextField();
		textField.setSize(800, 150);
		mainPanel.add(textField,BorderLayout.SOUTH);
		
		textArea = new JTextArea(10,10);
		textArea.setEditable(false);
		mainPanel.add(textArea, BorderLayout.EAST);
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}
}
