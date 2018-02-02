package callbacks;

/**
 * Holder class for : CallbackServer
 * 
 * @author OpenORB Compiler
 */
final public class CallbackServerHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal CallbackServer value
     */
    public callbacks.CallbackServer value;

    /**
     * Default constructor
     */
    public CallbackServerHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public CallbackServerHolder(callbacks.CallbackServer initial)
    {
        value = initial;
    }

    /**
     * Read CallbackServer from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = CallbackServerHelper.read(istream);
    }

    /**
     * Write CallbackServer into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        CallbackServerHelper.write(ostream,value);
    }

    /**
     * Return the CallbackServer TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return CallbackServerHelper.type();
    }

}
