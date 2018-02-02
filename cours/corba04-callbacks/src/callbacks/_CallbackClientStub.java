package callbacks;

/**
 * Interface definition: CallbackClient.
 * 
 * @author OpenORB Compiler
 */
public class _CallbackClientStub extends org.omg.CORBA.portable.ObjectImpl
        implements CallbackClient
{
    static final String[] _ids_list =
    {
        "IDL:callbacks/CallbackClient:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = callbacks.CallbackClientOperations.class;

    /**
     * Operation getName
     */
    public String getName()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getName",true);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getName",_opsClass);
                if (_so == null)
                   continue;
                callbacks.CallbackClientOperations _self = (callbacks.CallbackClientOperations) _so.servant;
                try
                {
                    return _self.getName();
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation showYourName
     */
    public void showYourName()
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("showYourName",true);
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
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("showYourName",_opsClass);
                if (_so == null)
                   continue;
                callbacks.CallbackClientOperations _self = (callbacks.CallbackClientOperations) _so.servant;
                try
                {
                    _self.showYourName();
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
