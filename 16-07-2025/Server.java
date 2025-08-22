package com.prana;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is listening on port 5000...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        String message = input.readLine();
        System.out.println("Received from client: " + message);
        output.println("Hello from Server!");
        socket.close();
        serverSocket.close();
        System.out.println("client connected and request accepted");
    }
}