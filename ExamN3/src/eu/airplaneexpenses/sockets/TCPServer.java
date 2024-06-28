package eu.airplaneexpenses.sockets;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import eu.airplaneexpenses.classes.Airplane;

public class TCPServer extends Thread {
	
	public static final int PORT = 7777;
	private Socket socket;
	public static Set<Airplane> airplanes = new TreeSet<>();
	
	public TCPServer(Socket socket) {
		this.socket = socket;
	}
	
	public void receive() {
		
		try (InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				ObjectInputStream ois = new ObjectInputStream(is)) {
			
			String filePath = dis.readUTF();
			try (FileOutputStream fos = new FileOutputStream(filePath + "_received.bin");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				
				oos.writeObject(ois.readObject());
				
				System.out.println("Object received");
				
				// deserializing received file
				Airplane airplane = Airplane.deserialize(filePath + "_received.bin");
				airplanes.add(airplane);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		super.run();
		receive();
		
		for (Airplane a : airplanes) {
			System.out.println(a);
			System.out.println("-----------------------------------");
		}
	}
	
	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			
			System.out.println("Server started on port " + PORT);
			
			ExecutorService threadPool = Executors.newCachedThreadPool();
			while (true) {
				
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connection accepted");
				TCPServer s = new TCPServer(clientSocket);
				threadPool.execute(s);
			}
			
//			Socket clientSocket = serverSocket.accept();
//			TCPServer server = new TCPServer(clientSocket);
//			server.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
