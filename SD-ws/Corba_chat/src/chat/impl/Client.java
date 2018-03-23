package chat.impl;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import chat.ChatClient;
import chat.ChatClientHelper;
import chat.ChatClientPOA;
import chat.ChatServer;
import chat.ChatServerHelper;
import chat.ColorHelper;
import chat.Message;
import chat.MessageHelper;

public class Client extends ChatClientPOA {

	private String name;
	private ORB orb;
	public ChatClient cref;

	private ChatServer server;

	private POA rootPOA;

	private ArrayList<Observer> obs;
	private Color color;
	
	private ImageIcon image;
	
	public String channel;

	public Client(String name, String[] args, Color color2, ImageIcon image) 
	{
		try {
			this.name = name;
			this.color = color2;
			this.image = image;
			obs = new ArrayList<>();

			orb = ORB.init(args, null);
			Thread orbThread = new Thread(new Runnable() {
				@Override
				public void run() 
				{
					orb.run();
				}
			});
			orbThread.start();

			rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(this);
			cref = ChatClientHelper.narrow(ref);

			BufferedReader br = new BufferedReader(new FileReader("server_ior.txt"));
			String ior = br.readLine();
			br.close();

			org.omg.CORBA.Object o = orb.string_to_object(ior);
			server = ChatServerHelper.narrow(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() 
	{
		return name;
	}
	
	@Override
	public void printMessage(Message message) 
	{
		notifyObservers(message);
	}

	public void sendMessage(Message message) 
	{
		server.sendMessage(message);
	}

	public void registerObserver(Observer o)
	{
		obs.add(o);
		server.registerChannel(cref, channel);
	}

	public Message createMessage(String message, ChatClient sender, ArrayList<ChatClient> targets)
	{
		Message mesRef = null;

		MessageImpl mes = new MessageImpl(message,sender,targets,color,rootPOA);
		org.omg.CORBA.Object ref;
		try 
		{
			ref = rootPOA.servant_to_reference(mes);
			mesRef = MessageHelper.narrow(ref);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return mesRef;
	}

	private void notifyObservers(Message message)
	{
		try
		{
			for(Observer o : obs)
				o.update(message);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String[] getChannelsFromServer()
	{
		return server.getChannels();
	}

	@Override
	public void updateClientList(ChatClient[] clients) 
	{
		for(Observer o : obs)
			o.update(clients);
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	@Override
	public chat.Color getColor() {
		chat.Color colRef = null;

		ColorImpl col = new ColorImpl(color);
		org.omg.CORBA.Object ref;
		try 
		{
			ref = rootPOA.servant_to_reference(col);
			colRef = ColorHelper.narrow(ref);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return colRef;
	}

	@Override
	public void shutdown() {
		server.remove(cref);
	}

	@Override
	public Any getImage() {
		Any any = orb.create_any();
//		TypeCode tc = createTypeCode((Serializable)image, any, orb);
		any.insert_Value((Serializable)image);
		return any;
	}
}
