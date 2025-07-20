package Practice.july16_Networking;

//Client Side
import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		ServerSocket sc=new ServerSocket(5000);
		System.out.println("Server running. waiting for client");
		Socket s=sc.accept();
		System.out.println("Connection with client is established");
		DataInputStream di=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		String str=di.readUTF();
		System.out.println("Client says: "+str);
		String mystr="Hi I am server";
		dout.writeUTF(mystr);
		s.close();
		sc.close();
	}
}