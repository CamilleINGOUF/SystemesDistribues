package callbacks;

/** 
 * Helper class for : CallbackServer
 *  
 * @author OpenORB Compiler
 */ 
public class CallbackServerHelper
{
    /**
     * Insert CallbackServer into an any
     * @param a an any
     * @param t CallbackServer value
     */
    public static void insert(org.omg.CORBA.Any a, callbacks.CallbackServer t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract CallbackServer from an any
     *
     * @param a an any
     * @return the extracted CallbackServer value
     */
    public static callbacks.CallbackServer extract( org.omg.CORBA.Any a )
    {
        if ( !a.type().equivalent( type() ) )
        {
            throw new org.omg.CORBA.MARSHAL();
        }
        try
        {
            return callbacks.CallbackServerHelper.narrow( a.extract_Object() );
        }
        catch ( final org.omg.CORBA.BAD_PARAM e )
        {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the CallbackServer TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc( id(), "CallbackServer" );
        }
        return _tc;
    }

    /**
     * Return the CallbackServer IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:callbacks/CallbackServer:1.0";

    /**
     * Read CallbackServer from a marshalled stream
     * @param istream the input stream
     * @return the readed CallbackServer value
     */
    public static callbacks.CallbackServer read(org.omg.CORBA.portable.InputStream istream)
    {
        return(callbacks.CallbackServer)istream.read_Object(callbacks._CallbackServerStub.class);
    }

    /**
     * Write CallbackServer into a marshalled stream
     * @param ostream the output stream
     * @param value CallbackServer value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, callbacks.CallbackServer value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to CallbackServer
     * @param obj the CORBA Object
     * @return CallbackServer Object
     */
    public static CallbackServer narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof CallbackServer)
            return (CallbackServer)obj;

        if (obj._is_a(id()))
        {
            _CallbackServerStub stub = new _CallbackServerStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to CallbackServer
     * @param obj the CORBA Object
     * @return CallbackServer Object
     */
    public static CallbackServer unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof CallbackServer)
            return (CallbackServer)obj;

        _CallbackServerStub stub = new _CallbackServerStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
