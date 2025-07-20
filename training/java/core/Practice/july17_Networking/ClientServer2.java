package Practice.july17_Networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientServer2 {
	public static void main(String[] args) {
		try {
			Socket client1=new Socket("localhost",9000);
			System.out.println("client2 started");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client1.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(client1.getInputStream()));
			out.write("Hi from client2\n");
			out.flush();
			String serverstr=in.readLine();
			System.out.println("Server says: "+serverstr);
			client1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
