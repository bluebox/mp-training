package Practice.july16_Networking;

import java.io.*;
import java.net.*;

public class Server {
public static void main(String[] args) throws IOException {
Socket cs=new Socket("localhost",5000);
System.out.println("Client started...");
DataOutputStream out = new DataOutputStream(cs.getOutputStream());
DataInputStream in = new DataInputStream(cs.getInputStream());
out.writeUTF("Hello from client");
String message = in.readUTF();
System.out.println("server says: " + message);

cs.close();
}
}


