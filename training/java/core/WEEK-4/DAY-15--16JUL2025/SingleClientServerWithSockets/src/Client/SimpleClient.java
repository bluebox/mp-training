package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
	public static void main(String [] args) {
		System.out.println("Client running succesfully...");
		try(Socket client=new Socket("localhost",5000);
			Scanner sc=new Scanner(System.in)){
			BufferedReader input=new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter output=new PrintWriter(client.getOutputStream(),true);
			while(true) {
				String sysin=sc.nextLine();
				output.println(sysin);
				String response=input.readLine();
				System.out.println(response);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Client disconeted");
		}
	}
}
