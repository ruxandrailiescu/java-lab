package ro.ase.acs.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;

public class TCPServer extends Thread {
	
	// OPTIMIZARE TO DO -- SCOATERE CLIENTI DIN LISTA CAND SE DECONECTEAZA
	
	// facem broadcast (trimitem catre toti clientii)
	
	// clasa Socket si Vector sunt thread-safe (ArrayList nu e thread-safe)
	
	// pt fiecare client vom avea alt fir de executie
	
	private Socket socket;		// socketul clientului care trimite mesajul (client curent)
	private static List<Socket> clients = new Vector<>();		// toate socketurile / toti clientii
	
	public TCPServer(Socket socket) {
		this.socket = socket;
	}
	
	// metoda prin care primim date
	public String receive() {
		
		String message = "";
		InputStream is;
		
		try {
		
			is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			message = dis.readUTF();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	// metoda prin care trimitem date (catre toti clientii - broadcasting)
	public void send(String message) {
		
		for (Socket socket : clients) {
			
			OutputStream os;
			try {
			
				os = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF(message);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void run() {
		super.run();
		
		while (true) {
			
			String message = receive();
			send(message);
			
		}
	}

	public static void main(String[] args) {
		
		try (ServerSocket server = new ServerSocket(7777)) {
			
			System.out.println("Server started on port 7777");
			
			// crearea unei cereri HTTP de tip GET
//			URL url = new URL("http://google.ro");
//			URLConnection connection = url.openConnection();
//			InputStream inputStream = connection.getInputStream();
//			InputStreamReader reader = new InputStreamReader(inputStream);
//			BufferedReader bufferedReader = new BufferedReader(reader);
//			System.out.println(bufferedReader.readLine());
			
			while (true) {
				
				Socket socket = server.accept();	// linie blocanta
				clients.add(socket);	// adaugam clientul in lista (clientul conectat la server)
				TCPServer serverInstance = new TCPServer(socket);
				serverInstance.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
