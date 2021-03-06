package common;

import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public interface ClientInterface extends Remote 
{
	public void printMessage(Message message) throws RemoteException;
	public String giveYourName() throws RemoteException;
	public void printClients(ArrayList<ClientInterface> clients) throws RemoteException;
	public void ServerMessage(String message) throws RemoteException;
	public ImageIcon getYourAvatar() throws RemoteException;
	public Color giveColor() throws RemoteException;
}
