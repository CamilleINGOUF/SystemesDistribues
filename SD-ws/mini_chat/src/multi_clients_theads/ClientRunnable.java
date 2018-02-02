package multi_clients_theads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunnable implements Runnable {

	private Socket client;
	
	private Server server;
	
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientRunnable(Socket socket, Server server) {
		client = socket;
		this.server = server;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
				System.out.println(message);
				server.sendMessage(message);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}

}
