package Practice.july17_Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	public static void main(String[] args)  {
		try {
			ServerSocket server=new ServerSocket(9000);
			System.out.println("Server Started");
			try {
				while(true) {
					Socket client=server.accept();
					System.out.println("Connection established with a new client");
					Thread newConnection=new Thread(new ClientHandler(client));
					newConnection.start();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
