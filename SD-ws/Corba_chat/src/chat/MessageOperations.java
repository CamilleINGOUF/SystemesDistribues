package chat;


/**
* chat/MessageOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* vendredi 16 mars 2018 15 h 49 CET
*/

public interface MessageOperations 
{
  chat.ChatClient getSender ();
  boolean isForAll ();
  chat.ChatClient[] getTargets ();
  String getMessage ();
  chat.Color getColor ();
} // interface MessageOperations
