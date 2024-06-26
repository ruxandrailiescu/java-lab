package ro.ase.acs.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient extends Thread {
	
	private DatagramSocket socket;
	
	public UDPClient() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		while (true) {
			
			// primim pachet de la server
			byte[] buffer = new byte[256];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			
			try {
			
				socket.receive(receivedPacket);
				String receivedMessage = new String(receivedPacket.getData());
				System.out.println(receivedMessage);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) {
		
		// clientii nu precizeaza portul
		
		Scanner scanner = new Scanner(System.in);
		UDPClient client = new UDPClient();
		client.start();
		
		try {
			
			InetAddress serverAddress = InetAddress.getByName("localhost");
			int serverPort = 7777;
			
			while (true) {
				
				// trimitem pachet catre server
				String message = scanner.nextLine();
				byte[] bytes = message.getBytes();
				
				DatagramPacket packet = new DatagramPacket(bytes, bytes.length, serverAddress, serverPort);
				client.socket.send(packet);

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}

}
