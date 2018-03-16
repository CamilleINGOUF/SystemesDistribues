package callbacks;


/**
* callbacks/CallbackServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from callbacks.idl
* vendredi 16 mars 2018 11 h 22 CET
*/

public abstract class CallbackServerPOA extends org.omg.PortableServer.Servant
 implements callbacks.CallbackServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sayHello", new java.lang.Integer (0));
    _methods.put ("register", new java.lang.Integer (1));
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
       case 0:  // callbacks/CallbackServer/sayHello
       {
         this.sayHello ();
         out = $rh.createReply();
         break;
       }

       case 1:  // callbacks/CallbackServer/register
       {
         callbacks.CallbackClient client = callbacks.CallbackClientHelper.read (in);
         this.register (client);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:callbacks/CallbackServer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CallbackServer _this() 
  {
    return CallbackServerHelper.narrow(
    super._this_object());
  }

  public CallbackServer _this(org.omg.CORBA.ORB orb) 
  {
    return CallbackServerHelper.narrow(
    super._this_object(orb));
  }


} // class CallbackServerPOA
