package ro.ase.acs.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient extends Thread {
	
	private Socket socket;
	
	public TCPClient(Socket socket) {
		this.socket = socket;
	}
	
	// primirea de mesaje
	@Override
	public void run() {
		super.run();
		
		while (true) {
			
			InputStream is;
			try {
			
				is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				String message = dis.readUTF();
				System.out.println(message);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Your name: ");
		String name = scanner.nextLine();
		
		try (Socket socket = new Socket("localhost", 7777)) {
			
			TCPClient client = new TCPClient(socket);
			client.start();
			
			while (true) {
				
				// trimiterea de mesaje
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				
				String message = scanner.nextLine();
				dos.writeUTF(String.format("%s: %s", name, message));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}

}
