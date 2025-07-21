package networking_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("connected with server");
			Socket cs= new Socket("localhost",5000);
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("enter a number : ");
			int num=Integer.parseInt(input.readLine());
			PrintWriter out=new PrintWriter(cs.getOutputStream(),true);
			out.println(num);
			BufferedReader in=new BufferedReader(new InputStreamReader(cs.getInputStream()));
			System.out.println(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
