package multi_clients_theads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static final int PORT = 4666;
	
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
		}
	}
	
	public void removeClient(ClientRunnable client)
	{
		clients.remove(client);
	}
	
	public void sendMessage(String message)
	{
		for(ClientRunnable client : clients)
		{
			client.sendMessage(message);
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
