package Day14_16_07_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {
	public static void main(String[] args) {
		ExecutorService ex=Executors.newCachedThreadPool();	
		try(ServerSocket server=new ServerSocket(5000)){
			while(true) {
			Socket socket=server.accept();
			System.out.println("server accepts clients connection");
			socket.setSoTimeout(100_0000);
			ex.submit(()->handleClientRequest(socket));
			}
		}catch(IOException e) {
			System.out.println();
		}
	}
	private static void handleClientRequest(Socket socket) {
		try(socket;
			BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output=new PrintWriter(socket.getOutputStream(),true);
			){
			while(true) {
				String st=input.readLine();
				System.out.println("server got request:"+st);
				if(st.equals("exist")) {
					break;
				}
				output.println("response from server:"+st);
			}
		}catch(Exception e) {
			System.out.println("Disconnecting!");
		}
	}
}			