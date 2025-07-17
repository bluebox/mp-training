package serversocket;
import java.io.*;
import java.net.*;
import java.util.*;

public class clientcl {
	public static void main(String[] args) {
		try(Socket soc=new Socket("localhost",1234)){
			//writing to serevr
			PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
			//reading from server
			BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			//object 
			Scanner sc=new Scanner(System.in);
			String line=null;
			while(!"exit".equalsIgnoreCase(line)) {
				//reading from user
				line=sc.nextLine();
				//sending the user input to server
				out.println(line);
				out.flush();
				System.out.println("server replied"+ in.readLine());
			}
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
