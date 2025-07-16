package jdbcNetworking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class networkMain {

public static void main(String [] args)throws IOException
{

	 try (Socket client = new Socket("localhost",6060)) {
		PrintWriter pr=new PrintWriter(client.getOutputStream());
		pr.println("hello");
		pr.flush();

	 }catch(IOException e)
	 {
		 e.printStackTrace();
	 }
}


}
