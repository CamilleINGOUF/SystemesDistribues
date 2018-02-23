import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.ClientInterface;
import common.Message;
import common.ServerInterface;

public class Client implements ClientInterface
{
	ClientInterface stub;
	ServerInterface serv;
	
	String name;
	
	public Client() 
	{
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
		System.out.println(message.message);
	}

	@Override
	public String giveYourName() throws RemoteException 
	{
		return name;
	}
	
	public static void main(String[] args) {
		new Client();
	}
}