package mini_chat_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
	private Socket socketClient;
	private ServerSocket ss;
	
	public static final int PORT = 6666; 
	
	public ServerTCP() {
		try {
			
			ss = new ServerSocket(PORT);
			System.out.println("Server opened on port "+PORT);
			
			System.out.println("Waiting...");
			socketClient = ss.accept();
			
			System.out.println("Client connected.");
			System.out.println("Client is "+socketClient.getInetAddress());
			
			System.out.println("Waiting for messages from client...");
			BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			String message = new String();
			int messageNumber = 1;
			
			while((message = in.readLine()) != null)
			{
				System.out.println("Message ["+messageNumber+"] = "+message);
				messageNumber++;
			}
			
			socketClient.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ServerTCP();
	}
}
