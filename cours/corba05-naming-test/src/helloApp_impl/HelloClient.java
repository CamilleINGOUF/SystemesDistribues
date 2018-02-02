/*
 * AVANT DE DEMARRER LE SERVER -> DEMARRER LE SERVICE DE NOMMAGE :
 * 		orbd -ORBInitialPort 1050
 * 
 * POUR LANCER LE SERVEUR, NE PAS OUBLIER LES PARAMETRES DU PROGRAMME :
 * 		java HelloServer -ORBInitialPort 1050 -ORBInitialHost localhost
 * 
 * POUR LANCER LE CLIENT, NE PAS OUBLIER LES PARAMETRES DU PROGRAMME :
 * 		java HelloClient -ORBInitialPort 1050 -ORBInitialHost localhost
 * 
 */

package helloApp_impl;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import helloApp.Hello;
import helloApp.HelloHelper;

public class HelloClient {

	static Hello helloImpl;

	  public static void main(String args[])
	    {
	      try{
	        // create and initialize the ORB
	        ORB orb = ORB.init(args, null);

	        // get the root naming context
	        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	        NamingContextExt namingRef = NamingContextExtHelper.narrow(objRef);
	 
	        // resolve the Object Reference in Naming
	        String name = "Hello";
	        helloImpl = HelloHelper.narrow(namingRef.resolve_str(name));

	        System.out.println("Obtained a handle on server object: " + helloImpl);
	        System.out.println(helloImpl.sayHello());

	        } catch (Exception e) {
	          System.out.println("ERROR : " + e) ;
	          e.printStackTrace(System.out);
	          }
	    }

	
}
