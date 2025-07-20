package July18;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("Server started");
			ServerSocket ss = new ServerSocket(5000);
			System.out.println("server is waiting for the request from the client");
			Socket s = ss.accept();
			System.out.println("Client connected");
			DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			String line = "";
			while (!line.equals("End")) {
				line = in.readUTF();
				System.out.println(line);
			}
			System.out.println("Closing the connection");
			s.close();
			in.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
