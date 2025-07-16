package challenge_16th_july;

import java.io.*;
import java.net.*;

class ClientHandler extends Thread {
    private Socket socket;
    public ClientHandler(Socket socket) { this.socket = socket; }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client says: " + in.readLine());
            socket.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5001);
        while (true) {
            Socket client = server.accept();
            new ClientHandler(client).start();
        }
    }
}
