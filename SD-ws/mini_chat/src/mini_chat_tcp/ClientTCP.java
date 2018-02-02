package mini_chat_tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {
	private Socket socket;
	
	public ClientTCP() {
		try {
			System.out.println("Connectin to sever on port "+ServerTCP.PORT);
			socket = new Socket("localhost", ServerTCP.PORT);
			System.out.println("Connected.");
			
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			out.println("Hello my friend !");
			out.flush();
			
			out.println("Do you know da way ?");
			out.flush();
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientTCP();
	}
}
