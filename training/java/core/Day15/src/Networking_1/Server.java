package Networking_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		try {
			System.out.println("waiting for connection ");
			ServerSocket serversocket=new ServerSocket(5000);
			Socket socketObject=serversocket.accept();
			System.out.println("connection established");
			
			BufferedReader output = new BufferedReader(new InputStreamReader(socketObject.getInputStream()));
			String str=output.readLine();
			PrintWriter serverClient=new PrintWriter(socketObject.getOutputStream(),true);
			serverClient.println("from server : "+ str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
