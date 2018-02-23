package common;

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
	
	public String message;
	
	public Date date;
	
	public Message(String from, ArrayList<String> to, String message) 
	{
		this.from = from;
		this.to = to;
		date = new Date();
		this.message = message;
	}
	
	public boolean isForAll()
	{
		return to == null || to.isEmpty();
	}
}
