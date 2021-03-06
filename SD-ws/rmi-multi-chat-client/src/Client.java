import java.awt.Color;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import common.ClientInterface;
import common.Message;
import common.ServerInterface;

public class Client implements ClientInterface
{
	public ClientInterface stub;
	public ServerInterface serv;

	private String name;

	private ImageIcon image;
	public ArrayList<Observer> obs;

	public Color color;

	public Client(String name, ClientGUY clientGUY, ImageIcon image) 
	{
		this.name = name;
		this.image = image;
		obs = new ArrayList<>();
		color = Color.black;
		obs.add(clientGUY);
		try {
			stub = (ClientInterface) UnicastRemoteObject.exportObject(this, 0) ;
			serv = (ServerInterface) Naming.lookup("rmi://localhost/Server");
			serv.register(stub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printMessage(Message message) throws RemoteException
	{
		notifyObservers(message);
	}

	private void notifyObservers(Message message)
	{
		try {
			for(Observer o : obs)
				o.update(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Message message)
	{
		try 
		{
			if(message.isForAll())
				serv.sendMessageToAll(message);
			else
			{
				serv.sendWhisper(message);
			}
		} 
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String giveYourName() throws RemoteException 
	{
		return name;
	}

	public void registerObserver(Observer o)
	{
		obs.add(o);
	}

	public void shutdown() 
	{
		try {
			serv.remove(stub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	
	public void printClients(ArrayList<ClientInterface> clients) throws RemoteException 
	{
		for(Observer o : obs)
			o.update(clients);
	}

	@Override
	public void ServerMessage(String message) throws RemoteException 
	{
		for(Observer o : obs)
			o.update(message);
	}

	public ImageIcon getYourAvatar() throws RemoteException {
		return image;
	}

	@Override
	public Color giveColor() {
		return color;
	}
}
