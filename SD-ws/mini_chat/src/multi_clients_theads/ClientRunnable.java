package multi_clients_theads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {

	private Socket client;
	
	private Server server;
	
	private String name;
	
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientRunnable(Socket socket, Server server) {
		client = socket;
		this.server = server;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			name = in.readLine();
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try 
		{
			String message = new String();
			while((message = in.readLine()) != null)
			{
				server.sendMessage(name+" => "+message);
			}
		} 
		catch (IOException e) 
		{
			System.out.println("Client deconnected");
		} 
		finally 
		{
			
		}
	}
	
	public void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}
	
	public String getName()
	{
		return name;
	}

}
