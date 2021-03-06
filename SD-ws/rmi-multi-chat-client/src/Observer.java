import java.rmi.RemoteException;
import java.util.ArrayList;

import common.ClientInterface;
import common.Message;

public interface Observer 
{
	public void update(Message message) throws RemoteException;
	public void update(ArrayList<ClientInterface> clients);
	public void update(String message);
}
