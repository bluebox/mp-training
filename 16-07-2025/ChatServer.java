package com.prana;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientOutputs = Collections.synchronizedSet(new HashSet<>());
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Chat Server started...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);
                clientOutputs.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(message);
                    if ("exit".equalsIgnoreCase(message)) break;
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                clientOutputs.remove(out);
                try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }

        private void broadcast(String message) {
            synchronized (clientOutputs) {
                for (PrintWriter writer : clientOutputs) {
                    writer.println("Client says: " + message);
                }
            }
        }
    }
}