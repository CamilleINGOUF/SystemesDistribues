package multi_clients_theads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	private OutputStream outImage;
	private InputStream inImage;
	
	private ArrayList<Observer> observers;

	public Client(String name) 
	{
		try {
			observers = new ArrayList<>();
			socket = new Socket("localhost", Server.PORT);
			out = new PrintWriter(socket.getOutputStream());
			out.println(name);
			out.flush();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Client(String name, byte[] imageSize, byte[] byteArray) 
	{
		try {
			observers = new ArrayList<>();
			socket = new Socket("localhost", Server.PORT);
			out = new PrintWriter(socket.getOutputStream());
			out.println(name);
			out.flush();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			outImage = socket.getOutputStream();
			inImage = socket.getInputStream(); 
			
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}

	@Override
	public void run() 
	{
		try {
			String message = new String();
			while((message = in.readLine()) != null)
			{
				notifyObeservers(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void register(Observer o)
	{
		observers.add(o);
	}
	
	private void notifyObeservers(String message)
	{
		for(Observer o : observers)
			o.update(message);
	}
}
