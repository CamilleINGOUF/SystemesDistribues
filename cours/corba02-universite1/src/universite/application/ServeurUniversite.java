package universite.application;

import java.io.PrintWriter;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;


public class ServeurUniversite {
	
	public static void main(String[] args) throws Exception {
		
		ORB orb = ORB.init(args, null) ;
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")) ;

		EtudiantImpl impl = new EtudiantImpl() ;
		
		byte[] id = rootPOA.activate_object(impl) ;
		org.omg.CORBA.Object ref = rootPOA.id_to_reference(id) ;
		
		String ior = orb.object_to_string(ref) ;
		System.out.println(ior) ;
		
		PrintWriter file = new PrintWriter("ior.txt") ;
		file.println(ior) ;
		file.close() ;
		
		rootPOA.the_POAManager().activate() ;
		orb.run() ;
	}
	
}
