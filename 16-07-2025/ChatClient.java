package com.prana;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        Socket socket = new Socket("localhost", 6000);
        try (
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            new Thread(() -> {
                try {
                    String fromServer;
                    while ((fromServer = in.readLine()) != null) {
                        System.out.println(fromServer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("Connected! Start chatting:");
            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input);
                if ("exit".equalsIgnoreCase(input)) break;
            }
        } finally {
            socket.close();
        }
    }
}