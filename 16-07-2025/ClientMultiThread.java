package com.prana;

import java.io.*;
import java.net.*;

public class ClientMultiThread {
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        Socket socket = new Socket("localhost", 5000);
        try (
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String inputLine;
            System.out.println("Connected to server. Type messages:");
            while ((inputLine = userInput.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Server response: " + in.readLine());
                if ("exit".equalsIgnoreCase(inputLine)) break;
            }
        } finally {
            socket.close();
        }
    }
}