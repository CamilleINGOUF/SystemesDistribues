package multi_clients_theads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static final int PORT = 5666;
	
	private ServerSocket ss;
	private Socket client;
	
	private ArrayList<ClientRunnable> clients;
	
	public Server() throws IOException {
		ss = new ServerSocket(PORT);
		clients = new ArrayList<>();
		
		boolean isRunning = true;
		
		System.out.println("[!] Server started on port "+PORT);
		
		while(isRunning)
		{
			System.out.println("Waiting for a client...");
			client = ss.accept();
			System.out.println("[!]Client connected.");
			
			ClientRunnable clientR = new ClientRunnable(client,this);
			clients.add(clientR);
			
			Thread clientT = new Thread(clientR);
			clientT.start();
			System.out.println("[!]Client "+clients.indexOf(clientR)+" started.");
			
			client = null;
			sendClient();
		}
	}
	
	public void removeClient(ClientRunnable client)
	{
		clients.remove(client);
		sendMessage("%from%SERVEUR%/from%%all%"+client.getName()+" disconnected.");
		sendClient();
	}
	
	public void sendMessage(String message)
	{
		String[] senderMessage = ServerUtil.getSender(message);
		
		if(ServerUtil.getMessageType(senderMessage[1]) == ServerUtil.ALL)
		{
			String cleanMessage = ServerUtil.cleanMessageForAll(senderMessage[1]);
			for(ClientRunnable client : clients)
			{
				client.sendMessage("%message%"+senderMessage[0]+" => "+cleanMessage);
			}
		}
		else//whisper
		{
			String targets[] = ServerUtil.getTargetsWhisper(senderMessage[1]);
			String prefix = new String("%message%"+senderMessage[0] + " TO ");
			for(String target : targets)
				prefix += target + " ";
			prefix += "=> ";
			String cleanedMessage = ServerUtil.cleanMessageFortWhisper(senderMessage[1]);
			for(String s : targets)
				sendMessageToClientbyName(s,prefix+cleanedMessage);
			sendMessageToClientbyName(senderMessage[0], prefix+cleanedMessage);
		}
	}
	
	public void sendClient()
	{
		String message = new String();
		for(ClientRunnable client : clients)
			message+=client.getName()+";";
		
		for(ClientRunnable client : clients)
		{
			client.sendMessage("%clients%"+message);
		}
	}
	
	public void sendMessageToClientbyName(String name, String message)
	{
		getClientByName(name).sendMessage(message);
	}
	
	public ClientRunnable getClientByName(String name)
	{
		for(ClientRunnable cli : clients)
		{
			if(cli.getName().equals(name))
				return cli;
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
