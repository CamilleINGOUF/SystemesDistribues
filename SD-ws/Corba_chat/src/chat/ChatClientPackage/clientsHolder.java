package chat.ChatClientPackage;


/**
* chat/ChatClientPackage/clientsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* vendredi 16 mars 2018 15 h 49 CET
*/

public final class clientsHolder implements org.omg.CORBA.portable.Streamable
{
  public chat.ChatClient value[] = null;

  public clientsHolder ()
  {
  }

  public clientsHolder (chat.ChatClient[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = chat.ChatClientPackage.clientsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    chat.ChatClientPackage.clientsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return chat.ChatClientPackage.clientsHelper.type ();
  }

}