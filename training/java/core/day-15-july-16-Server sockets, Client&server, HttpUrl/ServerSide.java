package day15;

import java.io.*;
import java.net.*;

public class ServerSide {

	public static void main(String[] args) {
		Socket socket = null;
		ServerSocket server = null;
		DataInputStream in = null;
		try {
			server = new ServerSocket(5000);
			System.out.println("Started server");
			System.out.println("waiting for client");
			socket = server.accept();
			System.out.println("client accepted");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String line = "";
			while (!line.equalsIgnoreCase("end")) {
				line = in.readUTF();
				System.out.println(line);
			}
			System.out.println("Closing connection");
			socket.close();
			in.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
