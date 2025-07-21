package Networking_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("connected to server");
			Socket clientsocket=new Socket("localhost",5000);
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("enter a string : ");
			String str=input.readLine();
			PrintWriter clientwriter=new PrintWriter(clientsocket.getOutputStream(),true);
			clientwriter.println(str);
			BufferedReader output=new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			System.out.println(output.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
