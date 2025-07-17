package serversocket;

import java.io.DataOutputStream;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 6666);
             DataOutputStream d = new DataOutputStream(s.getOutputStream())) {

            d.writeUTF("Hello GFG Readers!");
            System.out.println("Client message sent successfully!");

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
