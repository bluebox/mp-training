package July18;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {

		try {
			System.out.println("Client started");
			Socket s = new Socket("localhost", 5000);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			DataInputStream in = new DataInputStream(System.in);
			String line = "";
			while(!line.equals("End")) {
				line = in.readLine();
				//System.out.println(line);
				out.writeUTF(line);
			}
			s.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
