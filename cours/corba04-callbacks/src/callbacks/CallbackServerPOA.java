package callbacks;

/**
 * Interface definition: CallbackServer.
 * 
 * @author OpenORB Compiler
 */
public abstract class CallbackServerPOA extends org.omg.PortableServer.Servant
        implements CallbackServerOperations, org.omg.CORBA.portable.InvokeHandler
{
    public CallbackServer _this()
    {
        return CallbackServerHelper.narrow(_this_object());
    }

    public CallbackServer _this(org.omg.CORBA.ORB orb)
    {
        return CallbackServerHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:callbacks/CallbackServer:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("register")) {
                return _invoke_register(_is, handler);
        } else if (opName.equals("sayHello")) {
                return _invoke_sayHello(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_sayHello(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        sayHello();

        _output = handler.createReply();

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_register(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        callbacks.CallbackClient arg0_in = callbacks.CallbackClientHelper.read(_is);

        register(arg0_in);

        _output = handler.createReply();

        return _output;
    }

}
