package networks;

import java.io.*;
import java.net.*;

public class ClientClass {
	
    public static void main(String[] args) throws IOException {
        
        Socket socket = new Socket("localhost", 3100);
        
        System.out.println("Connected to server.");

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Hello, server! This is the client.");
        
        output.close();
        socket.close();
    }
}
