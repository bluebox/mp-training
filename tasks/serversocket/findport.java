package serversocket;
import java.net.InetAddress;

import java.net.ServerSocket;

public class findport {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(0)) { // 0 means OS assigns a free port
            System.out.println("Port: " + serverSocket.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

