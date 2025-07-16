import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FactorialServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("waiting for clients");
			ServerSocket ss=new ServerSocket(8081);
			Socket soc=ss.accept();
			System.out.println("connection established");
			BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			int num=Integer.parseInt(in.readLine());
			PrintWriter out=new PrintWriter(soc.getOutputStream(),true);
			out.println("factorial of the number is "+factorial(num));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	static int factorial(int num)
	{
		if(num==0)
			return 1;
		else
			 return num*factorial(num-1);
	}

}
