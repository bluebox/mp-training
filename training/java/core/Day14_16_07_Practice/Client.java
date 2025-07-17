package Day14_16_07_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] args) {
		try(Socket sk=new Socket("localhost",5000)){
			BufferedReader br=new BufferedReader(new InputStreamReader(sk.getInputStream()));
			PrintWriter out=new PrintWriter(sk.getOutputStream(),true);
			
			Scanner sc=new Scanner(System.in);
			String request,response;
			
			do {
				System.out.println("Enter request to server:");
				request=sc.nextLine();
				out.println(request);
				if(!request.toLowerCase().equals("exit")) {
					response=br.readLine();
					System.out.println(response);
				}
			}while(!request.toLowerCase().equals("exit"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("Disconnected server");
		}
	}
}
