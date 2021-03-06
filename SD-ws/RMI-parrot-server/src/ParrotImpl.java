import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import common.ClientInterface;
import common.Parrot;

public class ParrotImpl implements Parrot 
{
	public ClientInterface client;
	
	public ParrotImpl() 
	{
		try
		{
			String name = "Guy";
			Parrot stub = (Parrot)UnicastRemoteObject.exportObject(this,0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind(name, stub);
			
			System.out.println("Guy is listening.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void repeat(String msg) throws RemoteException {
		System.out.println("Repeating "+msg);
	}

	@Override
	public Date watTime() throws RemoteException {
		Date date = new Date();
		System.out.println("I'm giving the date "+date);
		return date;
	}
	
	public static void main(String[] args) {
		new ParrotImpl();
	}

	@Override
	public void register(ClientInterface client) throws RemoteException 
	{
		this.client = client;
		client.showYourName();
	}
}
