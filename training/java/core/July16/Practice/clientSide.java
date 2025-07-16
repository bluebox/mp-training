package July16.Practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class clientSide {

	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;

	public clientSide(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");
			input = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
		}catch (IOException e) {
			e.printStackTrace();
		}

		String line = "";
		while (!line.equals("End")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
			}
			catch (IOException i) {
				System.out.println(i);
			}
		}
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		clientSide client = new clientSide("127.0.0.1", 5000);
	}
}
