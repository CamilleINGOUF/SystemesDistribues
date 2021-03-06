package callbacks.impl;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import callbacks.CallbackClient;
import callbacks.CallbackServerPOA;

public class CallbackServerImpl extends CallbackServerPOA
{
	private ArrayList<CallbackClient> clients;
	
	public CallbackServerImpl(String[] args) 
	{
		try
		{
			clients = new ArrayList<>();
		
			ORB orb = ORB.init(args, null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			
			System.out.println(InetAddress.getLocalHost().getHostName());
			
			byte[] id = rootPOA.activate_object(this);
			org.omg.CORBA.Object ref = rootPOA.id_to_reference(id);
			
			String ior = orb.object_to_string(ref);
			System.out.println(ior);
			
			PrintWriter writer = new PrintWriter("server_ior.txt");
			writer.print(ior);
			writer.close();
			
			rootPOA.the_POAManager().activate();
			orb.run();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void sayHello() 
	{
		System.out.println("Saying hello.");
	}

	@Override
	public void register(CallbackClient client) 
	{
		clients.add(client);
		System.out.println("Client named "+client.getName()+" registered.");
		client.showYourName();
	}
	
	public static void main(String[] args) 
	{
		new CallbackServerImpl(args);
	}
}
