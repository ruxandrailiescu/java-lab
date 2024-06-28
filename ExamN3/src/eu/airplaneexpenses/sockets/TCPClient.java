package eu.airplaneexpenses.sockets;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient extends Thread {
	
	private static final int PORT = 7777;
	
	public TCPClient() {}
	
	public void send() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the file name you wish to send (without extension): ");
		String file = scanner.nextLine();
		scanner.close();
		
		try (Socket socket = new Socket("localhost", PORT);
				OutputStream os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				FileInputStream fis = new FileInputStream(file + ".bin");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			System.out.println("Connection accepted");
			dos.writeUTF(file);
			
			oos.writeObject(ois.readObject());
			
			System.out.println("Object sent");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		send();
	}

	public static void main(String[] args) {
		
		TCPClient client = new TCPClient();
		client.start();
	}

}
