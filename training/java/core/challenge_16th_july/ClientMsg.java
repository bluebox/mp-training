package challenge_16th_july;

import java.io.*;
import java.net.*;

public class ClientMsg {

 public static void main(String[] args)
 {
     try {
         Socket socket = new Socket("localhost", 1346);
         System.out.println(
             "Connected Successfully.....");

         BufferedReader bs = new BufferedReader(
             new InputStreamReader(socket.getInputStream()));
         System.out.println("Response from Server.....");

         System.out.println("Client Side : "
                            + bs.readLine());
         socket.close();
     }
     catch (UnknownHostException e) {
         System.out.println("IP not found for" + e);
     }
     catch (IOException e) {
         System.out.println("Not found data for socket"
                            + e);
     }     
 }
}
