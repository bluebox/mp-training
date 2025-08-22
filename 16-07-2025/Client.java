package com.prana;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        Socket socket = new Socket("localhost", 5000);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Hello Server!");
        String response = input.readLine();
        System.out.println("Received from server: " + response);
        socket.close();
    }
}