package callbacks;

/**
 * Interface definition: CallbackClient.
 * 
 * @author OpenORB Compiler
 */
public abstract class CallbackClientPOA extends org.omg.PortableServer.Servant
        implements CallbackClientOperations, org.omg.CORBA.portable.InvokeHandler
{
    public CallbackClient _this()
    {
        return CallbackClientHelper.narrow(_this_object());
    }

    public CallbackClient _this(org.omg.CORBA.ORB orb)
    {
        return CallbackClientHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:callbacks/CallbackClient:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("getName")) {
                return _invoke_getName(_is, handler);
        } else if (opName.equals("showYourName")) {
                return _invoke_showYourName(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_getName(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        String _arg_result = getName();

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_showYourName(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;

        showYourName();

        _output = handler.createReply();

        return _output;
    }

}
