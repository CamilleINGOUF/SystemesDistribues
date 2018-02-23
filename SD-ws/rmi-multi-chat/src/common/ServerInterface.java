package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote 
{
	public void register(ClientInterface client) throws RemoteException;
	public void sendMessageToAll(Message message) throws RemoteException;
}