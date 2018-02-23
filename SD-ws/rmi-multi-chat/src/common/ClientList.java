package common;

import java.util.ArrayList;

public class ClientList implements Common
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArrayList<String> clients = new ArrayList<>();
	
	public ClientList(ArrayList<String> clients)
	{
		this.clients = clients;
	}
}
