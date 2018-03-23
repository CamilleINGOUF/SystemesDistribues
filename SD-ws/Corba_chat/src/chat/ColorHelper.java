package chat;


/**
* chat/ColorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* vendredi 23 mars 2018 10 h 33 CET
*/

abstract public class ColorHelper
{
  private static String  _id = "IDL:chat/Color:1.0";

  public static void insert (org.omg.CORBA.Any a, chat.Color that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static chat.Color extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (chat.ColorHelper.id (), "Color");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static chat.Color read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ColorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, chat.Color value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static chat.Color narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof chat.Color)
      return (chat.Color)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      chat._ColorStub stub = new chat._ColorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static chat.Color unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof chat.Color)
      return (chat.Color)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      chat._ColorStub stub = new chat._ColorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
