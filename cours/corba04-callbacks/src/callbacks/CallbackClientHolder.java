package callbacks;

/**
 * Holder class for : CallbackClient
 * 
 * @author OpenORB Compiler
 */
final public class CallbackClientHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal CallbackClient value
     */
    public callbacks.CallbackClient value;

    /**
     * Default constructor
     */
    public CallbackClientHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public CallbackClientHolder(callbacks.CallbackClient initial)
    {
        value = initial;
    }

    /**
     * Read CallbackClient from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = CallbackClientHelper.read(istream);
    }

    /**
     * Write CallbackClient into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        CallbackClientHelper.write(ostream,value);
    }

    /**
     * Return the CallbackClient TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return CallbackClientHelper.type();
    }

}
