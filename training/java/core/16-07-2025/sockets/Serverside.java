package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverside {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serversocket=new ServerSocket(6000);
		System.out.println("Sever started");
		Socket socket =serversocket.accept();
		System.out.println("client connected");
		BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
		String message=reader.readLine();
		System.out.println("message"+message);
		writer.println(message);
		serversocket.close();
	}

}
