package mini_chat_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
	public ClientUDP() {
		try {
			InetAddress address = InetAddress.getByName("localhost");
			DatagramSocket socket = new DatagramSocket();
			
			byte[] buffer = "Bonjour serveur".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, ServerUDP.PORT);
			
			buffer = new byte[256];
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length, address, ServerUDP.PORT);
			
			socket.send(sendPacket);
			
			System.out.println("Waiting...");
			socket.receive(receivePacket);
			System.out.println(new String(receivePacket.getData()));
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ClientUDP();
	}
}
