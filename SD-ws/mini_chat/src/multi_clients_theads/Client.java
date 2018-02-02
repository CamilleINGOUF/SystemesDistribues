package multi_clients_theads;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private Socket socket;
	private PrintWriter out;
	
	
	
	public Client() {
		try {
			socket = new Socket("localhost", Server.PORT);
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}
	
	public void disconnect()
	{
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
