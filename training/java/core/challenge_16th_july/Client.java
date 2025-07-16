package challenge_16th_july;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("asdasd");
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello from Client");
        socket.close();
    }
}
