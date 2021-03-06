package multi_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerTCP_loop {

	boolean running = true;
	
	ServerSocket ss;
	Socket client;
	
	public ServerTCP_loop() {
		try {
			ss = new ServerSocket(TCP_loop_helper.PORT);
			
			int i = 0;
			while(running)
			{
				System.out.println(new Date() + " -> Server ready");
				client = ss.accept();
				System.out.println(new Date() + " -> Client connected");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String message = new String();
				while((message = reader.readLine()).compareTo(TCP_loop_helper.END_OF_MESSAGE) != 0)
				{
					System.out.println("Client : ["+ i++ +"]"+message);
				}
				
				PrintWriter out = new PrintWriter(client.getOutputStream());
				out.println("Bye.");
				out.flush();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new ServerTCP_loop();
	}

}
