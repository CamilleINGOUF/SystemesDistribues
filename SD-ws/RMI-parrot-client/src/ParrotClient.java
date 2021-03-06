import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.ClientInterface;
import common.Parrot;

public class ParrotClient implements ClientInterface {
	
	public String name = "GuyClient";
	
	public ParrotClient() 
	{
		try 
		{
			ClientInterface stub = (ClientInterface) UnicastRemoteObject.exportObject(this, 0) ;
			Parrot serv = (Parrot) Naming.lookup("rmi://localhost/Guy");
			
			serv.repeat("Hello Guy !");
			System.out.println("Guy : "+serv.watTime());
			serv.register(stub);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ParrotClient();
	}

	@Override
	public void showYourName() throws RemoteException 
	{
		System.out.println("My name is "+name);
	}
}
