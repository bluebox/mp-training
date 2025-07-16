package networks;

import java.io.*;
import java.net.*;

public class SocketClass {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3100); 
        System.out.println("Server started. Waiting for client...");

        Socket clientSocket = serverSocket.accept(); 
        System.out.println("Client connected.");

        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = input.readLine(); 
        System.out.println("Received from client: " + message);

        
        input.close();
        clientSocket.close();
        serverSocket.close();
    }
}
