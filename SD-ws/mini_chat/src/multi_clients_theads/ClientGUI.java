package multi_clients_theads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ClientGUI extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;

	Client client;
	
	JPanel mainPanel;
	JTextField textField;
	JTextArea messages;
	JTextArea clientsArea;
	
	JList<String> clientsName;
	
	private String name;
	
	private byte[] imageSize;
	private ByteArrayOutputStream byteArrayOutputStream;
	
	public ClientGUI() {
		setup();
		client = new Client(name/*,imageSize,byteArrayOutputStream.toByteArray()*/);
		client.register(this);
	}
	
	private void setup()
	{
		name = JOptionPane.showInputDialog(this,"What's your name ?");
		
		if(name == null)
			System.exit(0);;
		
		if(name.equals(""))
			name = new String("Guest");

		FileFilter imagesFilter = new FileNameExtensionFilter("jpg", "jpeg", "png");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose an avatar");
		fileChooser.addChoosableFileFilter(imagesFilter);
//		int result = fileChooser.showOpenDialog(null);
//		 
//		if (result == JFileChooser.APPROVE_OPTION)
//        {
//            String filename = fileChooser.getSelectedFile().getPath();
//            JOptionPane.showMessageDialog(null, "You selected " + filename);
//            try {
//				BufferedImage image = ImageIO.read(new File(filename));
//	            byteArrayOutputStream = new ByteArrayOutputStream();
//	            ImageIO.write(image, "jpg", byteArrayOutputStream);
//	            imageSize = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//        }
//        else if (result == JFileChooser.CANCEL_OPTION)
//        {   
//            JOptionPane.showMessageDialog(null, "You selected nothing.");
//        }
//        else if (result == JFileChooser.ERROR_OPTION)
//        {
//            JOptionPane.showMessageDialog(null, "An error occurred.");  
//        }         
		
		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		textField = new JTextField();
		textField.setSize(800, 150);
		
		mainPanel.add(textField,BorderLayout.SOUTH);
		
		messages = new JTextArea(10,48);
		messages.setEditable(false);
		messages.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(messages);
		mainPanel.add(scrollPane, BorderLayout.WEST);
		
		
		clientsName = new JList<>();
		clientsName.setLayoutOrientation(JList.VERTICAL);
		clientsName.setVisibleRowCount(-1);
		clientsName.setSize(100, 7);
		
		clientsArea = new JTextArea(5,12);
		clientsArea.setSize(600, 10);
		clientsArea.setEditable(false);
		clientsArea.setBackground(Color.LIGHT_GRAY);
//		JScrollPane scrollPaneClients = new JScrollPane(clientsArea);
		JScrollPane scrollPaneClients = new JScrollPane(clientsName);
		clientsArea.setWrapStyleWord(true);
		mainPanel.add(scrollPaneClients, BorderLayout.EAST);

		
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle(name);
		
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prefix = new String("%all%");
				
				if(!clientsName.isSelectionEmpty())
				{
					prefix = "%whisper%";
					for(String s : clientsName.getSelectedValuesList())
						prefix += (String)s+";";
					prefix += "%/whisper%";
				}
					
				client.sendMessage(prefix+textField.getText());
				textField.setText("");
				clientsName.clearSelection();
			}
		});
	}
	
	private void processMessage(String message)
	{
		if(message.substring(0, 9).equals("%message%"))
		{
			messages.append(message.substring(9)+"\n");
		}
		else
		{
			showClientsNames(message.substring(9));
		}
	}
	
	private void showClientsNames(String str)
	{
		clientsArea.setText("");
		String strs[] = str.split(";");
		clientsName.setListData(strs);
		clientsName.repaint();
	}
	
	@Override
	public void update(String message) 
	{
		processMessage(message);
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}
}
