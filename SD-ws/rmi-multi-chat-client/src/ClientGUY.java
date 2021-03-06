import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import common.ClientInterface;
import common.Message;

public class ClientGUY extends JFrame implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Client client;

	private JTextPane pane;
	private JTextField text;
	private JList<ClientInterface> group2;

	private String name;
	private ImageIcon image;

	public ClientGUY() 
	{

		name = JOptionPane.showInputDialog(this,"What's your name ?");
		Color color = JColorChooser.showDialog(null, "Give me a color", Color.BLACK);
		if(name == null)
			System.exit(0);

		if(name.equals(""))
			name = new String("Guest");

		selectImage();

		BuildUI();
		client = new Client(name,this, image);
		client.color = color;
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Client - "+name);
	}

	private void selectImage() 
	{
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			File file = fc.getSelectedFile();
			try 
			{
				image = new ImageIcon(ImageIO.read(file));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			image = null;
		}
	}

	private void BuildUI()
	{
		getContentPane().setLayout(new BorderLayout());

		text = new JTextField();
		getContentPane().add(text, BorderLayout.SOUTH);
		text.grabFocus();
		JScrollPane scroll;
		
		pane = new JTextPane();
		pane.setEditable(false);
		scroll = new JScrollPane(pane);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		group2 = new JList<>();
		group2.setCellRenderer(new ClientCellRenderer());
		scroll = new JScrollPane(group2);
		scroll.setPreferredSize(new Dimension(200, 1000));
		getContentPane().add(scroll, BorderLayout.EAST);
		addListeners();
	}

	private void addListeners() {
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					if(!text.getText().equals(""))
					{
						ArrayList<ClientInterface> tos = new ArrayList<>(group2.getSelectedValuesList());
						Message message = new Message(client, tos, text.getText(),client.color);
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
	public void update(Message message) throws RemoteException 
	{
		try {
			printToPane(pane, message);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void printToPane(JTextPane tp, Message message) throws BadLocationException, RemoteException
	{
		StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, message.color);
        
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        
        Date date = message.date;
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        if(message.isForAll())
        	tp.getDocument().insertString(len, date.getHours()+":"+date.getMinutes()+" "+message.froms.giveYourName()+" => "+message.message+"\n", aset);
		else
		{
			ArrayList<String> names= new ArrayList<>();
			for(ClientInterface cliI : message.tos)
				names.add(cliI.giveYourName());
			tp.getDocument().insertString(len, date.getHours()+":"+date.getMinutes()+" "+message.froms.giveYourName()+" to "+names+" => "+message.message+"\n", aset);
		}
	}

	@Override
	public void update(ArrayList<ClientInterface> clients)
	{
		ClientInterface[] str = new ClientInterface[clients.size()];
		group2.setListData(clients.toArray(str));
		repaint();
	}

	@Override
	public void update(String message) 
	{
		StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
        
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        Date date = new Date();
        int len = pane.getDocument().getLength();
        pane.setCaretPosition(len);
        pane.setCharacterAttributes(aset, false);
        
        try {
			pane.getDocument().insertString(len, date.getHours()+":"+date.getMinutes()+" SERVER  => "+message+"\n", aset);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
