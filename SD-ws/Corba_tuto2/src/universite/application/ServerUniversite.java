package universite.application;

import java.io.PrintWriter;
import java.net.InetAddress;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ServerUniversite {

	public static void main(String[] args) throws Exception 
	{
		ORB orb = ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		
		System.out.println(InetAddress.getLocalHost().getHostName());
		
		FacImpl fac = new FacImpl(rootPOA);
		
		byte[] id = rootPOA.activate_object(fac);
		org.omg.CORBA.Object ref = rootPOA.id_to_reference(id);
		
		String ior = orb.object_to_string(ref);
		System.out.println(ior);
		
		PrintWriter writer = new PrintWriter("ior.txt");
		writer.print(ior);
		writer.close();
		
		rootPOA.the_POAManager().activate();
		orb.run();
	}

}
