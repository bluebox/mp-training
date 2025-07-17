package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String [] args) {
		try(ServerSocket server=new ServerSocket(5000)){
			System.out.println("Server Running at 5000");
			try(Socket clientSocket=server.accept()){
				System.out.println("Connection obtained with client");
				BufferedReader input=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter output=new PrintWriter(clientSocket.getOutputStream(), true);
				while(true) {
					String command=input.readLine();
					System.out.println("Command recievd froms client is : "+command);
					if(command.equals("exit")) {
						break;
					}
					output.println("Response from server is : "+command);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
