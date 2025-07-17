package Day14_16_07_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try(ServerSocket server=new ServerSocket(5000)){
			try(Socket socket=server.accept();){
			
			System.out.println("server accepts clients connection");
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			
			
			
			while(true) {
				String input=br.readLine();
				System.out.println("server got request data"+input);
				if(input.equals("exit")) {
					break;
				}
				out.println("server response"+input);
				
				
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
