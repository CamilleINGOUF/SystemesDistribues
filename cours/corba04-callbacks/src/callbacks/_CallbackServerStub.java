package callbacks;

/**
 * Interface definition: CallbackServer.
 * 
 * @author OpenORB Compiler
 */
public class _CallbackServerStub extends org.omg.CORBA.portable.ObjectImpl
        implements CallbackServer
{
    static final String[] _ids_list =
    {
        "IDL:callbacks/CallbackServer:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = callbacks.CallbackServerOperations.class;

    /**
     * Operation sayHello
     */
    public void sayHello()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("sayHello",true);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("sayHello",_opsClass);
                if (_so == null)
                   continue;
                callbacks.CallbackServerOperations _self = (callbacks.CallbackServerOperations) _so.servant;
                try
                {
                    _self.sayHello();
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation register
     */
    public void register(callbacks.CallbackClient client)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("register",true);
                    callbacks.CallbackClientHelper.write(_output,client);
                    _input = this._invoke(_output);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("register",_opsClass);
                if (_so == null)
                   continue;
                callbacks.CallbackServerOperations _self = (callbacks.CallbackServerOperations) _so.servant;
                try
                {
                    _self.register( client);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
