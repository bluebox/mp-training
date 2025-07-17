package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedSimpleServer {
	public static void main(String[] args) {

		ExecutorService exeService = Executors.newCachedThreadPool();

		try (ServerSocket serverScoket = new ServerSocket(5000)) {

			while (true) {

				try (Socket socket = serverScoket.accept()) {
					System.out.println("Server accepts clients connection");
					socket.setSoTimeout(900_0000);
					exeService.submit(() -> handleClientRequest(socket));
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println("Server Error " + e.getMessage());
				}
			}

		} catch (IOException e1) {
			System.out.println("Server Error " + e1.getMessage());
		}
	}

	private static void handleClientRequest(Socket socket) {

		try (socket;
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);) {

			while (true) {
				String data = input.readLine();
				System.out.println("Server got request data: " + data);
				if (data.equals("exit")) {
					break;
				}
				output.println("Mess from server: " + data);
			}

		} catch (Exception e) {
			System.out.println("Client socket shut down here");
		}

	}
}
