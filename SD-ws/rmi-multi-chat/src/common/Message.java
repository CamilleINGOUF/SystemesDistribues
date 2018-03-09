package common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Common
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String from;
	public ArrayList<String> to;
	public ArrayList<ClientInterface> tos;
	
	public String message;
	
	public Date date;
	
	public Color color;
	public ClientInterface froms;
	
	public Message(String from, ArrayList<String> to, String message) 
	{
		this.from = from;
		this.to = to;
		date = new Date();
		this.message = message;
		color = Color.black;
	}
	
	public Message(ClientInterface from, ArrayList<ClientInterface> to, String message,Color color) 
	{
		this.froms = from;
		this.tos = to;
		date = new Date();
		this.message = message;
		this.color = color;
	}
	
	public boolean isForAll()
	{
		return tos == null || tos.isEmpty();
	}
}
