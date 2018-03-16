package chat.impl;

import javax.swing.text.BadLocationException;

import chat.ChatClient;
import chat.Message;

public interface Observer 
{
	public void update(Message message) throws BadLocationException;
	public void update(String message);
	void update(ChatClient[] clients);
}
