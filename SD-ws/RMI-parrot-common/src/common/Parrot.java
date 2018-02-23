package common;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface Parrot extends Remote {
	public void repeat(String msg) throws RemoteException;
	public Date watTime() throws RemoteException;
	public void register(ClientInterface client) throws RemoteException;
}
