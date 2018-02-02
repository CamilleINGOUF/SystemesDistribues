package corba_chat.impl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import corba_chat.ChatClient;
import corba_chat.ChatServerPOA;

public class ChatServerImpl extends ChatServerPOA {
	
	private ORB orb ;
	
	private HashMap<ChatClient, String> clients =  new HashMap<ChatClient, String>() ;
	
	public ChatServerImpl(String[] args) {
		try{
			
			orb = ORB.init(args, null);
			Thread orbThread = new Thread(new Runnable() { // l'appel � orb.run() �tant bloquant, on l'ex�cute dans un Thread.
				@Override
				public void run() {
					orb.run(); // !!! bloquant !!! donc dans un thread
				}
			});
			orbThread.start();
			
			// Mise a disposition de l'objet CORBA serveur
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")) ;
			rootPOA.the_POAManager().activate() ;
			byte[] id = rootPOA.activate_object(this) ;
		
			// Ecriture de l'IOR du serveur dans un fichier (a destination des clients)
			org.omg.CORBA.Object ref = rootPOA.id_to_reference(id) ;
			String ior = orb.object_to_string(ref) ;
			System.out.println(ior) ;
			PrintWriter file = new PrintWriter("server_ior.txt") ;
			file.println(ior) ;
			file.close() ;
		
		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	private void updateConnectedUsers(){
		
		Object objs[] = clients.values().toArray() ; 
		String[] names = new String[objs.length] ;
		for (int i=0 ; i<objs.length ; i++) names[i] = (String)objs[i] ;
			
		ChatClient tmp = null ; // pour retirer l'objet deconnecte en cas d'exception...
		try{
			for (ChatClient client : clients.keySet()){
				tmp = client ;
				tmp.updateConnectedUsers(names) ;
			}
		} catch (Exception e) {
			System.out.println(clients.get(tmp) + " est deconnecte !");
			clients.remove(tmp) ;
			updateConnectedUsers() ;
		}
	}
	
	@Override
	public void register(ChatClient c) {
		System.out.println("Server registering " + c.getUserName());
		clients.put(c, c.getUserName()) ;
		updateConnectedUsers() ;
	}
	
	@Override
	public void unRegister(ChatClient c) {
		System.out.println("Server unregistering " + c.getUserName());
		clients.remove(c) ;
		updateConnectedUsers() ;
	}
	
	@Override
	public void sendToAll(String msg, ChatClient from) {
		String senderName = clients.get(from) ;
		System.out.println("Server sending " + msg + " from " + senderName);
		
		Vector<ChatClient> disconnected = new Vector<ChatClient>() ;
		for(ChatClient c : clients.keySet()){
			try{
				c.addMSG(senderName + " > " + msg + "\n") ;
			}catch(Exception e){
				System.out.println("ChatServerImpl.sendToAll()");
				disconnected.add(c) ; // gestion des clients d�connect�s
			}
		}
		
		// nettoyage de la liste
		if (!disconnected.isEmpty()) {
			for(ChatClient c : disconnected){
				System.out.println(c + " est deconnecte !");
				clients.remove(c) ;
			}
			updateConnectedUsers() ;
		}
	}
	
	public static void main(String[] args) {
		new ChatServerImpl(args) ;
	}

}
