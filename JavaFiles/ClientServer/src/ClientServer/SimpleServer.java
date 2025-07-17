package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args) {

		try (ServerSocket serverScoket = new ServerSocket(5000)) {

			try (Socket socket = serverScoket.accept()) {
				System.out.println("Server accepts clients connection");
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String data = input.readLine();
					System.out.println("Server got request data: " + data);
					if (data.equals("exit")) {
						break;
					}
					output.println("Mess from server: " + data);
				}
			}

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Server Error " + e.getMessage());
		}
	}

}
