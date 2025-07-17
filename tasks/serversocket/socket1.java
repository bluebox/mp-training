package serversocket;

import java.io.*;
import java.net.*;

public class socket1 {
    public static void main(String[] args) {
        try (ServerSocket soc = new ServerSocket(6666)) {
            System.out.println("Server is listening on port 6666...");

            try (Socket s = soc.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter output = new PrintWriter(s.getOutputStream(), true)) {

                System.out.println("Server accepts client connection");

                while (true) {
                    String echoString = input.readLine();
                    if (echoString == null || echoString.equalsIgnoreCase("exit")) {
                        break;
                    }
                    System.out.println("Server received: " + echoString);
                    output.println("Echo from server: " + echoString);
                }

            } catch (IOException e) {
                System.out.println("Server exception: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }
}
