package chat.impl;

import java.awt.Color;
import java.util.ArrayList;

import org.omg.PortableServer.POA;

import chat.ChatClient;
import chat.ColorHelper;
import chat.MessagePOA;

public class MessageImpl extends MessagePOA {

	private String message;
	private ChatClient sender;
	private ArrayList<ChatClient> targets;
	
	private Color color;
	
	private POA rootPOA;
	
	public MessageImpl(String message, ChatClient sender, ArrayList<ChatClient> targets, Color color, POA rootPOA) 
	{
		this.message = message;
		this.sender = sender;
		this.targets = targets;
		this.color = color;
		this.rootPOA = rootPOA;
	}
	
	@Override
	public ChatClient getSender() {
		return sender;
	}

	@Override
	public boolean isForAll() {
		return targets.isEmpty();
	}

	@Override
	public ChatClient[] getTargets() {
		ChatClient[] cls = new ChatClient[targets.size()];
		return targets.toArray(cls);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public chat.Color getColor() {
		chat.Color colRef = null;

		ColorImpl col = new ColorImpl(color);
		org.omg.CORBA.Object ref;
		try 
		{
			ref = rootPOA.servant_to_reference(col);
			colRef = ColorHelper.narrow(ref);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return colRef;
	}
}
