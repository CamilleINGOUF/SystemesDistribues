package callbacks.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import callbacks.CallbackClient;
import callbacks.CallbackClientHelper;
import callbacks.CallbackClientPOA;
import callbacks.CallbackServer;
import callbacks.CallbackServerHelper;

public class CallbackClientImpl extends CallbackClientPOA{

	private String name;
	private CallbackServer server;

	private ORB orb;
	
	private CallbackClient cref;

	public CallbackClientImpl(String name, String[] args) 
	{
		try {
			this.name = name;

			orb = ORB.init(args, null);
			Thread orbThread = new Thread(new Runnable() {
				@Override
				public void run() 
				{
					orb.run();
				}
			});
			orbThread.start();

			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(this);
			cref = CallbackClientHelper.narrow(ref);
			
			BufferedReader br = new BufferedReader(new FileReader("server_ior.txt"));
			String ior = br.readLine();
			br.close();
			
			org.omg.CORBA.Object o = orb.string_to_object(ior);
			server = CallbackServerHelper.narrow(o);
			server.register(cref); // Obligé de thread orb.run() pour register ensuite, et obligé d'avoir un orb de run pour register
			
			server.sayHello();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void showYourName() 
	{
		System.out.println(name);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Give me your name : ");
		String name = in.nextLine();
		new CallbackClientImpl(name, args);
	}

}
