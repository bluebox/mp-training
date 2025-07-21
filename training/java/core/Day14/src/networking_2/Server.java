package networking_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("waiting for client");
			ServerSocket ss=new ServerSocket(5000);
			Socket s=ss.accept();
			System.out.println("connected with client");
			BufferedReader reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
			int num=Integer.parseInt(reader.readLine());
			PrintWriter out=new PrintWriter(s.getOutputStream(),true);
			out.println("factorial of "+num+" is "+factorial(num));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int factorial(int num) {
		int ans=1;
		for(int i=num;i>=1;i--) {
			ans*=i;
		}
		return ans;
	}
}
