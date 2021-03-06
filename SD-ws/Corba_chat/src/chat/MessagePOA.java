package chat;


/**
* chat/MessagePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat.idl
* vendredi 23 mars 2018 10 h 33 CET
*/

public abstract class MessagePOA extends org.omg.PortableServer.Servant
 implements chat.MessageOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getSender", new java.lang.Integer (0));
    _methods.put ("isForAll", new java.lang.Integer (1));
    _methods.put ("getTargets", new java.lang.Integer (2));
    _methods.put ("getMessage", new java.lang.Integer (3));
    _methods.put ("getColor", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // chat/Message/getSender
       {
         chat.ChatClient $result = null;
         $result = this.getSender ();
         out = $rh.createReply();
         chat.ChatClientHelper.write (out, $result);
         break;
       }

       case 1:  // chat/Message/isForAll
       {
         boolean $result = false;
         $result = this.isForAll ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // chat/Message/getTargets
       {
         chat.ChatClient $result[] = null;
         $result = this.getTargets ();
         out = $rh.createReply();
         chat.MessagePackage.clientsHelper.write (out, $result);
         break;
       }

       case 3:  // chat/Message/getMessage
       {
         String $result = null;
         $result = this.getMessage ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // chat/Message/getColor
       {
         chat.Color $result = null;
         $result = this.getColor ();
         out = $rh.createReply();
         chat.ColorHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:chat/Message:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Message _this() 
  {
    return MessageHelper.narrow(
    super._this_object());
  }

  public Message _this(org.omg.CORBA.ORB orb) 
  {
    return MessageHelper.narrow(
    super._this_object(orb));
  }


} // class MessagePOA
