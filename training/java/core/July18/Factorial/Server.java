package July18.Factorial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("Server is started");
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server is waiting for the request from the client");
			Socket s = ss.accept();
			System.out.println("Client connected");
			InputStream in = s.getInputStream();
			int n = in.read();
			int result = 1;
			for (int i = 2; i <= n; i++) {
				result *= i;
			}
			OutputStream out = s.getOutputStream();
			out.write(result);
			ss.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
