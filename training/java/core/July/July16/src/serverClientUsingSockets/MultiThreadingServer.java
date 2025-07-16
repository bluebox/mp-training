package serverClientUsingSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingServer {
	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(5000);) {

			while (true) {
				Socket sokect = serverSocket.accept();
				System.out.println("Server accepts client conneciton");
				serverSocket.setSoTimeout(9000000);
				executorService.submit(() -> handleClientRequest(sokect));

			}

		} catch (IOException e) {
			System.out.println("Server Error " + e.getMessage());
		}
	}

	private static void handleClientRequest(Socket socket) {
		try (socket;
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);) {

			while (true) {
				String echoString = input.readLine();
				System.out.println("Server got request data: " + echoString);

				if (echoString.equals("exit")) {
					break;
				}
				output.println("Echo from server : " + echoString);
			}

		} catch (IOException e) {
			System.out.println("Server Error " + e.getMessage());
		}
	}
}
