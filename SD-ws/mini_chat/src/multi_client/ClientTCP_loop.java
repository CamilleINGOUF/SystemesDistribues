package multi_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ClientTCP_loop {

	private Socket socket;
	
	public ClientTCP_loop() {
		try {
			System.out.println(new Date() + " -> Client started.");
			socket = new Socket("localhost", TCP_loop_helper.PORT);
			System.out.println(new Date() + " -> Client connected.");
			
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println("The game.");
			out.flush();
			
			out.println(TCP_loop_helper.END_OF_MESSAGE);
			out.flush();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = new String();
			while((message = reader.readLine()) != null)
			{
				System.out.println("Server : "+message);
			}
			
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientTCP_loop();
	}

}
