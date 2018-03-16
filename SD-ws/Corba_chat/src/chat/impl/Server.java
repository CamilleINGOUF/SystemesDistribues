package chat.impl;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import chat.ChatClient;
import chat.ChatServerPOA;
import chat.Message;

public class Server extends ChatServerPOA {

	private ArrayList<ChatClient> clients;

	public Server(String[] args) 
	{
		try{
			clients = new ArrayList<>();
			
			ORB orb = ORB.init(args, null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			
			System.out.println(InetAddress.getLocalHost().getHostName());
			
			byte[] id = rootPOA.activate_object(this);
			org.omg.CORBA.Object ref = rootPOA.id_to_reference(id);
			
			String ior = orb.object_to_string(ref);
			System.out.println(ior);
			
			PrintWriter writer = new PrintWriter("server_ior.txt");
			writer.print(ior);
			writer.close();
			
			rootPOA.the_POAManager().activate();
			orb.run();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void register(ChatClient client) 
	{
		clients.add(client);
		updateClients();
	}

	@Override
	public void remove(ChatClient client) 
	{
		clients.remove(client);
		updateClients();
	}
	
	private void updateClients()
	{
		ChatClient[] str = new ChatClient[clients.size()];
		for(ChatClient c : clients)
			c.updateClientList(clients.toArray(str));
	}

	public static void main(String[] args) {
		new Server(args);
	}

	@Override
	public void sendMessage(Message message) 
	{
		if(message.isForAll())
		{
			for(ChatClient c : clients){
				c.printMessage(message);
			}
		}
		else
		{
			//TODO
		}
	}
}
