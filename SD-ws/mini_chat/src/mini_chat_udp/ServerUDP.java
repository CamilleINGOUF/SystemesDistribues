package mini_chat_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

	public static final int PORT = 4666;
	
	public ServerUDP() {
		try {
			DatagramSocket socket = new DatagramSocket(PORT);
			
			byte[] sendbuffer = null ;
			byte[] receivebuffer = new byte[256] ;
			
			DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
			DatagramPacket sendPacket = null;
			
			while(true)
			{
				System.out.println("Server waiting...");
				socket.receive(receivePacket);
				System.out.println(new String(receivePacket.getData()));
				
				InetAddress address = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				sendbuffer = ("Votre adresse est "+address+" avec le port "+port).getBytes();
				sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, address, port);
				
				socket.send(sendPacket);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ServerUDP();
	}
	
}
