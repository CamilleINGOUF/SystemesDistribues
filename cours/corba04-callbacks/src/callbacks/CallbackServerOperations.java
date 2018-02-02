package callbacks;

/**
 * Interface definition: CallbackServer.
 * 
 * @author OpenORB Compiler
 */
public interface CallbackServerOperations
{
    /**
     * Operation sayHello
     */
    public void sayHello();

    /**
     * Operation register
     */
    public void register(callbacks.CallbackClient client);

}
