package ro.ase.acs.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPServer extends Thread {
	
	private final int port = 7777;
	private DatagramSocket socket;
	
	public UDPServer() {
		
		try {
		
			socket = new DatagramSocket(port);
			System.out.println("Server started on port " + port);
		
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		while (true) {
			
			// primim pachet de la client
			byte[] buffer = new byte[256];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			try {
				
				socket.receive(packet);		// functie care blocheaza firul de executie pana la primirea pachetului
				String receivedMessage = new String(packet.getData());
				System.out.println(receivedMessage);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		UDPServer server = new UDPServer();
		
		try {
			
			// primim pachet de la client
			byte[] buffer = new byte[256];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			server.socket.receive(packet); 	// functie care blocheaza firul de executie pana la primirea pachetului
			
			String receivedMessage = new String(packet.getData());
			System.out.println(receivedMessage);
			
			InetAddress clientAddress = packet.getAddress();
			int clientPort = packet.getPort();
			
			// thread separat pt primirea pachetelor
			server.start();
			
			while (true) {
				
				// trimitem pachet catre client
				String message = scanner.nextLine();
				byte[] bytes = message.getBytes();
				DatagramPacket packetToBeSent = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
				server.socket.send(packetToBeSent);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}

}
