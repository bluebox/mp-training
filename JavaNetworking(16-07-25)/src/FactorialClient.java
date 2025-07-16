import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FactorialClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		System.out.println("Client Started");
		Socket soc=new Socket("localhost",8081);
		BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number");
		int num=Integer.parseInt(userInput.readLine());
		PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
		out.println(num);
		BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
		System.out.println(in.readLine());	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
