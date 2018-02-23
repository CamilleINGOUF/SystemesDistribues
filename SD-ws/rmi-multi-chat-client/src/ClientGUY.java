import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.Message;

public class ClientGUY extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Client client;
	
	private JTextArea display;
	private JTextField text;
	private JList<String> group;
	
	private String name;
	
	public ClientGUY() 
	{
		
		name = JOptionPane.showInputDialog(this,"What's your name ?");
		
		if(name == null)
			System.exit(0);
		
		if(name.equals(""))
			name = new String("Guest");
		
		BuildUI();
		client = new Client(name,this);
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Client - "+name);
	}
	
	private void BuildUI()
	{
		getContentPane().setLayout(new BorderLayout());
		
		text = new JTextField();
		getContentPane().add(text, BorderLayout.SOUTH);
		text.grabFocus();
		
		display = new JTextArea();
		display.setEditable(false);
		display.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(display);
		add(scroll, BorderLayout.CENTER);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		group = new JList<>();
		group.setPrototypeCellValue("Greg is ze Best !");
		scroll = new JScrollPane(group);
		add(scroll, BorderLayout.EAST);
		
		addListeners();
	}
	
	private void addListeners() {
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					if(!text.getText().equals(""))
					{
						ArrayList<String> tos = new ArrayList<>(group.getSelectedValuesList());
						Message message = new Message(name, tos, text.getText());
						client.sendMessage(message);
					}
					text.setText("");
					text.grabFocus();
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				closeWindow();
			}
		});
	}

	private void closeWindow() 
	{
		client.shutdown();
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		new ClientGUY();
	}

	@Override
	public void update(Message message) 
	{
		Date date = message.date;
		if(message.isForAll())
			display.append(date.getHours()+":"+date.getMinutes()+" "+message.from+" => "+message.message+"\n");
		else
		{
			display.append(date.getHours()+":"+date.getMinutes()+" "+message.from+" to "+message.to+" => "+message.message+"\n");
		}
	}

	@Override
	public void update(ArrayList<String> clients) 
	{
		System.out.println(clients);
		String[] str = new String[clients.size()];
		group.setListData(clients.toArray(str));
	}
}
