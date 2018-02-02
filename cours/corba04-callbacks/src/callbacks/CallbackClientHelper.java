package callbacks;

/** 
 * Helper class for : CallbackClient
 *  
 * @author OpenORB Compiler
 */ 
public class CallbackClientHelper
{
    /**
     * Insert CallbackClient into an any
     * @param a an any
     * @param t CallbackClient value
     */
    public static void insert(org.omg.CORBA.Any a, callbacks.CallbackClient t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract CallbackClient from an any
     *
     * @param a an any
     * @return the extracted CallbackClient value
     */
    public static callbacks.CallbackClient extract( org.omg.CORBA.Any a )
    {
        if ( !a.type().equivalent( type() ) )
        {
            throw new org.omg.CORBA.MARSHAL();
        }
        try
        {
            return callbacks.CallbackClientHelper.narrow( a.extract_Object() );
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
     * Return the CallbackClient TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc( id(), "CallbackClient" );
        }
        return _tc;
    }

    /**
     * Return the CallbackClient IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:callbacks/CallbackClient:1.0";

    /**
     * Read CallbackClient from a marshalled stream
     * @param istream the input stream
     * @return the readed CallbackClient value
     */
    public static callbacks.CallbackClient read(org.omg.CORBA.portable.InputStream istream)
    {
        return(callbacks.CallbackClient)istream.read_Object(callbacks._CallbackClientStub.class);
    }

    /**
     * Write CallbackClient into a marshalled stream
     * @param ostream the output stream
     * @param value CallbackClient value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, callbacks.CallbackClient value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to CallbackClient
     * @param obj the CORBA Object
     * @return CallbackClient Object
     */
    public static CallbackClient narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof CallbackClient)
            return (CallbackClient)obj;

        if (obj._is_a(id()))
        {
            _CallbackClientStub stub = new _CallbackClientStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to CallbackClient
     * @param obj the CORBA Object
     * @return CallbackClient Object
     */
    public static CallbackClient unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof CallbackClient)
            return (CallbackClient)obj;

        _CallbackClientStub stub = new _CallbackClientStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
