package day15;

import java.io.*;
import java.net.Socket;

public class ClientSide {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DataInputStream input = null;
		DataOutputStream out = null; 
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 5000);
			input = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String line = "";
		while (!line.equalsIgnoreCase("end")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		try {
			System.out.println("Closing connection");
			input.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
