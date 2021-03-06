package multi_clients_theads;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ClientRunnable implements Runnable {

	private Socket client;
	
	private Server server;
	
	private String name;
	
	private BufferedReader in;
	private InputStream inImage;
	private PrintWriter out;
	
	private BufferedImage outImage;
	
	public ClientRunnable(Socket socket, Server server) {
		client = socket;
		this.server = server;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			inImage = client.getInputStream();
			name = in.readLine();
//	        image = ImageIO.read(new ByteArrayInputStream(imageAr));
//	        System.out.println("Received " + image.getHeight() + "x" + image.getWidth());
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
				server.sendMessage("%from%"+name+"%/from%"+message);
			}
		} 
		catch (IOException e) 
		{
			System.out.println("C'est la merde putain");
		} 
		finally 
		{
			System.out.println("Client deconnected");
			server.removeClient(this);
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
