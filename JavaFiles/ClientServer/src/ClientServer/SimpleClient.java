package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 5000)) {

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			Scanner sc = new Scanner(System.in);

			String requestString;
			String responseString;

			do {
				System.out.println("Enter strign to be sent to server :");
				requestString = sc.nextLine();

				output.println(requestString);
				if (!requestString.equals("exit")) {
					responseString = input.readLine();
					System.out.println(responseString);
				}

			} while (!requestString.equals("exit"));

		} catch (IOException e) {
			System.out.println("Server Error " + e.getMessage());

		} finally {
			System.out.println("Client Disconnected");
		}

	}

}
