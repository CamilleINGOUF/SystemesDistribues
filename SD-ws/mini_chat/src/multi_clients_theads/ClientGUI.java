package multi_clients_theads;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements Observer{
	
	Client client;
	
	JPanel mainPanel;
	JTextField textField;
	JTextArea textArea;
	
	private String name;
	
	public ClientGUI() {
		setup();
		client = new Client(name);
		client.register(this);
	}
	
	private void setup()
	{
		name = JOptionPane.showInputDialog(this,"What's your name ?");
		if(name == null || name.equals(""))
			name = new String("Guest");
		
		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		textField = new JTextField();
		textField.setSize(800, 150);
		
		mainPanel.add(textField,BorderLayout.SOUTH);
		
		textArea = new JTextArea(10,50);
		textArea.setEditable(false);
		mainPanel.add(textArea, BorderLayout.WEST);
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendMessage(textField.getText());
				textField.setText("");
			}
		});
	}
	
	@Override
	public void update(String message) 
	{
		textArea.append(message+"\n");
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}
}
