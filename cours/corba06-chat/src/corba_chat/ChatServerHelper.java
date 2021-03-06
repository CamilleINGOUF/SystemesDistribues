package corba_chat;


/**
* corba_chat/ChatServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba_chat.idl
* vendredi 17 février 2017 12 h 36 CET
*/

abstract public class ChatServerHelper
{
  private static String  _id = "IDL:corba_chat/ChatServer:1.0";

  public static void insert (org.omg.CORBA.Any a, corba_chat.ChatServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba_chat.ChatServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corba_chat.ChatServerHelper.id (), "ChatServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corba_chat.ChatServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ChatServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba_chat.ChatServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corba_chat.ChatServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba_chat.ChatServer)
      return (corba_chat.ChatServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba_chat._ChatServerStub stub = new corba_chat._ChatServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corba_chat.ChatServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba_chat.ChatServer)
      return (corba_chat.ChatServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba_chat._ChatServerStub stub = new corba_chat._ChatServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
