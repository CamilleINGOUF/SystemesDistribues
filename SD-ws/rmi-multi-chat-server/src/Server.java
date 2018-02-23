import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.ClientInterface;
import common.Message;
import common.ServerInterface;

public class Server implements ServerInterface{

	private ArrayList<ClientInterface> clients;
	
	ServerInterface stub;
	Registry registry;
	
	public Server() 
	{
		clients = new ArrayList<>();
		try {
			String name = "Server";
			stub = (ServerInterface)UnicastRemoteObject.exportObject(this,0);
			registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void register(ClientInterface client) throws RemoteException 
	{
		clients.add(client);
		System.out.println("New client connected.");
	}

	@Override
	public void sendMessageToAll(Message message) throws RemoteException 
	{
		for(ClientInterface client : clients)
			client.printMessage(message);
	}
	
	public static void main(String[] args) {
		new Server();
	}

}
