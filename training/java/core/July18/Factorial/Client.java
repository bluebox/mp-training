package July18.Factorial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 9999);
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter a number : ");
			int n = sc.nextInt();
			OutputStream out = s.getOutputStream();
			out.write(n);
			InputStream in = s.getInputStream();
			System.out.println("Factorial of " + n + " is " + in.read());
			s.close();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
