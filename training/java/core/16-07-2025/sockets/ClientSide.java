package sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket=new Socket("localhost",6000);
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));//take input from keyboard
		PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);//prepares to send data to server
		BufferedReader responseReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Enter a message");
		String message=reader.readLine();
		writer.println(message);
		String response=responseReader.readLine();
		System.out.println("Server says:"+response);
		socket.close();
		
		
	}

}
