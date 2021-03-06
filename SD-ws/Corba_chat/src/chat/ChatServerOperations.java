package chat;


/**
* chat/ChatServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* vendredi 23 mars 2018 10 h 33 CET
*/

public interface ChatServerOperations 
{
  void register (chat.ChatClient client);
  void registerChannel (chat.ChatClient client, String channel);
  void remove (chat.ChatClient client);
  void sendMessage (chat.Message message);
  void createChannel (chat.ChatClient client, String name);
  void joinChannel (chat.ChatClient client, chat.ChatChannel channel);
  String[] getChannels ();
} // interface ChatServerOperations
