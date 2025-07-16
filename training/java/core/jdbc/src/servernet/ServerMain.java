package servernet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {
	public static void main(String []args) throws IOException{
	//String srvrName="localhost";
	 
	 try (ServerSocket x = new ServerSocket( 6060)) {
		 System.out.println("SERVER STARTED");
		 
		 Socket g=x.accept();
		 System.out.println("server connected");
		 InputStreamReader in=new InputStreamReader(g.getInputStream());
		 BufferedReader bf=new BufferedReader(in);
		 String str= bf.readLine();
		 System.out.println(str);
		 
		 x.close();
	 }catch(IOException e)
	 {
		 e.printStackTrace();
	 }
	}

}
